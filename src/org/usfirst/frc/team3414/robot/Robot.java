     
package org.usfirst.frc.team3414.robot;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.autonomous.AutoBase;
import org.hackbots.autonomous.AutonBlueAllianceCenterStartShoot;
import org.hackbots.autonomous.AutonBlueAllianceLeftStartShoot;
import org.hackbots.autonomous.AutonCenterStartCenterGear;
import org.hackbots.autonomous.AutonCenterStartLeftGear;
import org.hackbots.autonomous.AutonCenterStartRightGear;
import org.hackbots.autonomous.AutonDoNothing;
import org.hackbots.autonomous.AutonDriveForward;
import org.hackbots.autonomous.AutonDriveLeftAndForward;
import org.hackbots.autonomous.AutonDriveRightAndForward;
import org.hackbots.autonomous.AutonLeftStartCenterGear;
import org.hackbots.autonomous.AutonLeftStartLeftGear;
import org.hackbots.autonomous.AutonRedAllianceCenterStartShoot;
import org.hackbots.autonomous.AutonRedAllianceRightStartShoot;
import org.hackbots.autonomous.AutonRightStartCenterGear;
import org.hackbots.autonomous.AutonRightStartRightGear;
import org.hackbots.sensors.HBJoystick;
import org.hackbots.sensors.SensorConfig;
import org.hackbots.teleop.JuniorTeleop;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot
{
	private JuniorTeleop teleop;
	
	private SendableChooser<AutoBase> autonChooser;

	public void robotInit() 
	{
		RobotStatus.setIsRunning(true);
		
		CameraServer.getInstance().addAxisCamera("169.254.224.101");
		//camera.setResolution(1280, 720);
		
		ActuatorConfig.getInstance().init();
		SensorConfig.getInstance().init();
		
		teleop = new JuniorTeleop();
		
		chooseAuto();	
	}

	public void disabled()
	{
		System.out.println("Disabled");
		// Mentor Francis added the next two lines to reset the encoders each time. This allows repeated testing of Auton without redeploying code
		ActuatorConfig.getInstance().getRightEncoder().setEncPosition(0);
		ActuatorConfig.getInstance().getLeftEncoder().setEncPosition(0);
		teleop.stop();
	}

	public void operatorControl()
	{
		RobotStatus.setIsRunning(true);
		RobotStatus.setIsAuto(false);
		RobotStatus.setIsTeleop(true);
		
		System.out.println("Telop Running!");
		teleop.init();		
	}
	
	/**
	 * Chose's the autonomous mode
	 */
	public void chooseAuto()
	{
		autonChooser = new SendableChooser<AutoBase>();
		autonChooser.addObject("Do Nothing", new AutonDoNothing());
		autonChooser.addObject("Drive Forward", new AutonDriveForward());
		autonChooser.addObject("Left Start Left Gear Delivery", new AutonLeftStartLeftGear());
		autonChooser.addObject("Left Start Center Gear Delivery", new AutonLeftStartCenterGear());
		autonChooser.addObject("Right Start Center Gear Delivery", new AutonRightStartCenterGear());
		autonChooser.addObject("Right Start Right Gear Delivery", new AutonRightStartRightGear());
		autonChooser.addObject("Drive Left then Forward", new AutonDriveLeftAndForward());
		autonChooser.addObject("Drive Right then Forward", new AutonDriveRightAndForward());
		autonChooser.addObject("Center Start Center Gear Delivery", new AutonCenterStartCenterGear());
		autonChooser.addObject("Center Start Right Gear Delivery", new AutonCenterStartRightGear());
		autonChooser.addObject("Center Start Left Gear Delivery", new AutonCenterStartLeftGear());
		autonChooser.addObject("Blue Alliance Center Start Shoot - Not Made", new AutonBlueAllianceCenterStartShoot());
		autonChooser.addObject("Blue Alliance Left Start Shoot - Not Made", new AutonBlueAllianceLeftStartShoot());
		autonChooser.addObject("Red Alliance Center Start Shoot - Not Made", new AutonRedAllianceCenterStartShoot());
		autonChooser.addObject("Red Alliance Right Start Shoot - Not Made", new AutonRedAllianceRightStartShoot());
		
				
		SmartDashboard.putData("Auton", autonChooser);
		
	//	SmartDashboard.putBoolean("Kill Switch", RobotStatus.isAuto());
	}
	
	public void autonomous()
	{			

		RobotStatus.setIsRunning(true);
		RobotStatus.setIsAuto(true);
		RobotStatus.setIsTeleop(false);
		System.out.println(autonChooser.getSelected());
		
		autonChooser.getSelected().doAuto();
	}
	
}
