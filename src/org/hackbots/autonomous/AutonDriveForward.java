package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.sensors.NavX;
import org.hackbots.sensors.SensorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.Timer;

public class AutonDriveForward implements IAuton
{
    private Drivetrain driveTrain = ActuatorConfig.getInstance().getDrivetrain();
	private Timer timer = new Timer();
	
	private NavX navX = SensorConfig.getInstance().getNavX();
	
	public void doAuto()
	{
		if (RobotStatus.isAuto())
		{
			driveTrain.setSpeed(0.4);
			
			timer.delay(1.8);
			
			driveTrain.stop();
		}
	}
}

