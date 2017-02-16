package org.hackbots.autonomous;

import org.usfirst.frc.team3414.robot.RobotStatus;

public class AutonDoNothing implements IAuton
{
	public void doAuto()
	{
		while (RobotStatus.isAuto())
		{
			System.out.println("I dont wanna! You told us to do nothing :3");

		}
	}
}