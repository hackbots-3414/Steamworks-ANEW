package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class AutonBlueAllianceCenterGearShoot extends AutoBase
{
	public void doAuto()
	{
		if (RobotStatus.isAuto())
		{
			System.out.println("Center Gear Delivery and Shoot on Red Alliance");
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(9, 0.35);
			ActuatorConfig.getInstance().getGearManipulator().set(Value.kForward);
			ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(2, 0.35);
			ActuatorConfig.getInstance().getGearManipulator().set(Value.kReverse);
			ActuatorConfig.getInstance().getShooter().setSpeed(0.9);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.35, 135);
			ActuatorConfig.getInstance().getAgitator().setSpeed(0.4);
			
		}
	}

}
