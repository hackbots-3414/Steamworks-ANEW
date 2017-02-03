package org.hackbots.acutator;

import org.hackbots.util.RotationalDirection;

public abstract class MotorBase 
{
	protected boolean isReversed = false;//Is the motor reversed
	protected RotationalDirection direction = RotationalDirection.NONE;//The direction the motor is spinning
	
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
	public RotationalDirection getDirection()
	{
		return direction;
	}
	
	/**
	 * Returns whether or not the motor is revered
	 * @return isReveresed
	 */
	public boolean isReversed()
	{
		return isReversed;
	}
	
	/**
	 * Reverses the motor if set to true
	 * @param reverse
	 */
	public abstract void setMotorReveresed(boolean reverse);
}
