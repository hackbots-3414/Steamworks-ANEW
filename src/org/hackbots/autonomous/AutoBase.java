package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.sensors.NavX;
import org.hackbots.sensors.SensorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.Timer;

public abstract class AutoBase implements Runnable
{
	 	protected Drivetrain driveTrain = ActuatorConfig.getInstance().getDrivetrain();
		protected Timer timer = new Timer();
		
		protected NavX navX = SensorConfig.getInstance().getNavX();
		
		private Thread autonThread = new Thread(this);
		
		private boolean shoot;
		private Alliance alliance;
		
		public void doAuto(boolean shoot, Alliance alliance)
		{
			this.shoot = shoot;
			this.alliance = alliance;
			
			autonThread.start();
		}
		
		protected abstract void vanillaAuto();
			
		protected abstract void redShoot();
		
		protected abstract void blueShoot();
		
		public void run()
		{
			System.out.println("Running Auton Thread----------------------------------------");
			//ActuatorConfig.getInstance().getCompressor().stop();
			
			if(RobotStatus.isAuto())		
			{
				vanillaAuto();
					
				if(shoot)
				{
					switch(alliance)
					{
						case RED:
							redShoot();
							break;
						
						case BLUE:
							blueShoot();
							break;
					}
				}	
			}	
		}
}
