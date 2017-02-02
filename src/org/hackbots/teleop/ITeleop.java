package org.hackbots.teleop;

public interface ITeleop 
{
	/**
	 * Updates the current teleop mode
	 */
	public void update();
	
	/**
	 * Initializes the needed resources
	 */
	public void init();
}
