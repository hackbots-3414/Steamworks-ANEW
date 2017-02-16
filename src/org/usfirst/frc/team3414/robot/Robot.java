     
package org.usfirst.frc.team3414.robot;

import org.hackbots.autonomous.AutonDoNothing;
import org.hackbots.autonomous.IAuton;
import org.hackbots.teleop.JuniorTeleop;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot 
{
	private JuniorTeleop teleop;
	
	private SendableChooser<IAuton> autonChooser;

	public void robotInit() 
	{
		teleop = new JuniorTeleop();
		
		choseAuto();
	}

	public void teleopInit() 
	{
		teleop.init();
	}
	
	public void disabled()
	{
		System.out.println("Disabled");
		//teleop.stop();
	}

	public void operatorControl() 
	{
		System.out.println("Running!");
		
		teleop.update();		
	}
	
	/**
	 * Chose's the autonomous mode
	 */
	public void choseAuto()
	{
		autonChooser = new SendableChooser<IAuton>();
		autonChooser.addObject("Do Nothing", new AutonDoNothing());
		
		SmartDashboard.putData("Auton", autonChooser);
	}
	
	public void autonomous()
	{
		System.out.println(autonChooser.getSelected());
		
		autonChooser.getSelected().doAuto();
	}
}
