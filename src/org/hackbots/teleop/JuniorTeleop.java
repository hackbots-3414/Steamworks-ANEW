package org.hackbots.teleop;

import org.hackbots.acutator.ActuatorConfig;
import org.hackbots.acutator.Drivetrain;
import org.hackbots.autonomous.AutonStatus;
import org.hackbots.sensors.ClockTimer;
import org.hackbots.sensors.Gamepad;
import org.hackbots.sensors.HBJoystick;
import org.hackbots.sensors.IGamepad;
import org.hackbots.sensors.SensorConfig;
import org.hackbots.util.ButtonGamepad;
import org.hackbots.util.Status;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class JuniorTeleop implements ITeleop
{
	private HBJoystick rightJoystick;
	private HBJoystick leftJoystick;
	
	private JoystickButton button;
	
//	private Button killButtonLeft;
//	private Button killButtonRight;
	
	private IGamepad gamepad;
	
	private Thread driveThread;
	
	private boolean isRunning;
	
	private Drivetrain drivetrain = ActuatorConfig.getInstance().getDrivetrain();
	
	private double startYaw;
	private double endYaw;
	
	private PowerDistributionPanel pdb;
	private double climberMaxCurrent = 0;
	
	//private SendableChooser<Object> parkingChooser;
	
	public void init() 
	{
		rightJoystick = new HBJoystick(0);
		leftJoystick = new HBJoystick(1);
		
		AutonStatus.getInstance().setStatus(Status.CANCELED);
		System.out.println("Killing Auton! -----------------------------------------------------");
		System.out.println("Interrupting Timer: " + ClockTimer.getInstance().interrupt());
		isRunning = false;

		gamepad = new Gamepad(2);

		
		button = new JoystickButton(leftJoystick, 11);
		
//		parkingChooser = new SendableChooser<Object>();
//		parkingChooser.addObject("Yes", true);
//		parkingChooser.addObject("No", false)
//		SmartDashboard.putData("Parking Brake", parkingChooser);
		
		pdb = SensorConfig.getInstance().getPDB();
		
		driveThread = new Thread(new DriveThread());
		
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
				//System.out.println("Battery Voltage: " + pdb.getVoltage());
				
				SmartDashboard.putNumber("Left Encoder - Teleop", ActuatorConfig.getInstance().getLeftEncoder().getSensorCollection().getQuadraturePosition() * (0.000122));//
				SmartDashboard.putNumber("Right Encoder - Teleop", ActuatorConfig.getInstance().getRightEncoder().getSensorCollection().getQuadraturePosition() * (-0.000122));
	
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

					
				if(gamepad.getButtonState(1))
				{
					ActuatorConfig.getInstance().getAgitator().setSpeed(-0.25);//-0.3 //-.02
				}
				else
				{
					ActuatorConfig.getInstance().getAgitator().setSpeed(0);
				}
				
				if (gamepad.getButtonState(3))
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
					//System.out.println("Climber Max Current: " + climberMaxCurrent);
				}

				if(gamepad.getButtonState(2) && gamepad.getButtonState(6))
				{
					ActuatorConfig.getInstance().getShooter().setSpeed(0.75);//85
				}
				else if (gamepad.getButtonState(2))
				{
					ActuatorConfig.getInstance().getShooter().setSpeed(1.00);//95 //85
				}
				else 
				{
					ActuatorConfig.getInstance().getShooter().setSpeed(0);
				}
				
				if(gamepad.getButtonState(4))
				{
					ActuatorConfig.getInstance().getIntakeMotor().setSpeed(-1);
				}
				else
				{
					ActuatorConfig.getInstance().getIntakeMotor().setSpeed(0);
				}
				
				if(gamepad.getButtonState(7))
				{
					ActuatorConfig.getInstance().getGearManipulator().set(Value.kForward);
				}
				else if (gamepad.getButtonState(8))
				{
					ActuatorConfig.getInstance().getGearManipulator().set(Value.kReverse);
				}
				
				if(gamepad.getButtonState(6))
				{
					ActuatorConfig.getInstance().getGearTopSolenoid().set(Value.kForward);
				}
				else if(gamepad.getButtonState(5))
				{
					ActuatorConfig.getInstance().getGearTopSolenoid().set(Value.kReverse);
				}
				
				if(rightJoystick.getRawButton(2) || leftJoystick.getRawButton(2))
				{
					leftJoystick.setReversed(true);
					rightJoystick.setReversed(true);
					System.out.println("Reversing...");
				//	rightJoystick = new HBJoystick (1);
				//	leftJoystick = new HBJoystick (0);
				}		
				else if(rightJoystick.getRawButton(1) || leftJoystick.getRawButton(1))
				{
					leftJoystick.setReversed(false);
					rightJoystick.setReversed(false);
					
					//System.out.println("Not reversing...");
				}
				
				try 
				{
					Thread.sleep(1);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}	
		}
	}
}
