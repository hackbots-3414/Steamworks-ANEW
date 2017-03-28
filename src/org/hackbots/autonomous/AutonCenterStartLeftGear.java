package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class AutonCenterStartLeftGear extends AutoBase
{
	  
		
	public void doAuto()
	{
		if (RobotStatus.isAuto())
		{
			System.out.println("Left Gear Delivery (Center Start)");
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(1, 0.35);
			ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.5, 90);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(1, 0.35);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.5, 90);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(1, 0.35);
			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.5, 120);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(1, 0.35);
			ActuatorConfig.getInstance().getGearManipulator().set(Value.kForward);
			ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(1, 0.35);
		}
	}

}
