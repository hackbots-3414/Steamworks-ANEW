package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

public class AutonRightStartCenterGear extends AutoBase
{
	public void doAuto()
	{
		if (RobotStatus.isAuto())
		{
			System.out.println("Center Gear Delivery (Right Start)");
			ActuatorConfig.getInstance().getDrivetrain().goForward(2);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.5, 90);
			ActuatorConfig.getInstance().getDrivetrain().goForward(4);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.5, 90);
			ActuatorConfig.getInstance().getDrivetrain().goForward(4);
		}
	}

}
