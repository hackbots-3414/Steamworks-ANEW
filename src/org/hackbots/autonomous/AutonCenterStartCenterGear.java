package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

public class AutonCenterStartCenterGear extends AutoBase
{

	public void doAuto()
	{
		if (RobotStatus.isAuto())
		{
			System.out.println("Auton Center Gear Delivery (CENTER START");
			
		//	SmartDashboard.putNumber("Left Encoder", ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.00013));//
		//	SmartDashboard.putNumber("Right Encoder", ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.00013));

			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(7, 0.35);
	
		}
	}


}
