     
package org.usfirst.frc.team3414.robot;

import org.hackbots.teleop.JuniorTeleop;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * 
 * @author Hackbots
 *
 */
public class Robot extends IterativeRobot 
{
	private JuniorTeleop teleop;

	public void robotInit() 
	{
		teleop = new JuniorTeleop();
	}

	public void teleopInit() 
	{
		teleop.init();
	}

	public void teleopPeriodic() 
	{
		System.out.println("Running!");
		
		teleop.update();		
	}

	public void autonomousInit() 
	{

	}

	public void autonomousPeriodic()
	{

	}
}
