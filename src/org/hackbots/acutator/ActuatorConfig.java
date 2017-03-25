package org.hackbots.acutator;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ActuatorConfig 
{
	private static ActuatorConfig instance;

	private CANTalon rightTalonOne;
	private CANTalon leftTalonTwo;
	private CANTalon rightTalonTwo;
	private CANTalon leftTalonOne;
	private CANTalon rightTalonThree;
	private CANTalon leftTalonThree;
	
	private CANTalon agitatorTalon;
	private CANTalon climberTalon1;
	private CANTalon climberTalon2;
	private CANTalon shooterTalon;
	private CANTalon intakeTalon;

	private Motor rightMotorOne;
	private Motor leftMotorTwo;
	private Motor rightMotorTwo;
	private Motor leftMotorOne;
	private Motor rightMotorThree;
	private Motor leftMotorThree;
	
	private Motor agitatorMotor;
	private Motor climberMotorOne;
	private Motor climberMotorTwo;
	private Motor shooterMotor;
	private Motor intakeMotor;
	
	private TripleMotor leftTripleMotor;
	private TripleMotor rightTripleMotor;
	
	private DoubleMotor climberMotors;
	
	private Drivetrain drivetrain;
	
	private DoubleSolenoid gearManipulator;
	private DoubleSolenoid topSolenoid;

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
		gearManipulator = new DoubleSolenoid (2,0);//not finalized number
		
		topSolenoid = new DoubleSolenoid(1,3);
		System.out.println("Open!");
		//topSolenoid.set(Value.kForward);
		
		rightTalonOne = new CANTalon(0);
		rightTalonTwo = new CANTalon(1);
		rightTalonThree = new CANTalon(2);
		
		leftTalonOne = new CANTalon(5);
		leftTalonTwo = new CANTalon(4);			
		leftTalonThree = new CANTalon(3);
		
		//Make the agitator (hopper) 1/2 power
		agitatorTalon = new CANTalon (9);
		climberTalon1 = new CANTalon (7);
		climberTalon2 = new CANTalon (10);
		shooterTalon = new CANTalon (6);
		intakeTalon = new CANTalon (8);
		
		rightMotorOne = new Motor(rightTalonOne);
		rightMotorTwo = new Motor(rightTalonTwo);
		rightMotorThree = new Motor(rightTalonThree);

		leftMotorTwo = new Motor(leftTalonTwo);
		leftMotorOne = new Motor(leftTalonOne);
		leftMotorThree = new Motor(leftTalonThree);
		
		agitatorMotor = new Motor (agitatorTalon);
		climberMotorOne = new Motor (climberTalon1);
		climberMotorTwo = new Motor (climberTalon2);
		shooterMotor = new Motor (shooterTalon);
		intakeMotor = new Motor (intakeTalon);
		
		rightMotorOne.setCurrentLimit(20);
		rightMotorTwo.setCurrentLimit(20);
		rightMotorThree.setCurrentLimit(20);
		
		leftMotorTwo.setCurrentLimit(20);
		leftMotorOne.setCurrentLimit(20);
		leftMotorThree.setCurrentLimit(20);
		
		leftTalonThree.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftTalonThree.configEncoderCodesPerRev(2048);
		leftTalonThree.setPosition(0);
		

		rightTalonTwo.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightTalonTwo.configEncoderCodesPerRev(2048);
		rightTalonTwo.setPosition(0);

		leftTripleMotor = new TripleMotor(leftMotorOne, leftMotorTwo, leftMotorThree);
		rightTripleMotor = new TripleMotor(rightMotorOne, rightMotorTwo, rightMotorThree);
		
		//rightTripleMotor.setMotorReveresed(true);
		leftTripleMotor.setMotorReveresed(true);
		
		drivetrain = new Drivetrain(rightTripleMotor, leftTripleMotor);
		
		climberMotors = new DoubleMotor(climberMotorOne, climberMotorTwo);		
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
	
	public DoubleMotor getClimberMotor()
	{
		return climberMotors;
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
	public DoubleSolenoid getGearManipulator()
	{
		return gearManipulator;
	}
	
	public DoubleSolenoid getGearTopSolenoid()
	{
		return topSolenoid;
	}
}
