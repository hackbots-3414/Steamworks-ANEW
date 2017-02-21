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
				/*SmartDashboard.putNumber("Yaw: ", navX.getRawYaw());
				SmartDashboard.putNumber("Pitch: ", navX.getPitch());
				SmartDashboard.putNumber("Roll: ", navX.getRoll());*/
				
				if (leftJoystick.getY() > 0.1 || rightJoystick.getY() > 0.1 || leftJoystick.getY() < -0.1 || rightJoystick.getY() < -0.1)
				{
					drivetrain.setSpeed((leftJoystick.getYAxis()), (rightJoystick.getYAxis()));
				}
				else if((leftJoystick.getY() > 0.1 && rightJoystick.getY() < -0.1) || (leftJoystick.getY() < -0.1 && rightJoystick.getY() > 0.1))
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
		}	
	}
}
	public class ControlThread implements Runnable
	{
		public void run()
		{
			while(isRunning)
			{				
				if(gamepad.getButtonValue(ButtonGamepad.SEVEN))
				{
					leftJoystick.setReversed(true);
					rightJoystick.setReversed(true);
				}		
				else if(gamepad.getButtonValue(ButtonGamepad.EIGHT))
				{
					leftJoystick.setReversed(false);
					rightJoystick.setReversed(false);
				}				
			}
		}		
	}
	
	public void update(Subject subject) 
	{
		System.out.println("Subject done! " + i);	
	}
}
