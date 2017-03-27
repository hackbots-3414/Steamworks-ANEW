package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

public class AutonDriveForward extends AutoBase
{

	public void doAuto()
	{
		
		//ActuatorConfig.getInstance().getDrivetrain().getRightMotor().setMotorReveresed(false);
		//ActuatorConfig.getInstance().getDrivetrain().getLeftMotor().setMotorReveresed(true);
		
		if(RobotStatus.isAuto())
		{
			System.out.println("Driving Forward Auton");
			ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(2, 0.35);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.5, 90);
			ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(2, 0.35);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.5, 90);
			ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(2, 0.35);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(.5, 90);
			ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(2, 0.35);
			//ActuatorConfig.getInstance().getDrivetrain().turnLeft(.5, 90);
			//ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(8, 0.35);
		}
		//ActuatorConfig.getInstance().getDrivetrain().getRightMotor().setMotorReveresed(true);
		//ActuatorConfig.getInstance().getDrivetrain().getLeftMotor().setMotorReveresed(false);
	}
}

