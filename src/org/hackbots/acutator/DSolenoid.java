package org.hackbots.acutator;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class DSolenoid
{
	private DoubleSolenoid dSol;
	
	public DSolenoid(DoubleSolenoid dSol)
	{
		this.dSol = dSol;
	}
	
	public void setValue(boolean value)
	{
		if(value == true)
		{
			dSol.set(DoubleSolenoid.Value.kForward);
		} 
		else 
		{
			dSol.set(DoubleSolenoid.Value.kReverse);
		}
	}
	
	public boolean getValue()
	{
		if(dSol.get() == (DoubleSolenoid.Value.kForward))
		{
			return true;
		} 
		else
		{
			return false;
		}
		
	}
	
	public void engage()
	{
		dSol.set(DoubleSolenoid.Value.kForward);
	}
	
	public void disengage()
	{
		dSol.set(DoubleSolenoid.Value.kReverse);
	}

}
