package org.hackbots.acutator;

import org.hackbots.sensors.HBJoystick;
import org.hackbots.sensors.NavX;
import org.hackbots.sensors.SensorConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain implements IDriveTrain
{
	private TripleMotor rightMotor;
	private TripleMotor leftMotor;
	
	double currentHeading;
	
	private HBJoystick rightJoystick;
	private HBJoystick leftJoystick;
	
	private double startYaw;
	private double endYaw;
	
	public Drivetrain(TripleMotor rightMotor, TripleMotor leftMotor)
	{
		this.rightMotor = rightMotor;
		this.leftMotor = leftMotor;
	}

	public void setSpeed(double speed) 
	{	
		rightMotor.setSpeed(speed);
		leftMotor.setSpeed(speed);
	}
	
	public void setSpeed(double leftSpeed, double rightSpeed)
	{
		leftMotor.setSpeed(leftSpeed);
		rightMotor.setSpeed(rightSpeed);
	}

	public void stop() 
	{
		leftMotor.stop();
		rightMotor.stop();
	}
	
	public TripleMotor getRightMotor()
	{
		return rightMotor;
	}
	
	public TripleMotor getLeftMotor()
	{
		return leftMotor;
	}

	/*private void turn(double speed, double angle, RotationalDirection direction) 
	{
		NavX navX = SensorConfig.getInstance().getNavX();
		double currentYaw = navX.getYaw();
		double endAngle = 0;
		

		if(endAngle > 360)
		{
			endAngle = 360 - endAngle;
		}
		
		if(direction == RotationalDirection.CLOCKWISE)
		{
			endAngle = currentYaw + angle;
			
			ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, -speed);
		}
		else if(direction == RotationalDirection.COUNTERCLOCKWISE)
		{
			endAngle = currentYaw - angle;
			
			ActuatorConfig.getInstance().getDrivetrain().setSpeed(-speed, speed);
		}
		
		
	}*/

	public void turnRight(double speed, double angle)
	{
		rightJoystick = new HBJoystick(0);
		leftJoystick = new HBJoystick(1);
		
		NavX navX = SensorConfig.getInstance().getNavX();
		
		float currentYaw = navX.getYaw();
		float endAngle = currentYaw + (float)angle;
		
		ActuatorConfig.getInstance().getDrivetrain().setSpeed(-speed, speed);
		
		if(endAngle > 360) {
			endAngle = endAngle - 360;
			while(currentYaw < endAngle || (currentYaw + angle) > 360 )
			{
				currentYaw = navX.getYaw();
				if(rightJoystick.getRawButton(1) || leftJoystick.getRawButton(1))
				{
					System.out.println("Kill Switch");
					break;
			  	}
			}
		} else {
			while(currentYaw < endAngle)
			{
				currentYaw = navX.getYaw();
				if(rightJoystick.getRawButton(1) || leftJoystick.getRawButton(1))
				{
					System.out.println("Kill Switch");
					break;
			  	}
			}
		}
		ActuatorConfig.getInstance().getDrivetrain().stop();
	}
	
	public void turnLeft(double speed, double angle)
	{
		rightJoystick = new HBJoystick(0);
		leftJoystick = new HBJoystick(1);
		
		NavX navX = SensorConfig.getInstance().getNavX();
		
		float currentYaw = navX.getYaw();
		float endAngle = currentYaw - (float)angle;
		System.out.println("Start Angle: " + currentYaw);
		System.out.println("End Angle: " + endAngle);
		ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, -speed);
		if(endAngle < 0) {
			endAngle = 360 + endAngle;
			System.out.println("Adjusted End Angle: " + endAngle);
			while((currentYaw + angle) <= 360 || (currentYaw - endAngle) > 0 )
			{
				currentYaw = navX.getYaw();
				System.out.println("Current Angle: " + currentYaw);
				if(rightJoystick.getRawButton(1) || leftJoystick.getRawButton(1))
				{
					System.out.println("Kill Switch");
					break;
			  	}
			}
		} else {
			while(currentYaw > endAngle)
			{
				currentYaw = navX.getYaw();
				if(rightJoystick.getRawButton(1) || leftJoystick.getRawButton(1))
				{
					System.out.println("Kill Switch");
					break;
			  	}
			}
		}
		ActuatorConfig.getInstance().getDrivetrain().stop();
	}
	
	private void move(double distance, double startSpeed, boolean isReversed)
	{
		double rightEncoderValue = Math.abs(ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.000122));
		double leftEncoderValue =  Math.abs(ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.000122));
		
		double speed = 0;
		
		while(true)
		{		
			 rightEncoderValue = Math.abs(ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.000122));
			 leftEncoderValue = Math.abs(ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.000122));
			 
			// System.out.println("Forwarding");
			 
			 
			 currentHeading =  SensorConfig.getInstance().getNavX().getRawYaw();
			 
			 if(isReversed)
			 {
				 speed = Math.log(((leftEncoderValue + rightEncoderValue) / 2) + (distance));
			 }
			 else
			 {
				 speed = Math.log(-((leftEncoderValue + rightEncoderValue) / 2) + (distance));//distance + 1
			 }
					 
			 if(speed < 0.10)
			 {
				 ActuatorConfig.getInstance().getDrivetrain().stop();
				 System.err.println("Stopping Drivetrain");
				 break;
			 }
			 else if(speed > 0.25)
			 {
				 speed = 0.25;
			 }
				
			if(isReversed)
			{
				speed = speed * -1;
			}
			
			 
			startYaw = SensorConfig.getInstance().getNavX().getRawYaw();
			double leftCorrect = 0;
			double rightCorrect = 0;
			
			if (endYaw > (startYaw)) 
			{
			
				//drivetrain.setSpeed((leftJoystick.getYAxis() / 2), (rightJoystick.getYAxis() / 2) + 0.2);//Add Gyro 
				System.out.println("Veering Right Telop");
				rightCorrect = 0.2;
			}
			else if (endYaw < (startYaw)) 
			{	
				//drivetrain.setSpeed((leftJoystick.getYAxis() / 2) + 0.2, (rightJoystick.getYAxis() / 2));//Add Gyro 
				System.out.println("Veering Left Telop");
				leftCorrect = 0.2;
			}
			else
			{
				leftCorrect = 0;
				rightCorrect = 0;
			}
			
		//	drivetrain.setSpeed((leftJoystick.getYAxis() / 2) + leftCorrect, (rightJoystick.getYAxis() / 2) + rightCorrect);//Add Gyro 
			ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed + leftCorrect, speed + rightCorrect);
			endYaw = SensorConfig.getInstance().getNavX().getRawYaw();
		}	
	}
	
	public void makeHeadingGreatAgain(double speed)
	{
		
		
	}
	
	/**
	 * Go forward number of rotations (1 Rot ~ 4pi ~ 1ft)
	 * @param distance
	 */
	public void goForward(double distance, double speed)
	{	
		move(distance, speed, false);	
	}
	
	public void goBackward(int distance, double speed)
	{
		move(distance, speed, true);
	}
	
	public void moveGyro(double distance, double speed, boolean isReversed)
	{
		rightJoystick = new HBJoystick(0);
		leftJoystick = new HBJoystick(1);
		
		NavX navx = SensorConfig.getInstance().getNavX();
		
		boolean isRightComplete = false;
		boolean isLeftComplete = false;
		double distanceRight;
		double distanceLeft;	
		double rightEncoderValue = ActuatorConfig.getInstance().getRightEncoder().getEncPosition() * (-0.000122);
		double leftEncoderValue =  ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (0.000122);
		if(isReversed) {
			speed = speed * -1;
			distanceRight  = rightEncoderValue - distance;
			distanceLeft = leftEncoderValue - distance;
		} else {
			distanceRight  = rightEncoderValue + distance;
			distanceLeft = leftEncoderValue + distance;
		}
		
		double currentYaw;
		double startYaw = navx.getRawYaw();
		SmartDashboard.putNumber("Start Yaw: ", startYaw);
		ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, speed);
		SmartDashboard.putNumber("Right Distance To ", distanceRight);
		SmartDashboard.putNumber("Left Distance To ", distanceLeft);
		SmartDashboard.putNumber("Start Left Enoder Value ", leftEncoderValue);
		SmartDashboard.putNumber("Start Right Encoder Value", rightEncoderValue);
		
		while(!isRightComplete && !isLeftComplete)
		{
			 rightEncoderValue = ActuatorConfig.getInstance().getRightEncoder().getEncPosition() * (-0.000122);
			 leftEncoderValue = ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (0.000122);
			 
			 SmartDashboard.putNumber("Left Enoder Value ", leftEncoderValue);
			 SmartDashboard.putNumber("Right Encoder Value", rightEncoderValue);
			 
			 // This Kill Switch will only work once Teleop begins and Joysticks start working
			 // This is a safety in case loop cannot complete for some reason while running Auton.
			 // To test: Run Auton and hit disable before loop completes. Then start teleop and press kill switch buttons 
			if(rightJoystick.getRawButton(1) || leftJoystick.getRawButton(1))
			{
				System.out.println("Kill Switch");
				break;
		  	}
			if(isReversed)
			{
				if(rightEncoderValue <= distanceRight)
				{
					isLeftComplete = true;
				//	ActuatorConfig.getInstance().getDrivetrain().getLeftMotor().stop();
					System.out.println("Left Finished First");
				}
				currentYaw = navx.getRawYaw();
				SmartDashboard.putNumber("Current Yaw ", currentYaw);
				if (currentYaw > (startYaw + 1)) 
				{
					// Veering left, so slow down right
					//System.out.println("Veering left");
					ActuatorConfig.getInstance().getDrivetrain().setSpeed((speed + .30), speed);	
				}
				else if (currentYaw < (startYaw + 1)) 
				{	
					// Veering right, so slow down left
					//System.out.println("Veering right");
					ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, (speed + .30));
				}
			}
			else
			{
				if(rightEncoderValue >= distanceRight)
				{
					isLeftComplete = true;
				//	ActuatorConfig.getInstance().getDrivetrain().getLeftMotor().stop();
					System.out.println("Left Finished First");
				}
				currentYaw = navx.getRawYaw();
				SmartDashboard.putNumber("Current Yaw ", currentYaw);
				if (currentYaw > (startYaw + 1)) 
				{
					// Veering left, so slow down right
					//System.out.println("Veering left");
					ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, (speed - .30));	
				}
				else if (currentYaw < (startYaw + 1)) 
				{	
					// Veering right, so slow down left
					//System.out.println("Veering right");
					ActuatorConfig.getInstance().getDrivetrain().setSpeed((speed - .30),speed );
				}
			}
		}	
		ActuatorConfig.getInstance().getDrivetrain().stop();
	}
	public void goForwardGyro(double distance, double speed)
	{
		moveGyro(distance, speed, false);
	}
	
	public void goBackwardsGyro(double distance, double speed)
	{
		moveGyro(distance, speed, true);
	}
}
