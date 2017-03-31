package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class AutonCenterStartCenterGear extends AutoBase
{

	public void doAuto()
	{
		if (RobotStatus.isAuto())
			
		{
			System.out.println("Auton Center Gear Delivery (CENTER START");
			
		//	SmartDashboard.putNumber("Left Encoder", ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.00013));//
		//	SmartDashboard.putNumber("Right Encoder", ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.00013));

			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(4.3, 0.25);
			System.out.println("Pneuamtiing");
			ActuatorConfig.getInstance().getDrivetrain().stop();
			ActuatorConfig.getInstance().getGearManipulator().set(Value.kForward);
			ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(1, 0.35);
			ActuatorConfig.getInstance().getDrivetrain().stop();
			ActuatorConfig.getInstance().getGearManipulator().set(Value.kReverse);
			
			

		}
	}


}
