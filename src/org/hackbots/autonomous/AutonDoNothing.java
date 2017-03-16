package org.hackbots.autonomous;

import org.usfirst.frc.team3414.robot.RobotStatus;

public class AutonDoNothing extends AutoBase
{
	public void doAuto()
	{
		if (RobotStatus.isAuto())
		{
			System.out.println("I dont wanna! You told us to do nothing :3  - Kayleigh, Hannah, Tyler");

		}
	}
}