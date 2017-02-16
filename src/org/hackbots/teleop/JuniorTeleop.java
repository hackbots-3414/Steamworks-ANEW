package org.hackbots.teleop;

import org.hackbots.acutator.DSolenoid;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.acutator.Motor;
import org.hackbots.acutator.Servo;
//import org.hackbots.acutator.DoubleMotor;
import org.hackbots.acutator.TripleMotor;
import org.hackbots.sensors.Encoder;
import org.hackbots.sensors.Gamepad;
import org.hackbots.sensors.IGamepad;
import org.hackbots.sensors.LimitSwitch;
import org.hackbots.sensors.NavX;
import org.hackbots.util.ButtonGamepad;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;

public class JuniorTeleop implements ITeleop
{
	private Joystick rightJoystick;
	private Joystick leftJoystick;

	private IGamepad gamepad;
	
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
	
	private static final int CLIMBER_LIM_SWITCH_CHANNEL = 3;
	private DigitalInput _climberLimitSwitch = new DigitalInput(CLIMBER_LIM_SWITCH_CHANNEL);
	private LimitSwitch climberLimitSwitch = new LimitSwitch(_climberLimitSwitch);

	private Drivetrain drivetrain;
	
	private NavX navX;
	
	private boolean reverseJoystics = false;
	
	private PowerDistributionPanel pdb;
	
	private Encoder rightEncoder;
	private Encoder leftEncoder;
	
	private Thread driveThread;
	private Thread controlThread;
	
	private float startingAngle;
	
	private boolean isRunning;
	
	public void init() 
	{
		rightJoystick = new Joystick(0);
		leftJoystick = new Joystick(1);

		gamepad = new Gamepad(2);
		
		solenoidOne = new DSolenoid(new DoubleSolenoid(0,1));
		solenoidTwo = new DSolenoid(new DoubleSolenoid(0,1));

		rightTalonOne = new CANTalon(0);
		rightTalonTwo = new CANTalon(1);
		rightTalonThree = new CANTalon(2);
		
		leftTalonTwo = new CANTalon(3);	
		leftTalonOne = new CANTalon(4);		
		leftTalonThree = new CANTalon(5);
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
		
		leftTripleMotor.setMotorReveresed(true);
		
		//leftDoubleMotor.setMotorReveresed(true);
		//rightDoubleMotor.setMotorReveresed(true);
		
		drivetrain = new Drivetrain(rightTripleMotor, leftTripleMotor);
		
		pickupMotor = new Motor(pickupTalon);
		//shootMotor = new Motor(shootTalon);
		//climbMotor = new Motor(climbTalon);
		//hopperMotor = new Motor(hopperTalon);
		//adjustShootServo = new Servo (adjustShootTalon);
		
		navX = new NavX(new AHRS(SPI.Port.kMXP));
		navX.reset();
		
		pdb = new PowerDistributionPanel(8);
		
		rightEncoder = new Encoder(rightTalonOne);
		leftEncoder = new Encoder(leftTalonOne);
		
		
		driveThread = new Thread(new DriveThread());
		controlThread = new Thread(new ControlThread());
		
		controlThread.start();
		driveThread.start();
		
		isRunning = true;
	}
	
	public void update()
	{
		
<<<<<<< HEAD
		
	}
	
	public void stop()
	{
		if(isRunning)
		{
			isRunning = false;    
		}
=======
>>>>>>> 32057740febcf8313244316172df8bbc1daa3d87
	}
	
	public class DriveThread implements Runnable
	{
		public void run()
		{
<<<<<<< HEAD
			while(isRunning)
			{
				if (leftJoystick.getY() > 0.1 || rightJoystick.getY() > 0.1 || leftJoystick.getY() < -0.1 || rightJoystick.getY() < -0.1)
				{
					drivetrain.setSpeed(leftJoystick.getY(), rightJoystick.getY());
				}
				else
				{
					drivetrain.setSpeed(0);
				}
=======
			/*if(reverseJoystics)
			{
				drivetrain.setSpeed(rightJoystick.getY(), leftJoystick.getY());
			}
			else
			{
				System.out.println("Driving");
				drivetrain.setSpeed(leftJoystick.getY(), rightJoystick.getY());
			}*/
			
			if (leftJoystick.getY() > 0.1 || rightJoystick.getY() > 0.1 || leftJoystick.getY() < -0.1 || rightJoystick.getY() < -0.1)
			{
				drivetrain.setSpeed(leftJoystick.getY(), rightJoystick.getY());
			}
			else
			{
				drivetrain.setSpeed(0);
>>>>>>> 32057740febcf8313244316172df8bbc1daa3d87
			}
		}	
	}
	
	public class ControlThread implements Runnable
	{
		public void run()
		{
			while(isRunning)
			{
				//System.out.println("Motor Current: " + pdb.getCurrent(15));
				
				//System.out.println("Right Endocer: " + -rightEncoder.getCount());
				//System.out.println("Left Endocer: " + leftEncoder.getCount());
				
				// System.out.println("compass Heading: " + compass.getHeading());
				System.out.println("Compass Heading" + navX.getYaw());
				
				if(gamepad.getButtonValue(ButtonGamepad.ONE))
				{
//					pickupMotor.setSpeed(0.80);
				}
				else
				{
//					pickupMotor.stop();
				}
				
				if(gamepad.getButtonValue(ButtonGamepad.SEVEN))
				{
					leftTripleMotor.setMotorReveresed(false);
					rightTripleMotor.setMotorReveresed(false);
					
					System.out.println("Unreversing");
					
					reverseJoystics = false;
				}
				
				if(gamepad.getButtonValue(ButtonGamepad.EIGHT))
				{
					leftTripleMotor.setMotorReveresed(true);
					rightTripleMotor.setMotorReveresed(true);
					
					reverseJoystics = true;

					System.out.println("Reversing");
				}
				
				if (rightJoystick.getRawButton(1))
				{
					startingAngle = navX.getYaw();
					
					while (rightJoystick.getRawButton(1))
					{
						if(navX.getYaw() < startingAngle - 1)
						{
							drivetrain.setSpeed(0.2, -0.2);
						}
						else if (navX.getYaw() > startingAngle + 1)
						{
							drivetrain.setSpeed(-0.2, 0.2);
						}
					}
				}
<<<<<<< HEAD

				
=======
			}		
>>>>>>> 32057740febcf8313244316172df8bbc1daa3d87

//		 		if(gamepad.getButtonValue(ButtonGamepad.TWO))
//		 		{
//		 			shootMotor.setSpeed(-0.5);
//		 		}
//		 		else
//		 		{
//		 			shootMotor.stop();
//		 		}
//				if (gamepad.getButtonValue(ButtonGamepad.TWO) && gamepad.getButtonValue(ButtonGamepad.THREE))
//				{
//					adjustShootServo.set(0.2);
//				}
//				else if (gamepad.getButtonValue(ButtonGamepad.TWO) && gamepad.getButtonValue(ButtonGamepad.FOUR))
//				{
//					adjustShootServo.set(0);
//				}
//				else
//				{
//					adjustShootServos.disengage();
//				}
//		 		if(gamepad.getButtonValue(ButtonGamepad.THREE))
//		 		{
//		 			climbMotor.setSpeed(-0.5);
//		 		}
//		 		else if (gamepad.getButtonValue(ButtonGamepad.THREE) & climberLimitSwitch.isHit()) 
//		 		{
//		 			climbMotor.stop();
//		 		}
//		 		else
//		 		{
//		 			climbMotor.stop();
//		 		}
				
//				if (gamepad.getButtonValue(ButtonGamepad.SEVEN) && gamepad.getButtonValue(ButtonGamepad.EIGHT))
//				{
//					dSol.set(DoubleSolenoid.Value.kForward);
//				}
				
				
				
				

//		 		if(gamepad.getButtonValue(ButtonGamepad.FOUR))
//		 		{
//		 			hopperMotor.setSpeed(-0.5);
//		 		}
//				else if(gamepad.getButtonValue(ButtonGamepad.FOUR) && gamepad.getButtonValue(ButtonGamepad.ONE))
//				{
//					hopperMotor.setSpeed(0.5);
//				}
//				else
//				{
//					hopperMotor.stop();
//				}
			}
		}		
	}
}
