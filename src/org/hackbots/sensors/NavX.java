package org.hackbots.sensors;

import com.kauailabs.navx.frc.AHRS;

public class NavX 
{
	private AHRS ahrs;
	
	private final static double GRAVITY = 9.80;
	
	private boolean isEnabled = true;
	
	private double pastYaw = 0;
	
	public NavX(AHRS ahrs)
	{
		this.ahrs = ahrs;
	}
		
	public double getX()
	{
		return ahrs.getWorldLinearAccelX();
	}

	public double getY()
	{
		return ahrs.getWorldLinearAccelY();
	}

	public double getZ()
	{
		return ahrs.getWorldLinearAccelZ() + GRAVITY;
	}

	public void resetDisplacment()
	{
		ahrs.resetDisplacement();
	}
	
	public float getYaw()
	{
		pastYaw = ahrs.getYaw();
		
		if(pastYaw < 0)
		{			
			return (float)ahrs.getYaw() + 360;
		}
		else if(pastYaw > 0)
		{
			return (float)ahrs.getYaw();
		}
		else
		{
			return 0;
		}
	}
	
	public double getPitch()
	{
		return ahrs.getPitch();
	}
	
	public double getRoll()
	{
		return ahrs.getRoll();
	}
	
	public double getRawYaw()
	{
		return ahrs.getYaw();
	}
	
	public double getRate()
	{
		return ahrs.getRate();
	}

	public void enable()
	{
		isEnabled = true;
	}

	public void disable()
	{
		isEnabled = false;
	}
	
	public boolean isEnabled() 
	{
		return isEnabled;
	}
	
	public void reset()
	{
		ahrs.reset();
	}
}
