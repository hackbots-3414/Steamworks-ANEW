package org.hackbots.teleop;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.sensors.Gamepad;
import org.hackbots.sensors.HBJoystick;
import org.hackbots.sensors.IGamepad;
import org.hackbots.sensors.SensorConfig;
import org.hackbots.util.ButtonGamepad;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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
	
	private double startYaw;
	private double endYaw;
	
	private PowerDistributionPanel pdb = new PowerDistributionPanel(23);
	private double climberMaxCurrent = 0;
	
	private Compressor compressor = new Compressor();
	
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
				SmartDashboard.putNumber("Left Encoder - Teleop", ActuatorConfig.getInstance().getLeftEncoder().getEncPosition() * (0.000122));//
				SmartDashboard.putNumber("Right Encoder - Teleop", ActuatorConfig.getInstance().getRightEncoder().getEncPosition()* (-0.000122));
	
//				SmartDashboard.putBoolean("Joys Reveresed: ", leftJoystick.isReversed());
//				drivetrain.setSpeed((leftJoystick.getYAxis()), (rightJoystick.getYAxis()));
				
				//ActuatorConfig.getInstance().getDrivetrain().makeHeadingGreatAgain();
				
				if (leftJoystick.getY() > 0.15 || rightJoystick.getY() > 0.15 || leftJoystick.getY() < -0.20 || rightJoystick.getY() < -0.1)
				{
					startYaw = SensorConfig.getInstance().getNavX().getRawYaw();
					double leftCorrect = 0;
					double rightCorrect = 0;
					
					if (endYaw > (startYaw)) 
					{
					
						//drivetrain.setSpeed((leftJoystick.getYAxis() / 2), (rightJoystick.getYAxis() / 2) + 0.2);//Add Gyro 
						System.out.println("Veering Right Telop");
						rightCorrect = 0.2;
					}
					else if (endYaw < (startYaw)) 
					{	
						//drivetrain.setSpeed((leftJoystick.getYAxis() / 2) + 0.2, (rightJoystick.getYAxis() / 2));//Add Gyro 
						System.out.println("Veering Left Telop");
						leftCorrect = 0.2;
					}
					else
					{
						leftCorrect = 0;
						rightCorrect = 0;
					}
					
				//	drivetrain.setSpeed((leftJoystick.getYAxis() / 2) + leftCorrect, (rightJoystick.getYAxis() / 2) + rightCorrect);//Add Gyro 
					drivetrain.setSpeed((leftJoystick.getYAxis()) + leftCorrect, (rightJoystick.getYAxis()) + rightCorrect);
					endYaw = SensorConfig.getInstance().getNavX().getRawYaw();
					
					SmartDashboard.putNumber("Statrt Yaw Tle", startYaw);
					SmartDashboard.putNumber("End Yaw Tle", endYaw);
				
				}
				else if((leftJoystick.getY() > 0.15 && rightJoystick.getY() < -0.15) || (leftJoystick.getY() < -0.20 && rightJoystick.getY() > 0.1))
				{
					if(rightJoystick.isReversed() && leftJoystick.isReversed())
					{
						drivetrain.setSpeed((leftJoystick.getYAxis() / 2), (rightJoystick.getYAxis() / 2));
						//drivetrain.setSpeed((leftJoystick.getYAxis()), (rightJoystick.getYAxis()));
					}
					
					else
					{
						drivetrain.setSpeed((leftJoystick.getYAxis() / 2), (rightJoystick.getYAxis() / 2));
						//drivetrain.setSpeed((leftJoystick.getYAxis()), (rightJoystick.getYAxis()));
					}
				}
				
				else
				{
					drivetrain.setSpeed(0);
				}

//				
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
					//startingAngle = Math.round(navX.getRawYaw());
					
//					while (rightJoystick.getRawButton(1))//change the button
//					{
//						if(navX.getYaw() > startingAngle - 1)
//						{
//							drivetrain.setSpeed(0.2, -0.2);
//						}
//						else if (navX.getYaw() < startingAngle + 1)
//						{
//							drivetrain.setSpeed(-0.2, 0.2);
//						}
//					}

					
				if(gamepad.getButtonValue(ButtonGamepad.ONE))
				{
					ActuatorConfig.getInstance().getAgitator().setSpeed(-0.20);//-0.3
				}
				else
				{
					ActuatorConfig.getInstance().getAgitator().setSpeed(0);
				}
				
				if (gamepad.getButtonValue(ButtonGamepad.THREE))
					//&&
						//(ActuatorConfig.getInstance().getClimberMotor().getCurrentMotorOne() < 50
						//|| ActuatorConfig.getInstance().getClimberMotor().getCurrentMotorTwo() < 50))
				{
					double climberCurrentOne = pdb.getCurrent(5);
					double climberCurrentTwo = pdb.getCurrent(9);
					
					ActuatorConfig.getInstance().getClimberMotor().setSpeed(-1);
					System.out.println("Climber Current One: " + climberCurrentOne);//Channels: 9,5
					System.out.println("Climber Current Two: " + climberCurrentTwo);
					
					if(climberCurrentOne > climberMaxCurrent)
					{
						climberMaxCurrent = climberCurrentOne;
					}
					else if(climberCurrentTwo > climberMaxCurrent)
					{
						climberMaxCurrent = climberCurrentTwo;
					}
				}
				else
				{
					ActuatorConfig.getInstance().getClimberMotor().setSpeed(0);
					System.out.println("Climber Max Current: " + climberMaxCurrent);
				}

				if(gamepad.getButtonValue(ButtonGamepad.TWO) && gamepad.getButtonValue(ButtonGamepad.SIX))
				{
					ActuatorConfig.getInstance().getShooter().setSpeed(0.85);
				}
				else if (gamepad.getButtonValue(ButtonGamepad.TWO))
				{
					ActuatorConfig.getInstance().getShooter().setSpeed(0.95);
				}
				else 
				{
					ActuatorConfig.getInstance().getShooter().setSpeed(0);
				}
				
				if(gamepad.getButtonValue(ButtonGamepad.FOUR))
				{
					ActuatorConfig.getInstance().getIntakeMotor().setSpeed(-1);
				}
				else
				{
					ActuatorConfig.getInstance().getIntakeMotor().setSpeed(0);
				}
				
				if(gamepad.getButtonValue(ButtonGamepad.SEVEN))
				{
					ActuatorConfig.getInstance().getGearManipulator().set(Value.kForward);
				}
				else if (gamepad.getButtonValue(ButtonGamepad.EIGHT))
				{
					ActuatorConfig.getInstance().getGearManipulator().set(Value.kReverse);
				}
				
				if(gamepad.getButtonValue(ButtonGamepad.SIX))
				{
					ActuatorConfig.getInstance().getGearTopSolenoid().set(Value.kForward);
				}
				else if(gamepad.getButtonValue(ButtonGamepad.FIVE))
				{
					ActuatorConfig.getInstance().getGearTopSolenoid().set(Value.kReverse);
				}
				
				if(rightJoystick.getRawButton(2) || leftJoystick.getRawButton(2))
				{
					leftJoystick.setReversed(true);
					rightJoystick.setReversed(true);
					System.out.println("Reversing...");
				}		
				else if(rightJoystick.getRawButton(1) || leftJoystick.getRawButton(1))
				{
					leftJoystick.setReversed(false);
					rightJoystick.setReversed(false);
					System.out.println("Not reversing...");
				}
				/*if((rightJoystick.getRawButton(3)|| leftJoystick.getRawButton(3)) 
				&& (leftJoystick.getY() > 0.15 || rightJoystick.getY() > 0.15))
				{
					NavX navx = SensorConfig.getInstance().getNavX();
					double currentYaw;
					double startYaw = navx.getRawYaw();
					currentYaw = navx.getRawYaw();
					SmartDashboard.putNumber("Current Yaw Tle", currentYaw);
					SmartDashboard.putNumber("Start Yaw Tle", startYaw);
					//drivetrain.setSpeed(leftJoystick.getYAxis() / 2, rightJoystick.getYAxis() / 2);
					//ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, speed);
					if (currentYaw > (startYaw + 1)) 
					{
						// Veering left, so slow down right
						//System.out.println("Veering left");
						//ActuatorConfig.getInstance().getDrivetrain().setSpeed(speed, (speed - .12));	
						ActuatorConfig.getInstance().getDrivetrain().setSpeed(0.5, (0.5- .12));
					}
					else if (currentYaw < (startYaw + 1)) 
					{	
						// Veering right, so slow down left
						//System.out.println("Veering right");
						//ActuatorConfig.getInstance().getDrivetrain().setSpeed((speed - .12),speed );
						ActuatorConfig.getInstance().getDrivetrain().setSpeed((0.5- .12), 0.5);
					}
				}*/
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
//				if(gamepad.getButtonValue(ButtonGamepad.EIGHT))
//				{
//					leftJoystick.setReversed(true);
//					rightJoystick.setReversed(true);
//					System.out.println("Reversing...");
//				}		
//				else if(gamepad.getButtonValue(ButtonGamepad.SEVEN))
//				{
//					leftJoystick.setReversed(false);
//					rightJoystick.setReversed(false);
//				}
			}
		}		
	}
	
	private boolean checkCompressor()
	{
		System.out.println("Compreesor Enabled: " + compressor.enabled());
		
		return compressor.enabled();
	}
}
