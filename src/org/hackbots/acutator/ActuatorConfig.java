package org.hackbots.acutator;

import com.ctre.CANTalon;

public class ActuatorConfig 
{
	private static ActuatorConfig instance;
	
	private DSolenoid solenoidOne;
	private DSolenoid solenoidTwo;

	private CANTalon rightTalonOne;
	private CANTalon leftTalonTwo;
	private CANTalon rightTalonTwo;
	private CANTalon leftTalonOne;
	private CANTalon rightTalonThree;
	private CANTalon leftTalonThree;
	private CANTalon pickupTalon;
	private CANTalon adjustShootTalon;
	private CANTalon shootTalon;
	private CANTalon climbTalon;
	private CANTalon hopperTalon;

	private Motor rightMotorOne;
	private Motor leftMotorTwo;
	private Motor rightMotorTwo;
	private Motor leftMotorOne;
	private Motor rightMotorThree;
	private Motor leftMotorThree;
	
	private Motor pickupMotor;
	private Motor shootMotor;
	private Motor climbMotor;
	private Motor hopperMotor;
	
	private Servo adjustShootServo;

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
		
		//pickupTalon = new CANTalon(6);
		//shootTalon = new CANTalon(7);
		//climbTalon = new CANTalon(8);
		//adjustShootTalon = new CANTalon(9);
		
		rightMotorOne = new Motor(rightTalonOne);
		rightMotorTwo = new Motor(rightTalonTwo);
		rightMotorThree = new Motor(rightTalonThree);

		leftMotorTwo = new Motor(leftTalonTwo);
		leftMotorOne = new Motor(leftTalonOne);
		leftMotorThree = new Motor(leftTalonThree);
		
		leftTripleMotor = new TripleMotor(leftMotorOne, leftMotorTwo, leftMotorThree);
		rightTripleMotor = new TripleMotor(rightMotorOne, rightMotorTwo, rightMotorThree);
		
		drivetrain = new Drivetrain(rightTripleMotor, leftTripleMotor);
		
		pickupMotor = new Motor(pickupTalon);
		//shootMotor = new Motor(shootTalon);
		//climbMotor = new Motor(climbTalon);
		//hopperMotor = new Motor(hopperTalon);
		//adjustShootServo = new Servo (adjustShootTalon);
	}
	
	public Drivetrain getDrivetrain()
	{
		return drivetrain;
	}
}
