package org.hackbots.acutator;

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
		
	}
	public void goBackward(double speed, int distance)
	{
		
		
	}
}
