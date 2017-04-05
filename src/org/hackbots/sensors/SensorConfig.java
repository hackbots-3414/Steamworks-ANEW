package org.hackbots.sensors;

import org.hackbots.sensors.ClockTimer;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

public class SensorConfig 
{
	private static SensorConfig instance;
	
	private NavX navX;
	
	private ClockTimer timer;
	
	private SensorConfig(){}
	
	public void init()
	{
		navX = new NavX(new AHRS(SPI.Port.kMXP));
		timer = new ClockTimer();
	}
	
	public static SensorConfig getInstance()
	{
		if(instance == null)
		{
			instance = new SensorConfig();
		}
		
		return instance;
	}
	
	public NavX getNavX()
	{
		return navX;
	}
	
	public ClockTimer getTimer()
	{
		return timer;
	}
}
