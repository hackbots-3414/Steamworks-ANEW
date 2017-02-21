package org.hackbots.sensors;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

public class SensorConfig 
{
	private static SensorConfig instance;
	
	private NavX navX;
	
	private SensorConfig(){}
	
	public void init()
	{
		navX = new NavX(new AHRS(SPI.Port.kMXP));
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
}
