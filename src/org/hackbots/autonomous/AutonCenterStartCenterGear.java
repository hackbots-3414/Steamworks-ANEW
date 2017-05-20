package org.hackbots.autonomous;

import org.hackbots.sensors.SensorConfig;
import org.hackbots.acutator.ActuatorConfig;
import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class AutonCenterStartCenterGear extends AutoBase
{
	protected void vanillaAuto()
	{
		System.out.println("Auton Center Gear Delivery (CENTER START)");
		
	//	SmartDashboard.putNumber("Left Encoder", ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.00013));//
	//	SmartDashboard.putNumber("Right Encoder", ActuatorConfig.getInstance().getRightEncoder().getEncPosition()  * (0.00013));

		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(5.43, 0.35);
		System.out.println("Pneuamtiing");
		SensorConfig.getInstance().getTimer().waitTimeInMillis(250);
		ActuatorConfig.getInstance().getGearManipulator().set(Value.kForward);
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
		ActuatorConfig.getInstance().getDrivetrain().goBackwardsGyro(1, 0.35);
		
		ActuatorConfig.getInstance().getGearManipulator().set(Value.kReverse);
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);
	}


	protected void blueShoot() 
	{
		ActuatorConfig.getInstance().getShooter().setSpeed(0.80);//81
		ActuatorConfig.getInstance().getDrivetrain().turnLeft(0.5, 96);//95, 92(gold)//99
		shoot();
	}
	
	protected void redShoot()
	{
		ActuatorConfig.getInstance().getShooter().setSpeed(0.80);//81
		ActuatorConfig.getInstance().getDrivetrain().turnRight(0.5, 90);//98(gold apparently)//100 is wayyyyy to much
		shoot();
	}
	
	protected void shoot()
	{
		SensorConfig.getInstance().getTimer().waitTimeInMillis(500);//Wait
		
		//Start Shooter
		//ActuatorConfig.getInstance().getShooter().setSpeed(0.90);
		ActuatorConfig.getInstance().getDrivetrain().goForwardGyro(4.43, 0.35);//4.17 //4.25 //4.27//4.36//4.37
		ActuatorConfig.getInstance().getAgitator().setSpeed(-.25);
		
		SensorConfig.getInstance().getTimer().waitTimeInMillis(6500);//More wair
		
		//Stop the boy
		ActuatorConfig.getInstance().getShooter().setSpeed(0);
		ActuatorConfig.getInstance().getAgitator().setSpeed(0);
	//	ActuatorConfig.getInstance().getGearManipulator().set(Value.kReverse);
	}

}
