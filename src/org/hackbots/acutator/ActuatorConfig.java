package org.hackbots.acutator;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

public class ActuatorConfig 
{
	private static ActuatorConfig instance;

	private TalonSRX rightTalonOne;
	private TalonSRX leftTalonTwo;
	private TalonSRX rightTalonTwo;
	private TalonSRX leftTalonOne;
	private TalonSRX rightTalonThree;
	private TalonSRX leftTalonThree;
	
	private TalonSRX agitatorTalon;
	private TalonSRX climberTalon1;
	private TalonSRX climberTalon2;
	private TalonSRX shooterTalon;
	private TalonSRX intakeTalon;

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
	
	private Relay spike;
	
	private Compressor compressor;

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
		
		rightTalonOne = new TalonSRX(0);
		rightTalonTwo = new TalonSRX(1);
		rightTalonThree = new TalonSRX(2);
		
		leftTalonOne = new TalonSRX(5);
		leftTalonTwo = new TalonSRX(4);			
		leftTalonThree = new TalonSRX(3);
		
		//Make the agitator (hopper) 1/2 power
		agitatorTalon = new TalonSRX (9);
		climberTalon1 = new TalonSRX (7);
		climberTalon2 = new TalonSRX (10);
		shooterTalon = new TalonSRX (6);
		intakeTalon = new TalonSRX (8);
		
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
		
		agitatorMotor.setCurrentLimit(60);
		intakeMotor.setCurrentLimit(60);
		
		leftTalonThree.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		//leftTalonThree.configEncoderCodesPerRev(2048);
		leftTalonThree.getSensorCollection().getQuadraturePosition();
		

		rightTalonTwo.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		//rightTalonTwo.configEncoderCodesPerRev(2048);
		rightTalonTwo.getSensorCollection().getQuadraturePosition();

		leftTripleMotor = new TripleMotor(leftMotorOne, leftMotorTwo, leftMotorThree);
		rightTripleMotor = new TripleMotor(rightMotorOne, rightMotorTwo, rightMotorThree);
		
		//rightTripleMotor.setMotorReveresed(true);
		leftTripleMotor.setMotorReveresed(true);
		
		drivetrain = new Drivetrain(rightTripleMotor, leftTripleMotor);
		
		climberMotors = new DoubleMotor(climberMotorOne, climberMotorTwo);		
		
		spike = new Relay(1);
		spike.set(Value.kForward);
		
		compressor = new Compressor();
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
	
	public TalonSRX getRightEncoder()
	{
		return rightTalonTwo;
	}
	
	public TalonSRX getLeftEncoder()
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
	
	public Compressor getCompressor()
	{
		return compressor;
	}
}
