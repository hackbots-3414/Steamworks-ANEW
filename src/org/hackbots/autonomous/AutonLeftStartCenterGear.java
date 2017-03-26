package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

public class AutonLeftStartCenterGear extends AutoBase
{
	public void doAuto()
	{
		if (RobotStatus.isAuto())
		{
			System.out.println("Center Gear Delivery (Left Start)");
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(2, 0.35);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.5, 90);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(2, 0.35);//switch 2 to 4
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.5, 90);
			ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(2, 0.35);//switch to forwards and 2 to 4
		}
	}

}
