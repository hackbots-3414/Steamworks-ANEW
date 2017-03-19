package org.hackbots.acutator;

import org.hackbots.sensors.SensorConfig;
import org.hackbots.sensors.NavX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain implements IDriveTrain
{
	private TripleMotor rightMotor;
	private TripleMotor leftMotor;
	
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

	public void turn(double speed, double angle) 
	{
		
	}


	public void turnRight(double speed, double angle)
	{
		NavX navX = SensorConfig.getInstance().getNavX();
		
		double currentYaw = navX.getYaw();
		double endAngle = currentYaw + angle;
		
		ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, -speed);
		
	/*	if(endAngle > 360)
		{
			
		}*/
		System.out.println("Curent: " + navX.getYaw());
		System.out.println("End: " + endAngle);
		
		while(navX.getYaw() < endAngle){}
		
		ActuatorConfig.getInstance().getDrivetrain().stop();
	}
	
	public void turnLeft(double speed, double angle)
	{	
		NavX navX = SensorConfig.getInstance().getNavX();
		
		double currentYaw = navX.getYaw();
		double endAngle = currentYaw - angle;
		
		ActuatorConfig.getInstance().getDrivetrain().setSpeed(-speed, speed);
		
	/*	if(endAngle > 360)
		{
			
		}*/
		
		while(navX.getYaw() > endAngle){}
		
		ActuatorConfig.getInstance().getDrivetrain().stop();
	}
	
	private void move(int distance, boolean isReversed)
	{
		double rightEncoderValue = ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.000122);
		double leftEncoderValue =  ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.000122);
		
		double speed = 0;
		
		while(true)
		{		
			 rightEncoderValue = ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.000122);
			 leftEncoderValue = ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.000122);
			 
			 if(isReversed)
			 {
				 speed = Math.log(((leftEncoderValue + rightEncoderValue) / 2) + (distance  + 1));
			 }
			 else
			 {
				 speed = Math.log(-((leftEncoderValue + rightEncoderValue) / 2) + (distance  + 1));
			 }
			
			 
			 if(speed < 0.29)
			 {
				 ActuatorConfig.getInstance().getDrivetrain().stop();
				 break;
			 }
			 else
			 {
				 speed = speed * 0.35;//0.25
			 }
				
			if(isReversed)
			{
				speed = speed * -1;
			}
			
			 ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, speed);
		}	
	}
	
	public void goForward(int distance)
	{	
		move(distance, false);	
	}
	
	public void goBackward(int distance)
	{
		move(distance, true);
	}
	
	public void goForwardGyro(double speed, int distance)
	{
		boolean isRightComplete = false;
		boolean isLeftComplete = false;
		
		double rightEncoderValue = ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.000122);
		double leftEncoderValue =  ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.000122);

		double distanceRight  = distance + (-rightEncoderValue);
		double distanceLeft = distance + (-leftEncoderValue);
		
		NavX navx = SensorConfig.getInstance().getNavX();
		
		float currentYaw;
		float startYaw = navx.getYaw();
		SmartDashboard.putNumber("Start Yaw ", startYaw);
		
		System.out.println("Right Encoeifarghagdf.gh.: " + rightEncoderValue);
		System.out.println("Left Encoeifarghagdf.gh.: " +  (-leftEncoderValue));
		
		ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, speed);
		
		 SmartDashboard.putNumber("Right Distance To ", distanceRight);
		 SmartDashboard.putNumber("Left Distance To ", distanceLeft);
		
		while(!isRightComplete && !isLeftComplete)
		{
			 rightEncoderValue = ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.000122);
			 leftEncoderValue = ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.000122);
			 
			 
			 SmartDashboard.putNumber("Left Enoder Value ", leftEncoderValue);
			 SmartDashboard.putNumber("Right Encoder Value", rightEncoderValue);
			
			if(rightEncoderValue >= (distanceRight - 0.19))
			{
				//isRightComplete = true;
			//	ActuatorConfig.getInstance().getDrivetrain().getRightMotor().stop();
				System.out.println("Right Stoped");
			}
			
			if(leftEncoderValue >= (distanceLeft - 0.19))
			{
				////isLeftComplete = true;
			//	ActuatorConfig.getInstance().getDrivetrain().getLeftMotor().stop();
				System.out.println("Left Stoped");
			}
			currentYaw = navx.getYaw();
			SmartDashboard.putNumber("Current Yaw ", currentYaw);
			if (currentYaw > (startYaw + 1))
				ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, (speed - 1));
			else if (currentYaw < (startYaw + 1))
				ActuatorConfig.getInstance().getDrivetrain().setSpeed((speed-1), speed);
		}	
		ActuatorConfig.getInstance().getDrivetrain().stop();
	}
}
