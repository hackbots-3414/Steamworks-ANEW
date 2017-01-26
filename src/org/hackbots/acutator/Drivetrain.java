package org.hackbots.acutator;

public class Drivetrain implements IDriveTrain
{
	private DoubleMotor rightMotor;
	private DoubleMotor leftMotor;
	
	public Drivetrain(DoubleMotor rightMotor, DoubleMotor leftMotor)
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
}
