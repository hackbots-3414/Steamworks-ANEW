package org.hackbots.sensors;

import com.ctre.CANTalon;

public class Encoder
{
	private CANTalon talon;
	
	public Encoder(CANTalon talon)
	{
		this.talon = talon;
	}
	
	public double getCount() 
	{
		return talon.get();
	}

	public void enable() 
	{
		talon.enableControl();
	}

	public void disable() 
	{
		talon.disableControl();
	}

	public void resetCount() 
	{
		talon.reset();
	}
}
