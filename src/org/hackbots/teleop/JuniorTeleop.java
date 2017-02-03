package org.hackbots.teleop;

import org.hackbots.acutator.DoubleMotor;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.acutator.Motor;
import org.hackbots.sensors.DualShockTwoController;
import org.hackbots.sensors.IGamepad;
import org.hackbots.util.ButtonGamepad;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;

public class JuniorTeleop implements ITeleop
{
	private Joystick rightJoystick;
	private Joystick leftJoystick;

	private IGamepad gamepad;

	private CANTalon rightTalonOne;
	private CANTalon leftTalonTwo;
	private CANTalon rightTalonTwo;
	private CANTalon leftTalonOne;
	private CANTalon pickupTalon;
	private CANTalon shootTalon;
	private CANTalon climbTalon;
	private CANTalon hopperTalon;

	private Motor rightMotorOne;
	private Motor leftMotorTwo;
	private Motor rightMotorTwo;
	private Motor leftMotorOne;
	private Motor pickupMotor;
	private Motor shootMotor;
	private Motor climbMotor;
	private Motor hopperMotor;

	private DoubleMotor leftDoubleMotor;
	private DoubleMotor rightDoubleMotor;

	private Drivetrain drivetrain;
	
	public void init() 
	{
		rightJoystick = new Joystick(0);
		leftJoystick = new Joystick(1);

		gamepad = new DualShockTwoController(2);

		rightTalonOne = new CANTalon(1);
		leftTalonTwo = new CANTalon(3);
		rightTalonTwo = new CANTalon(2);
		leftTalonOne = new CANTalon(4);
		pickupTalon = new CANTalon(5);
		//shootTalon = new CANTalon(6);
		//climbTalon = new CANTalon(7);
		
		rightMotorOne = new Motor(rightTalonOne);
		rightMotorTwo = new Motor(rightTalonTwo);

		leftMotorTwo = new Motor(leftTalonTwo);
		leftMotorOne = new Motor(leftTalonOne);
		
		

		leftDoubleMotor = new DoubleMotor(leftMotorOne, leftMotorTwo);
		rightDoubleMotor = new DoubleMotor(rightMotorOne, rightMotorTwo);
		
		//leftDoubleMotor.setMotorReveresed(true);
		//rightDoubleMotor.setMotorReveresed(true);
		
		drivetrain = new Drivetrain(rightDoubleMotor, leftDoubleMotor);
		
		pickupMotor = new Motor(pickupTalon);
		//shootMotor = new Motor(shootTalon);
		//climbMotor = new Motor(climbTalon);
		//hopperMotor = new Motor(hopperTalon)
	}
	
	public void update()
	{
		drivetrain.setSpeed(leftJoystick.getY(), rightJoystick.getY());
		
		if(gamepad.getButtonValue(ButtonGamepad.ONE))
		{
			pickupMotor.setSpeed(0.80);
		}
		else
		{
			pickupMotor.stop();
		}
		
		if(gamepad.getButtonValue(ButtonGamepad.SEVEN))
		{
			leftDoubleMotor.setMotorReveresed(false);
			rightDoubleMotor.setMotorReveresed(false);
		}
		
		if(gamepad.getButtonValue(ButtonGamepad.EIGHT))
		{
			leftDoubleMotor.setMotorReveresed(true);
			rightDoubleMotor.setMotorReveresed(true);
		}


// 		if(gamepad.getButtonValue(ButtonGamepad.TWO))
// 		{
// 			shootMotor.setSpeed(-0.5);
// 		}
// 		else
// 		{
// 			shootMotor.stop();
// 		}

// 		if(gamepad.getButtonValue(ButtonGamepad.THREE))
// 		{
// 			climbMotor.setSpeed(-0.5);
// 		}
// 		else
// 		{
// 			climbMotor.stop();
// 		}

// 		if(gamepad.getButtonValue(ButtonGamepad.FOUR))
// 		{
// 			hopperMotor.setSpeed(-0.5);
// 		}
// 		else
// 		{
// 			hopperMotor.stop();
// 		}
	}
}
