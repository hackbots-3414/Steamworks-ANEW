package org.hackbots.acutator;

import org.hackbots.util.RotationalDirection;

import com.ctre.CANTalon;

public class Motor extends MotorBase
{
	private CANTalon talon;//The talon that controls the motor

	/**
	 * Creates a motor
	 * @param talon
	 */
	public Motor(CANTalon talon)
	{
		this.talon = talon;
		direction = RotationalDirection.NONE;
	}
		
	public void setSpeed(double speed)
	{
		if(isReversed)
		{
			speed = 0 - speed;
		}
				
		if(speed < 0)
		{
			direction = RotationalDirection.COUNTERCLOCKWISE;
		}
		else if(speed > 0)
		{
			direction = RotationalDirection.CLOCKWISE;
		}
		else
		{
			direction = RotationalDirection.NONE;
		}
		
		talon.set(speed);
	}

	public void stop() 
	{
		setSpeed(0);		
	}

	public RotationalDirection getDirection() 
	{
		return direction;
	}

	public void setDirection(RotationalDirection direction)
	{
		this.direction = direction;
	}	
	
	public void setMotorReveresed(boolean reverse)
	{
		this.isReversed = reverse;
	}
}
