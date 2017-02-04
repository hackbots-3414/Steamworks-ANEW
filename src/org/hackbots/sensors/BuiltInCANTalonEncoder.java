package org.hackbots.sensors;

import com.ctre.CANTalon;

public class BuiltInCANTalonEncoder {
	CANTalon canMotor;
	
	public BuiltInCANTalonEncoder(CANTalon canMotor)
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
