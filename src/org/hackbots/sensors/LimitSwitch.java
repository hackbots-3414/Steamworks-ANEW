package org.hackbots.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch
{
	private DigitalInput limitSwitch;

	public LimitSwitch(DigitalInput limitSwitch)
	{
		this.limitSwitch = limitSwitch;
	}

	public boolean isHit()
	{
		return limitSwitch.get();
	}
}
