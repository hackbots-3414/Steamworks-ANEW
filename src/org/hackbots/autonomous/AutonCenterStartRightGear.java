package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

public class AutonCenterStartRightGear extends AutoBase
{
	public void doAuto()
	{
		if (RobotStatus.isAuto())
		{
			System.out.println("Right Gear Delivery (Center Start)");
			ActuatorConfig.getInstance().getDrivetrain().goForward(2, 0.35);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.5, 90);
			ActuatorConfig.getInstance().getDrivetrain().goForward(4, 0.35);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.5, 90);
			ActuatorConfig.getInstance().getDrivetrain().goForward(8, 0.35);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.5, 120);
			ActuatorConfig.getInstance().getDrivetrain().goForward(2, 0.35);
		}
	}

}
