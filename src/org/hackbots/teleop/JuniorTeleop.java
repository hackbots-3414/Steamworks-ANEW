package org.hackbots.teleop;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.sensors.Gamepad;
import org.hackbots.sensors.HBJoystick;
import org.hackbots.sensors.IGamepad;
import org.hackbots.util.ButtonGamepad;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JuniorTeleop implements ITeleop
{
	private HBJoystick rightJoystick;
	private HBJoystick leftJoystick;

	private IGamepad gamepad;
	
	private Thread driveThread;
	private Thread controlThread;
	
	private boolean isRunning;
	
	private Drivetrain drivetrain = ActuatorConfig.getInstance().getDrivetrain();
	
	//private PowerDistributionPanel pdb = new PowerDistributionPanel(8);
	
	public void init() 
	{
		rightJoystick = new HBJoystick(0);
		leftJoystick = new HBJoystick(1);

		gamepad = new Gamepad(2);
		
		driveThread = new Thread(new DriveThread());
		controlThread = new Thread(new ControlThread());
		
		controlThread.start();
		driveThread.start();
		
		isRunning = true;
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
				SmartDashboard.putNumber("Left Encoder - Teleop", ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (-0.000122));//
				SmartDashboard.putNumber("Right Encoder - Teleop", ActuatorConfig.getInstance().getRightEncoder().getEncPosition()* (0.000122));
	
				if (leftJoystick.getY() > 0.15 || rightJoystick.getY() > 0.15 || leftJoystick.getY() < -0.20 || rightJoystick.getY() < -0.1)
				{
					double speed = (leftJoystick.getY() + rightJoystick.getYAxis()) / 2;
					
					drivetrain.setSpeed(speed, speed);//Add Gyro 
				}
				else if((leftJoystick.getY() > 0.15 && rightJoystick.getY() < -0.15) || (leftJoystick.getY() < -0.20 && rightJoystick.getY() > 0.1))
				{
					if(rightJoystick.isReversed() && leftJoystick.isReversed())
					{
						drivetrain.setSpeed((leftJoystick.getYAxis() / 2), (rightJoystick.getYAxis() / 2));
					}
					
					else
					{
						drivetrain.setSpeed((leftJoystick.getYAxis() / 2), (rightJoystick.getYAxis() / 2));
					}
				}
				
				else
				{
					drivetrain.setSpeed(0);
				}
				
				//TODO: Change the drivetrain speed from 0.2 to +0.2 of the velocity so you can drive forward.
//				if (rightJoystick.getRawButton(11))
//				{
////					System.out.println("SetcPointing");
////					ActuatorConfig.getInstance().getPIDController().setSetpoint(200);
////					
//
//
//					/*double heading = Math.round(navX.getYaw()) + 90;		
//					
//					if(heading > 360)
//					{
//						heading -= 360;
//						
//						while((navX.getYaw() + FRICTION_FACTOR_CAFETERIA - 2) > heading)
//						{
//							System.out.println("Turning to " + heading + "At: " + navX.getYaw());
//							drivetrain.setSpeed(0.3, -0.3);
//						}	
//					}
//					
//						while((navX.getYaw() + FRICTION_FACTOR_CAFETERIA) < heading)
//						{
//							System.out.println("Turning to " + heading + "At: " + navX.getYaw());
//							drivetrain.setSpeed(0.3, -0.3);
//						}	*/
//					
//					
//				//	System.out.println("Heading: " + heading);
//					
//					
//					
//					
//					
//					
//					//startingAngle = Math.round(navX.getRawYaw());
//					
//					/*while (rightJoystick.getRawButton(1))
//					{
//						if(navX.getYaw() > startingAngle - 1)
//						{
//							drivetrain.setSpeed(0.2, -0.2);
//						}
//						else if (navX.getYaw() < startingAngle + 1)
//						{
//							drivetrain.setSpeed(-0.2, 0.2);
//						}
//					}*/
//				}
				
				if(gamepad.getButtonValue(ButtonGamepad.ONE))
				{
					ActuatorConfig.getInstance().getAgitator().setSpeed(-0.20);
				}
				else
				{
					ActuatorConfig.getInstance().getAgitator().setSpeed(0);
				}
				
				if(gamepad.getButtonValue(ButtonGamepad.SIX))
				{
					ActuatorConfig.getInstance().getClimberMotor().setSpeed(-1);
				}
				else
				{
					ActuatorConfig.getInstance().getClimberMotor().setSpeed(0);
				}
				
				if(gamepad.getButtonValue(ButtonGamepad.THREE))
				{
					ActuatorConfig.getInstance().getShooter().setSpeed(0.9);
				}
				else
				{
					ActuatorConfig.getInstance().getShooter().setSpeed(0);
				}
				
				if(gamepad.getButtonValue(ButtonGamepad.FIVE))
				{
					ActuatorConfig.getInstance().getIntakeMotor().setSpeed(-1);
				}
				else
				{
					ActuatorConfig.getInstance().getIntakeMotor().setSpeed(0);
				}
				
				if(leftJoystick.getRawButton(1))
				{
					ActuatorConfig.getInstance().getGearManipulator().set(Value.kForward);
				}
				else
				{
					ActuatorConfig.getInstance().getGearManipulator().set(Value.kReverse);
				}
				
				if(rightJoystick.getRawButton(1))
				{
					ActuatorConfig.getInstance().getGearTopSolenoid().set(Value.kForward);
				}
				else if(rightJoystick.getRawButton(2))
				{
					ActuatorConfig.getInstance().getGearManipulator().set(Value.kReverse);
				}
				
				if(rightJoystick.getRawButton(8) || leftJoystick.getRawButton(8))
				{
					leftJoystick.setReversed(true);
					rightJoystick.setReversed(true);
					System.out.println("Reversing...");
				}		
				else if(rightJoystick.getRawButton(7) || leftJoystick.getRawButton(7))
				{
					leftJoystick.setReversed(false);
					rightJoystick.setReversed(false);
				}
				
//				if(gamepad.getButtonValue(ButtonGamepad.ONE))
//				{
//					ActuatorConfig.getInstance().getShooter().setSpeed(0.9);
//					
//					if(ActuatorConfig.getInstance().getShooter().isRunning())
//					{
//						System.out.println("Shooter is Runnin");
//
//						ActuatorConfig.getInstance().getAgitator().setSpeed(-0.25);
//					}
//				}
//				else
//				{
//					ActuatorConfig.getInstance().getShooter().setSpeed(0);
//	 					ActuatorConfig.getInstance().getAgitator().setSpeed(0);
//				}	
				
//				if (gamepad.getButtonValue(ButtonGamepad.ONE))
//				{
//					drivetrain.setSpeed(-0.2, 0.2);
//				}
		}	
	}
}
	public class ControlThread implements Runnable
	{
		public void run()
		{
			while(isRunning)
			{				
				if(gamepad.getButtonValue(ButtonGamepad.EIGHT))
				{
					leftJoystick.setReversed(true);
					rightJoystick.setReversed(true);
					System.out.println("Reversing...");
				}		
				else if(gamepad.getButtonValue(ButtonGamepad.SEVEN))
				{
					leftJoystick.setReversed(false);
					rightJoystick.setReversed(false);
				}
			}
		}		
	}
}
