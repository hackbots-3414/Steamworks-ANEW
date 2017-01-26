package org.hackbots.sensors;

public interface IJoystick {
	public double getX();

	public double getY();

	public double getTwist();

	public double getSwitch();

//	public boolean getButtonValue(EButtonJoystick value);
}
