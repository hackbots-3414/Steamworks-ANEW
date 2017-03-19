package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonRightStartRightGear extends AutoBase
{
	public void doAuto()
	{
		if (RobotStatus.isAuto())
		{
			System.out.println("Auton Right Gear Delivery (RIGHT START");
			
		//	SmartDashboard.putNumber("Left Encoder", ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.00013));//
		//	SmartDashboard.putNumber("Right Encoder", ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.00013));

			ActuatorConfig.getInstance().getDrivetrain().goForward(9);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.5, 120);
			ActuatorConfig.getInstance().getDrivetrain().goForward(2);

		}
	}

}
