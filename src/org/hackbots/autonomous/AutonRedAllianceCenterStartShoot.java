package org.hackbots.autonomous;

	import org.hackbots.acutator.ActuatorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class AutonRedAllianceCenterStartShoot extends AutoBase 
{

		protected void vanillaAuto()
		{
			
				System.out.println("Center Gear Delivery and Shoot on Red Alliance");
				ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(5, 0.35);
				ActuatorConfig.getInstance().getGearManipulator().set(Value.kForward);
				ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(2, 0.35);
				ActuatorConfig.getInstance().getGearManipulator().set(Value.kReverse);
				ActuatorConfig.getInstance().getShooter().setSpeed(0.9);
				ActuatorConfig.getInstance().getDrivetrain().turnRight(0.35, 135);
				ActuatorConfig.getInstance().getAgitator().setSpeed(0.4);
				
			
		}

		@Override
		protected void redShoot() {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void blueShoot() {
			// TODO Auto-generated method stub
			
		}

}
