package org.hackbots.acutator;

import org.hackbots.util.RotationalDirection;

public class TripleMotor extends MotorBase
{
	private Motor motorOne;
	private Motor motorTwo;
	private Motor motorThree;
	
	private boolean isSafeConfig = false;
	
	/**
	 * Creates a triple motor
	 * @param motorOne
	 * @param motorTwo
	 * @param motorThree
	 */
	public TripleMotor(Motor motorOne, Motor motorTwo, Motor MotorThree)
	{
		this.motorOne = motorOne;
		this.motorTwo = motorTwo;
		this.motorThree = MotorThree;
		
		isSafeConfig();
	}
	
	public void setSpeed(double speed)
	{
		if(isSafeConfig())
		{
		  //motorOne.setSpeed(speed);
			motorTwo.setSpeed(speed);
			//motorThree.setSpeed(speed);
		}	
	}
	
	public void stop() 
	{
		motorOne.stop();
		motorTwo.stop();
		motorThree.stop();
	}
	
	public void setDirection(RotationalDirection direction) 
	{
		motorOne.setDirection(direction);
		motorTwo.setDirection(direction);
		motorThree.setDirection(direction);
	}
	
	/**
	 * Checks to make sure that all three motors will be spinning in the same direction. Otherwise, the motors will not run in order to prevent damage
	 * @return isConfigSafe
	 */
	private boolean isSafeConfig()
	{
		if(motorOne.isReversed() && motorOne.isReversed() && motorThree.isReversed())
		{
			return isSafeConfig = true;
		}	
		else if(!motorOne.isReversed() && !motorOne.isReversed() && !motorThree.isReversed())
		{
			return isSafeConfig = true;
		}
		else
		{
			System.err.println("Double Motor Configuration is unsafe: Locking");
			return isSafeConfig = false;
		}
	}
	
	public void setMotorReveresed(boolean reverse)
	{
		this.isReversed = reverse;
		
		motorOne.setMotorReveresed(reverse);
		motorTwo.setMotorReveresed(reverse);
		motorThree.setMotorReveresed(reverse);
	}
	
	public Motor getMotorOne()
	{
		return motorOne;
	}
	
	public Motor getMotorTwo()
	{
		return motorTwo;
	}
	
	public Motor getMotorThree()
	{
		return motorThree;
	}
}
