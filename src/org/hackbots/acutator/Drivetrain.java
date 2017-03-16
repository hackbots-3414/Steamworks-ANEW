package org.hackbots.acutator;

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
		
	}
	public void turnLeft(double speed, double angle)
	{
		
	}
	
	public void goForward(double speed, int distance)
	{
		boolean isRightComplete = false;
		boolean isLeftComplete = false;
		
		double rightEncoderValue = ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.000122);
		double leftEncoderValue =  ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.000122);
		
		double distanceRight  = distance + (-rightEncoderValue);
		double distanceLeft = distance + (-leftEncoderValue);
		
		System.out.println("Right Encoeifarghagdf.gh.: " + rightEncoderValue);
		System.out.println("Left Encoeifarghagdf.gh.: " +  (-leftEncoderValue));
		
		ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed * 0.85, speed);
		
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
				isRightComplete = true;
			//	ActuatorConfig.getInstance().getDrivetrain().getRightMotor().stop();
				System.out.println("Right Stoped");
			}
			
			if(leftEncoderValue >= (distanceLeft - 0.19))
			{
				isLeftComplete = true;
			//	ActuatorConfig.getInstance().getDrivetrain().getLeftMotor().stop();
				System.out.println("Left Stoped");
			}
		}	
		ActuatorConfig.getInstance().getDrivetrain().stop();
	}
	
	public void goBackward(double speed, int distance)
	{
		boolean isRightComplete = false;
		boolean isLeftComplete = false;
		
		double rightEncoderValue = ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.000122);
		double leftEncoderValue =  ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.000122);
		
		double distanceRight  = distance + (-rightEncoderValue);
		double distanceLeft = distance + (-leftEncoderValue);
		
		System.out.println("Right Encoeifarghagdf.gh.: " + rightEncoderValue);
		System.out.println("Left Encoeifarghagdf.gh.: " +  (-leftEncoderValue));
		
		speed = -speed;
		distance = -distance;
		
		ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed);
		
		 SmartDashboard.putNumber("Right Distance To ", distanceRight);
		 SmartDashboard.putNumber("Left Distance To ", distanceLeft);
		
		while(!isRightComplete || !isLeftComplete)
			
		{
			 rightEncoderValue = ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.000122);
			 leftEncoderValue = ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.000122);
			 
			 
			 SmartDashboard.putNumber("Left Enoder Value ", leftEncoderValue);
			 SmartDashboard.putNumber("Right Encoder Value", rightEncoderValue);
			
			if(rightEncoderValue <= (distanceRight - 0.19))
			{
				isRightComplete = true;
				ActuatorConfig.getInstance().getDrivetrain().getRightMotor().stop();
				System.out.println("Right Stoped");
			}
			
			if(leftEncoderValue <= (distanceLeft - 0.19))
			{
				isLeftComplete = true;
				ActuatorConfig.getInstance().getDrivetrain().getLeftMotor().stop();
				System.out.println("Left Stoped");
			}
		}	
	}
}
