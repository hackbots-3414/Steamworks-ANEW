package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

public class AutonDriveForward extends AutoBase
{

	public void doAuto()
	{
		if(RobotStatus.isAuto())
		{
			System.out.println("Driving Forward Auton");  
			ActuatorConfig.getInstance().getDrivetrain().goForward(3, 0.35);
			//ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.5, 90);
		}
	}
}

