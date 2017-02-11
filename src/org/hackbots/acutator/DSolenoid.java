package org.hackbots.acutator;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
public class DSolenoid
{
	private DoubleSolenoid dSol;
	
	public DSolenoid(DoubleSolenoid _dSol)
	{
		dSol = _dSol;
	}
	public void setValue(boolean value)
	{
		if (value == true)
		{
			dSol.set(DoubleSolenoid.Value.kForward);
		} else {
			dSol.set(DoubleSolenoid.Value.kReverse);
		}
	}
	public boolean getValue()
	{
		if (dSol.get()== (DoubleSolenoid.Value.kForward))
		{
			return true;
		} else {
			return false;
		}
		
	}

}
2