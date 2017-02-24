package org.hackbots.teleop;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.sensors.Gamepad;
import org.hackbots.sensors.HBJoystick;
import org.hackbots.sensors.IGamepad;
import org.hackbots.sensors.NavX;
import org.hackbots.sensors.SensorConfig;
import org.hackbots.util.ButtonGamepad;
import org.hackbots.util.Observer;
import org.hackbots.util.Subject;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JuniorTeleop implements ITeleop, Observer
{
	private HBJoystick rightJoystick;
	private HBJoystick leftJoystick;

	private IGamepad gamepad;
	
	private Thread driveThread;
	private Thread controlThread;
	
	private float startingAngle;
	
	private boolean isRunning;
	
	int i = 0;
	
	private NavX navX;
	
	private Drivetrain drivetrain = ActuatorConfig.getInstance().getDrivetrain();
	
	//private final double FRICTION_FACTOR_SHOP = 8;
	private final double FRICTION_FACTOR_CAFETERIA = 14.37;
	
	private PowerDistributionPanel pdb = new PowerDistributionPanel(0);
	
	public void init() 
	{
		rightJoystick = new HBJoystick(0);
		leftJoystick = new HBJoystick(1);

		gamepad = new Gamepad(2);
		
		navX = SensorConfig.getInstance().getNavX();
		
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
				SmartDashboard.putNumber("Yaw: ", navX.getYaw());
				SmartDashboard.putNumber("Pitch: ", navX.getPitch());
				SmartDashboard.putNumber("Roll: ", navX.getRoll());
				SmartDashboard.putNumber("Shooter Current: ", pdb.getCurrent(6));
				SmartDashboard.putNumber("Agitator Current: ", pdb.getCurrent(3));
				
				if (leftJoystick.getY() > 0.15 || rightJoystick.getY() > 0.15 || leftJoystick.getY() < -0.20 || rightJoystick.getY() < -0.1)
				{
					drivetrain.setSpeed((leftJoystick.getYAxis()), (rightJoystick.getYAxis()));
				}
				else if((leftJoystick.getY() > 0.15 && rightJoystick.getY() < -0.15) || (leftJoystick.getY() < -0.20 && rightJoystick.getY() > 0.1))
				{
					drivetrain.setSpeed((leftJoystick.getYAxis() / 2), (rightJoystick.getYAxis() / 2));
				}
				
				else
				{
					drivetrain.setSpeed(0);
				}
				
				//TODO: Change the drivetrain speed from 0.2 to +0.2 of the velocity so you can drive forward.
				if (rightJoystick.getRawButton(1))
				{
					double heading = Math.round(navX.getYaw()) + 90;
					
					
					
					if(heading > 360)
					{
						heading -= 360;
						
						while((navX.getYaw() + FRICTION_FACTOR_CAFETERIA - 2) > heading)
						{
							System.out.println("Turning to " + heading + "At: " + navX.getYaw());
							drivetrain.setSpeed(0.3, -0.3);
						}	
					}
					
						while((navX.getYaw() + FRICTION_FACTOR_CAFETERIA) < heading)
						{
							System.out.println("Turning to " + heading + "At: " + navX.getYaw());
							drivetrain.setSpeed(0.3, -0.3);
						}	
					
					
					System.out.println("Heading: " + heading);
					
					
					
					
					
					
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
				
				if(gamepad.getButtonValue(ButtonGamepad.ONE))
				{
					ActuatorConfig.getInstance().getAgitator().setSpeed(-0.20);
				}
				/*else if(gamepad.getButtonValue(ButtonGamepad.))
				{
					ActuatorConfig.getInstance().getAgitator().setSpeed(0.4);
				}*/
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
//					ActuatorConfig.getInstance().getAgitator().setSpeed(0);
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
				}		
				else if(gamepad.getButtonValue(ButtonGamepad.SEVEN))
				{
					leftJoystick.setReversed(false);
					rightJoystick.setReversed(false);
				}
				
				/*if(gamepad.getButtonValue(ButtonGamepad.FOUR))
				{
								
				}*/
			}
		}		
	}
	
	public void update(Subject subject) 
	{
		System.out.println("Subject done! " + i);	
	}
}
