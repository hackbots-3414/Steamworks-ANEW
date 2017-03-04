package org.hackbots.acutator;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

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
	
	private CANTalon agitatorTalon;
	private CANTalon climberTalon;
	private CANTalon shooterTalon;
	private CANTalon intakeTalon;

	private Motor rightMotorOne;
	private Motor leftMotorTwo;
	private Motor rightMotorTwo;
	private Motor leftMotorOne;
	private Motor rightMotorThree;
	private Motor leftMotorThree;
	
	private Motor agitatorMotor;
	private Motor climberMotor;
	private Motor shooterMotor;
	private Motor intakeMotor;
	
	private TripleMotor leftTripleMotor;
	private TripleMotor rightTripleMotor;
	
	private Drivetrain drivetrain;
	
	// Drive encoders
	//private QuadEncoder leftDriveEncoder;
	//private QuadEncoder rightDriveEncoder;

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
		

		//Make the agitator (hopper) 1/2 power
		agitatorTalon = new CANTalon (9);
		climberTalon = new CANTalon (7);
		shooterTalon = new CANTalon (6);
		intakeTalon = new CANTalon (8);
		
		/*leftDriveEncoder = new QuadEncoder(0,1);
		rightDriveEncoder = new QuadEncoder(0,1);
		
		leftDriveEncoder.enable();
		rightDriveEncoder.enable();*/
		
		rightMotorOne = new Motor(rightTalonOne);
		rightMotorTwo = new Motor(rightTalonTwo);
		rightMotorThree = new Motor(rightTalonThree);

		leftMotorTwo = new Motor(leftTalonTwo);
		leftMotorOne = new Motor(leftTalonOne);
		leftMotorThree = new Motor(leftTalonThree);
		
		agitatorMotor = new Motor (agitatorTalon);
		climberMotor = new Motor (climberTalon);
		shooterMotor = new Motor (shooterTalon);
		intakeMotor = new Motor (intakeTalon);
		
		leftTalonThree.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftTalonThree.configEncoderCodesPerRev(2048);
		leftTalonThree.setPosition(0);
//		leftTalonThree.setInverted(true);
//		leftTalonThree.setForwardSoftLimit(+15.0);
//		leftTalonThree.setReverseSoftLimit(-15.0);
//		rightTalonTwo.reverseSensor(true);
		rightTalonTwo.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightTalonTwo.configEncoderCodesPerRev(2048);
		rightTalonTwo.setPosition(0);
//		rightTalonTwo.setForwardSoftLimit(+15.0);
//		rightTalonTwo.setReverseSoftLimit(-15.0);
		
		
		leftTripleMotor = new TripleMotor(leftMotorOne, leftMotorTwo, leftMotorThree);
		rightTripleMotor = new TripleMotor(rightMotorOne, rightMotorTwo, rightMotorThree);
		
		rightTripleMotor.setMotorReveresed(true);
		
		drivetrain = new Drivetrain(rightTripleMotor, leftTripleMotor);
	}
	
	public Drivetrain getDrivetrain()
	{
		return drivetrain;
	}
	
	public Motor getAgitator()
	{
		return agitatorMotor;
	}
	
	public Motor getShooter()
	{
		return shooterMotor;
	}
	
	public Motor getClimberMotor()
	{
		return climberMotor;
	}
	
	public Motor getIntakeMotor()
	{
		return intakeMotor;
	}
	
	public CANTalon getRightEncoder()
	{
		return rightTalonTwo;
	}
	
	public CANTalon getLeftEncoder()
	{
		return leftTalonThree;
	}

}
