package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.sensors.NavX;
import org.hackbots.sensors.SensorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.Timer;

public abstract class AutoBase
{
	 	protected Drivetrain driveTrain = ActuatorConfig.getInstance().getDrivetrain();
		protected Timer timer = new Timer();
		
		protected NavX navX = SensorConfig.getInstance().getNavX();
		
		public void doAuto(boolean shoot, boolean redAlliance)
		{
			if(RobotStatus.isAuto())		
			{
				vanillaAuto();
				
				if(shoot && redAlliance)
				{
					redShoot();
				}
				else if(shoot)
				{
					blueShoot();
				}
			}	
		}
		
		protected abstract void vanillaAuto();
		
		//protected abstract void shoot();
			
		protected abstract void redShoot();
		
		protected abstract void blueShoot();
}
