package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.sensors.SensorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class AutonBlueAllianceLeftStartShoot extends AutoBase {
	  	
		protected void vanillaAuto()
		{
			
				System.out.println("Left Gear Delivery (Center Start)");
//				ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(2, 0.35);
//				ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.5, 90);
//				ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(4, 0.35);
//				ActuatorConfig.getInstance().getDrivetrain().turnRight(0.5, 90);
//				ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(8, 0.35);
//				ActuatorConfig.getInstance().getDrivetrain().turnRight(0.5, 120);
//				ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(2, 0.35);
//				ActuatorConfig.getInstance().getGearManipulator().set(Value.kForward);
//				ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(2, 0.35);
				
				ActuatorConfig.getInstance().getShooter().setSpeed(0.87);
				ActuatorConfig.getInstance().getAgitator().setSpeed(-.20);
				SensorConfig.getInstance().getTimer().waitTimeInMillis(7000);
				ActuatorConfig.getInstance().getShooter().setSpeed(0);
				ActuatorConfig.getInstance().getAgitator().setSpeed(0);
				SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
			//	ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(1, 0.35);
			//	SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
				ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.5, 90);
				SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
				ActuatorConfig.getInstance().getDrivetrain().goForward(7, 0.35);
			


		}

		protected void redShoot() 
		{
			System.out.println("Left Gear Delivery (Center Start)");
			ActuatorConfig.getInstance().getShooter().setSpeed(0.87);
			ActuatorConfig.getInstance().getAgitator().setSpeed(-.20);
			SensorConfig.getInstance().getTimer().waitTimeInMillis(7000);
			ActuatorConfig.getInstance().getShooter().setSpeed(0);
			ActuatorConfig.getInstance().getAgitator().setSpeed(0);
			SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
		//	ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(1, 0.35);
		//	SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.5, 90);
			SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
			ActuatorConfig.getInstance().getDrivetrain().goForward(7, 0.35);		
		}

		protected void blueShoot()
		{
			System.out.println("Left Gear Delivery (Center Start)");
			ActuatorConfig.getInstance().getShooter().setSpeed(0.87);
			ActuatorConfig.getInstance().getAgitator().setSpeed(-.20);
			SensorConfig.getInstance().getTimer().waitTimeInMillis(7000);
			ActuatorConfig.getInstance().getShooter().setSpeed(0);
			ActuatorConfig.getInstance().getAgitator().setSpeed(0);
			SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
		//	ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(1, 0.35);
		//	SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.5, 90);
			SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
			ActuatorConfig.getInstance().getDrivetrain().goForward(7, 0.35);
		}
		
}
