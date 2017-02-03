package org.hackbots.sensors;

import edu.wpi.first.wpilibj.CameraServer;

public class MicrosoftLifeCam {

	CameraServer server;
	
	public MicrosoftLifeCam(int usbPort){
		
		server = CameraServer.getInstance();
		server.startAutomaticCapture(usbPort);
	}
	
}
