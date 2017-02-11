package org.hackbots.sensors;

import com.ctre.CANTalon;

public class Encoder
{
	private CANTalon canMotor;
	
	public Encoder(CANTalon canMotor)
	{
		this.canMotor = canMotor;
	}
	
	public double getCount() 
	{
		return canMotor.get();
	}

	public void enable() 
	{
		canMotor.enableControl();
	}

	public void disable() 
	{
		canMotor.disableControl();
	}

	public void resetCount() 
	{
		canMotor.reset();
	}

}
