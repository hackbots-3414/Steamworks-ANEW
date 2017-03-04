package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.sensors.NavX;
import org.hackbots.sensors.SensorConfig;

import edu.wpi.first.wpilibj.Timer;

public abstract class AutoBase
{
	 protected Drivetrain driveTrain = ActuatorConfig.getInstance().getDrivetrain();
		protected Timer timer = new Timer();
		
		protected NavX navX = SensorConfig.getInstance().getNavX();
		
		public abstract void doAuto();
}
