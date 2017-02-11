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
	
	/**
	 * Returns values in between -180 and 180
	 */
	public double getHardCount()
	{
		if (isEnabled)
		{
			return ahrs.getYaw();
		} else
		{
			return 0;
		}
	}
	
	public float getSoftCount()
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
	
	public double getYaw()
	{
		return ahrs.getYaw();
	}

	public void enable()
	{
		isEnabled = true;
	}

	public void disable()
	{
		isEnabled = false;
	}

	public void softResetCount()
	{
		pastYaw += ahrs.getYaw();
		ahrs.reset();
	}
	
	public void hardResetCount()
	{
		pastYaw = ahrs.getYaw();
		ahrs.reset();
	}
	
	public double getRate()
	{
		return ahrs.getRate();
	}
	
	public double getPitchRate()
	{
		return ahrs.getRawGyroY();
	}
	
	public void reset()
	{
		ahrs.reset();
	}
}
