
package org.usfirst.frc.team3414.robot;

import org.hackbots.acutator.DoubleMotor;
import org.hackbots.acutator.Motor;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot
{
	private Joystick rightJoystick;
	private Joystick leftJoystick;
	
	private CANTalon rightTalonOne;
	private CANTalon leftTalonTwo;
	private CANTalon rightTalonTwo;
	private CANTalon leftTalonOne;
	
	private Motor rightMotorOne;
	private Motor leftMotorTwo;
	private Motor rightMotorTwo;
	private Motor leftMotorOne;
	
	private DoubleMotor leftDoubleMotor;
	private DoubleMotor rightDoubleMotor;
	
	@Override
	public void robotInit() 
	{
		rightJoystick = new Joystick(0);
		leftJoystick = new Joystick(1);
		
		rightTalonOne = new CANTalon(1);
		leftTalonTwo = new CANTalon(3);
		rightTalonTwo = new CANTalon(2);
		leftTalonOne = new CANTalon(4);
		
		rightMotorOne = new Motor(rightTalonOne);
		rightMotorTwo = new Motor(rightTalonTwo);
		
		leftMotorTwo = new Motor(leftTalonTwo);
		leftMotorOne = new Motor(leftTalonOne);		
		
		leftDoubleMotor = new DoubleMotor(leftMotorOne, leftMotorTwo);
		rightDoubleMotor = new DoubleMotor(rightMotorOne, rightMotorTwo);
	}

	@Override
	public void teleopInit() 
	{

	}

	@Override
	public void teleopPeriodic() 
	{
		System.out.println("Running!");
		/*rightMotorOne.setSpeed(rightJoystick.getY());
		rightMotorTwo.setSpeed(rightJoystick.getY());
		leftMotorTwo.setSpeed(leftJoystick.getY());
		leftMotorOne.setSpeed(leftJoystick.getY());*/
		
		rightDoubleMotor.setSpeed(rightJoystick.getY());
		leftDoubleMotor.setSpeed(leftJoystick.getY());
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
