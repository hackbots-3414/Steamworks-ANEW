package org.hackbots.autonomous;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.sensors.SensorConfig;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class AutonLeftStartLeftGear extends AutoBase 
{
	protected void vanillaAuto()
	{
		
			System.out.println("Auton Left Gear Delivery (LEFT START)");
			
		//	SmartDashboard.putNumber("Left Encoder", ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.00013));//
		//	SmartDashboard.putNumber("Right Encoder", ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.00013));

			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(6.57, 0.35);//9,0.35 //6.48
			ActuatorConfig.getInstance().getDrivetrain().turnRight(0.5, 43);
			ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(1.1, 0.35);//1.6
			SensorConfig.getInstance().getTimer().waitTimeInMillis(1000);
			ActuatorConfig.getInstance().getGearManipulator().set(Value.kForward);
			SensorConfig.getInstance().getTimer().waitTimeInMillis(2000);
			ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(1, 0.35);
			ActuatorConfig.getInstance().getGearManipulator().set(Value.kReverse);
		
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
