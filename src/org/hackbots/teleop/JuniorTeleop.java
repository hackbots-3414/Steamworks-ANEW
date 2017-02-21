package org.hackbots.teleop;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.sensors.Gamepad;
import org.hackbots.sensors.IGamepad;
import org.hackbots.sensors.NavX;
import org.hackbots.util.ButtonGamepad;
import org.hackbots.util.Observer;
import org.hackbots.util.Subject;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JuniorTeleop implements ITeleop, Observer
{
	
	
	private Joystick rightJoystick;
	private Joystick leftJoystick;

	private IGamepad gamepad;
		
	private static final int CLIMBER_LIM_SWITCH_CHANNEL = 3;
	private DigitalInput _climberLimitSwitch = new DigitalInput(CLIMBER_LIM_SWITCH_CHANNEL);
	//private LimitSwitch climberLimitSwitch = new LimitSwitch(_climberLimitSwitch);
	
	//private NavX navX;
	
	//private boolean reverseJoystics = false;
	
	//private PowerDistributionPanel pdb;
	
	//private Encoder rightEncoder;
	//private Encoder leftEncoder;
	
	private Thread driveThread;
	private Thread controlThread;
	
	private float startingAngle;
	
	private boolean isRunning;
	
	int i = 0;
	
	private Drivetrain drivetrain = ActuatorConfig.getInstance().getDrivetrain();
	
	public void init() 
	{
		rightJoystick = new Joystick(0);
		leftJoystick = new Joystick(1);

		gamepad = new Gamepad(2);
		
		//navX = new NavX(new AHRS(SPI.Port.kMXP));
		//navX.reset();
		
		//pdb = new PowerDistributionPanel(8);
		
	//	rightEncoder = new Encoder(rightTalonOne);
	//	leftEncoder = new Encoder(leftTalonOne);
		
		
		driveThread = new Thread(new DriveThread());
		controlThread = new Thread(new ControlThread());
		
		controlThread.start();
		driveThread.start();
		
		isRunning = true;
	}
	
	public void update()
	{
		
	}
	
	public void stop()
	{
		if(isRunning)
		{
			isRunning = false;    
		}
	}
	
	public class DriveThread implements Runnable
	{
		public void run()
		{
			while(isRunning)
			{
				//System.out.println("Drive Thread");

			/*	System.out.println("Yaw: " + navX.getRawYaw());
				System.out.println("Pitch: " + navX.getPitch());
				System.out.println("Roll: " + navX.getRoll());*/
				/*SmartDashboard.putNumber("Yaw: ", navX.getRawYaw());
				SmartDashboard.putNumber("Pitch: ", navX.getPitch());
				SmartDashboard.putNumber("Roll: ", navX.getRoll());*/
				
				//System.out.println("Left Joy: " + leftJoystick.getY());
				//System.out.println("Right Joy: " + rightJoystick.getY());
				//System.out.println("X Veocity: " + navX.getVelocityX());
				//System.out.println("Y Veocity: " + navX.getVelocityY());
				//System.out.println("Z Veocity: " + navX.getVelocityZ());
				
				if (leftJoystick.getY() > 0.1 || rightJoystick.getY() > 0.1 || leftJoystick.getY() < -0.1 || rightJoystick.getY() < -0.1)
				{
					drivetrain.setSpeed(-(leftJoystick.getY()), -(rightJoystick.getY()));
				}
				else if((leftJoystick.getY() > 0.1 && rightJoystick.getY() < -0.1) || (leftJoystick.getY() < -0.1 && rightJoystick.getY() > 0.1))
				{
					drivetrain.setSpeed(-(leftJoystick.getY() / 2), -(rightJoystick.getY() / 2));
				}
				
				else
				{
					drivetrain.setSpeed(0);
				}
				//TODO: Change the drivetrain speed from 0.2 to +0.2 of the velocity so you can drive forward.
				if (rightJoystick.getRawButton(1))
				{
					//startingAngle = Math.round(navX.getRawYaw());
					
					/*while (rightJoystick.getRawButton(1))
					{
						if(navX.getYaw() > startingAngle - 1)
						{
							drivetrain.setSpeed(0.2, -0.2);
						}
						else if (navX.getYaw() < startingAngle + 1)
						{
							drivetrain.setSpeed(-0.2, 0.2);
						}
					}*/
				}
//				if (rightJoystick.getRawButton(2))
//				{
//					drivetrain.setSpeed(-0.2, 0.2);
//				}

				
			/*if(reverseJoystics)
			{
				drivetrain.setSpeed(rightJoystick.getY(), leftJoystick.getY());
			}
			else
			{
				System.out.println("Driving");
				drivetrain.setSpeed(leftJoystick.getY(), rightJoystick.getY());
			}*/
			
			/*if (leftJoystick.getY() > 0.1 || rightJoystick.getY() > 0.1 || leftJoystick.getY() < -0.1 || rightJoystick.getY() < -0.1)
			{
				drivetrain.setSpeed(-leftJoystick.getY(), -rightJoystick.getY());
			}
			else
			{
				drivetrain.setSpeed(0);
			}*/
				
				
				
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
				//System.out.println("Compass Heading" + navX.getYaw());
				
				if(gamepad.getButtonValue(ButtonGamepad.ONE))
				{
//					pickupMotor.setSpeed(0.80);
				}
				else
				{
//					pickupMotor.stop();
				}
				
//				if(gamepad.getButtonValue(ButtonGamepad.SEVEN))
//				{
//					leftTripleMotor.setMotorReveresed(false);
//					rightTripleMotor.setMotorReveresed(false);
//					
//					System.out.println("Unreversing");
//					
//					reverseJoystics = false;
//				}
//				
//				if(gamepad.getButtonValue(ButtonGamepad.EIGHT))
//				{
//					leftTripleMotor.setMotorReveresed(true);
//					rightTripleMotor.setMotorReveresed(true);
//					
//					reverseJoystics = true;
//
//					System.out.println("Reversing");
//				}				
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
	public void update(Subject subject) 
	{
		
		System.out.println("Subject done! " + i);	
	}
}
