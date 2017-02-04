package org.hackbots.acutator;

public class Servo {
	private static final double SERVO_LATCHED = 1.0;
	private static final double SERVO_UNLATCHED = 0.0;
	
	edu.wpi.first.wpilibj.Servo servo;
	
	public Servo(int channel)
	{
		servo = new edu.wpi.first.wpilibj.Servo(channel);
	}
	
	public void engage() 
	{
		servo.set(SERVO_LATCHED);
	}

	public void disengage() 
	{
		servo.set(SERVO_UNLATCHED);
	}

	public void set(double value) 
	{
		servo.set(value);
	}

	public double get() 
	{
		return servo.get();
	}

}
