package org.hackbots.autonomous;

import org.usfirst.frc.team3414.robot.RobotStatus;

public class AutonCenterStartCenterGear extends AutoBase
{

	public void doAuto()
	{
		while (RobotStatus.isAuto())
		{
			driveTrain.setSpeed(0.4);
			
			timer.delay(1.6);
			
			driveTrain.stop();
		}
	}


}
