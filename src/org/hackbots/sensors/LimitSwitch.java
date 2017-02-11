package org.hackbots.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

	public class LimitSwitch
	{
		private DigitalInput limitSwitchOne;

		public LimitSwitch(DigitalInput _limitSwitchOne)
		{
			limitSwitchOne = _limitSwitchOne;
		}

		public boolean isHit()
		{
			return limitSwitchOne.get();
		}

	}
