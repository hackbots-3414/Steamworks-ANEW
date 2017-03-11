package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.sensors.NavX;
import org.hackbots.sensors.SensorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.Timer;

public class AutonDriveForward extends AutoBase
{

	public void doAuto()
	{
		if (RobotStatus.isAuto())
		{
//			driveTrain.setSpeed(0.4);
//			
			ActuatorConfig.getInstance().getDrivetrain().goForward(0.4, 0);
//			
//			
//			driveTrain.stop();
		}
	}
}

