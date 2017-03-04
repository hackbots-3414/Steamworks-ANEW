     
package org.usfirst.frc.team3414.robot;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.autonomous.AutonDoNothing;
import org.hackbots.autonomous.AutonDriveForward;
import org.hackbots.autonomous.AutoBase;
import org.hackbots.sensors.NavX;
import org.hackbots.sensors.SensorConfig;
import org.hackbots.teleop.JuniorTeleop;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot 
{
	private JuniorTeleop teleop;
	
	private NavX navX = null;
	
	private SendableChooser<AutoBase> autonChooser;

	public void robotInit() 
	{
		RobotStatus.setIsRunning(true);
		
		ActuatorConfig.getInstance().init();
		SensorConfig.getInstance().init();
		
		teleop = new JuniorTeleop();
		
		navX = SensorConfig.getInstance().getNavX();
		navX.registerObserver(teleop);
		
		choseAuto();
		
		
	}

	public void disabled()
	{
		System.out.println("Disabled");
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
	public void choseAuto()
	{
		
		autonChooser = new SendableChooser<AutoBase>();
		autonChooser.addObject("Do Nothing", new AutonDoNothing());
		autonChooser.addObject("Drive Forward", new AutonDriveForward());
		
		
		SmartDashboard.putData("Auton", autonChooser);
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
