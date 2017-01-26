package org.hackbots.acutator;

public interface IDriveTrain
{
	/**
	 * Sets the overall speed for the drivetrain
	 * @param speed
	 */
	public void setSpeed(double speed);
	
	/**
	 * Sets the speed of the drivetrain for each respective sides
	 * @param leftSpeed
	 * @param rightSpeed
	 */
	public void setSpeed(double leftSpeed, double rightSpeed);
	
	/**
	 * Stops the drivetrain
	 */
	public void stop();
}
