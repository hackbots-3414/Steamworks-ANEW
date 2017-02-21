package org.hackbots.acutator;

import com.ctre.CANTalon;

public class ActuatorConfig 
{
	private static ActuatorConfig instance;
	
	//private DSolenoid solenoidOne;
	//private DSolenoid solenoidTwo;

	private CANTalon rightTalonOne;
	private CANTalon leftTalonTwo;
	private CANTalon rightTalonTwo;
	private CANTalon leftTalonOne;
	private CANTalon rightTalonThree;
	private CANTalon leftTalonThree;
	

	private Motor rightMotorOne;
	private Motor leftMotorTwo;
	private Motor rightMotorTwo;
	private Motor leftMotorOne;
	private Motor rightMotorThree;
	private Motor leftMotorThree;

	private TripleMotor leftTripleMotor;
	private TripleMotor rightTripleMotor;
	
	private Drivetrain drivetrain;

	private ActuatorConfig(){}
	
	public static ActuatorConfig getInstance()
	{
		if(instance == null)
		{
			instance = new ActuatorConfig();
		}
		
		return instance;
	}
	
	public void init()
	{
		//solenoidOne = new DSolenoid(new DoubleSolenoid(0,1));
		//solenoidTwo = new DSolenoid(new DoubleSolenoid(0,1));

		rightTalonOne = new CANTalon(0);
		rightTalonTwo = new CANTalon(1);
		rightTalonThree = new CANTalon(2);
		
		leftTalonOne = new CANTalon(5);
		leftTalonTwo = new CANTalon(4);			
		leftTalonThree = new CANTalon(3);
		
		rightMotorOne = new Motor(rightTalonOne);
		rightMotorTwo = new Motor(rightTalonTwo);
		rightMotorThree = new Motor(rightTalonThree);

		leftMotorTwo = new Motor(leftTalonTwo);
		leftMotorOne = new Motor(leftTalonOne);
		leftMotorThree = new Motor(leftTalonThree);
		
		leftTripleMotor = new TripleMotor(leftMotorOne, leftMotorTwo, leftMotorThree);
		rightTripleMotor = new TripleMotor(rightMotorOne, rightMotorTwo, rightMotorThree);
		
		drivetrain = new Drivetrain(rightTripleMotor, leftTripleMotor);
	}
	
	public Drivetrain getDrivetrain()
	{
		return drivetrain;
	}
}
