package org.hackbots.autonomous;

import org.hackbots.util.Status;

public class AutonStatus
{
	private Status autonStatus;
	
	private static AutonStatus instance;
	
	private AutonStatus()
	{
		autonStatus = Status.IDLE;
	}
	
	public static AutonStatus getInstance()
	{
		if(instance == null)
		{
			instance = new AutonStatus();
		}
		
		return instance;
	}
	
	public Status getStatus()
	{
		return autonStatus;
	}
	
	public void setStatus(Status status)
	{
		autonStatus = status;
	}
}
