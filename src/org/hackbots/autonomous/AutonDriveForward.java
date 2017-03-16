package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.sensors.NavX;
import org.hackbots.sensors.SensorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonDriveForward extends AutoBase
{

	public void doAuto()
	{
		//ActuatorConfig.getInstance().getDrivetrain().setSpeed(0.2);
		
		if(RobotStatus.isAuto())
		{
			System.out.println("Driving Forward Auton");

			ActuatorConfig.getInstance().getDrivetrain().goForward(0.4,3);
		//	ActuatorConfig.getInstance().getDrivetrain().goBackward(0.2,1);
			//ActuatorConfig.getInstance().getDrivetrain().setSpeed(1);
			
		}
	}
}

