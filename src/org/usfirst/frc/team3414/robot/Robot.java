
package org.usfirst.frc.team3414.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot
{
	private Joystick rightJoystick;
	private Joystick leftJoystick;
	
	private CANTalon rightMotorOne;
	private CANTalon leftMotorTwo;
	private CANTalon rightMotorTwo;
	private CANTalon leftMotorOne;
	
	@Override
	public void robotInit() 
	{
		rightJoystick = new Joystick(0);
		leftJoystick = new Joystick(1);
		
		rightMotorOne = new CANTalon(1);
		rightMotorTwo = new CANTalon(2);
		
		leftMotorTwo = new CANTalon(3);
		leftMotorOne = new CANTalon(4);			
	}

	@Override
	public void teleopInit() 
	{

	}

	@Override
	public void teleopPeriodic() 
	{
		System.out.println("Running!");
		rightMotorOne.set(rightJoystick.getY());
		rightMotorTwo.set(rightJoystick.getY());
		leftMotorTwo.set(leftJoystick.getY());
		leftMotorOne.set(leftJoystick.getY());
	}
	
	@Override
	public void autonomousInit()
	{
		
	}
	
	@Override
	public void autonomousPeriodic()
	{
		
	}
}
