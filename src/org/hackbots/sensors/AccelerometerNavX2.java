package org.hackbots.sensors;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AccelerometerNavX2 extends SampleRobot{
	AHRS ahrs;
	  RobotDrive Robot;
	  Joystick stick;

	  public AccelerometerNavX2() {
	    Robot = new RobotDrive(0, 1);
	    Robot.setExpiration(0.1);
	    stick = new Joystick(0);
	    try {
	        /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
	        /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
	        /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
	        ahrs = new AHRS(SPI.Port.kMXP); 
	    } catch (RuntimeException ex ) {
	        DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
	    }
	  }

	  /**
	   * Drive left & right motors for 2 seconds then stop
	   */
	  public void autonomous() {
	      Robot.setSafetyEnabled(false);
	      Robot.drive(-0.5, 0.0);	// drive forwards half speed
	      Timer.delay(2.0);		//    for 2 seconds
	      Robot.drive(0.0, 0.0);	// stop robot
	  }

	  /**
	   * Runs the motors with arcade steering.
	   */

	  public void operatorControl() {
	      Robot.setSafetyEnabled(true);
	      while (isOperatorControl() && isEnabled()) {

	          boolean motionDetected = ahrs.isMoving();
	          SmartDashboard.putBoolean("MotionDetected", motionDetected);
	          
	          try {
	              Robot.mecanumDrive_Cartesian(stick.getX(), stick.getY(), stick.getTwist(),0);
	          } catch( RuntimeException ex ) {
	              String err_string = "Drive system error:  " + ex.getMessage();
	              DriverStation.reportError(err_string, true);
	          }
	          Timer.delay(0.005);		// wait for a motor update time
	      }
	  }

	  /**
	   * Runs during test mode
	   */
	  public void test() {
	  }
}
