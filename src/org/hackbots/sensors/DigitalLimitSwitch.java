package org.hackbots.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

	public class DigitalLimitSwitch
	{
		private DigitalInput limitSwitchOne;

		public DigitalLimitSwitch(DigitalInput _limitSwitchOne)
		{
			limitSwitchOne = _limitSwitchOne;
		}

		public boolean isHit()
		{
			return limitSwitchOne.get();
		}

	}
