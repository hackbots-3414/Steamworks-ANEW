package org.hackbots.acutator;

import org.hackbots.util.RotationalDirection;

public abstract class MotorBase 
{
	/**
	 * Sets the speed of the motor
	 * @param speed
	 */
	public abstract void setSpeed(double speed);
	
	/**
	 * Stops the motor
	 */
	public abstract void stop();
	
	/**
	 * Sets the direction of the motor
	 * @param direction
	 */
	public abstract void setDirection(RotationalDirection direction);
	
	/**
	 * Gets the rotational direction of the motor
	 * @return rotational direction
	 */
	public abstract RotationalDirection getDirection();
}
