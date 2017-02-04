package org.hackbots.sensors;

import edu.wpi.first.wpilibj.Encoder;

public class QuadEncoder {
	Encoder encoder;
	boolean enabled;
	
	public QuadEncoder(int channelA, int channelB)
	{
		encoder = new Encoder(channelA, channelB);
		encoder.reset();
		
		enabled = true;
	}

	public double getCount() {
		if(enabled)
		{
			return encoder.get();
		} else
		{
			return 0;
		}
	}

	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}

	public void resetCount() {
		encoder.reset();
	}

}
