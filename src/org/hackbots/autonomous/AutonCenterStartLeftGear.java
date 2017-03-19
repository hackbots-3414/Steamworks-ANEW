package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.sensors.NavX;
import org.hackbots.sensors.SensorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.Timer;

public class AutonCenterStartLeftGear extends AutoBase
{
	  
		
	public void doAuto()
	{
		if (RobotStatus.isAuto())
		{
			System.out.println("Left Gear Delivery (Center Start)");
			ActuatorConfig.getInstance().getDrivetrain().goForward(2);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.5, 90);
			ActuatorConfig.getInstance().getDrivetrain().goForward(4);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.5, 90);
			ActuatorConfig.getInstance().getDrivetrain().goForward(8);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.5, 120);
			ActuatorConfig.getInstance().getDrivetrain().goForward(2);
		}
	}

}
