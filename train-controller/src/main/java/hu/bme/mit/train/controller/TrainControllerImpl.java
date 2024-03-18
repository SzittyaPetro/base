package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private ScheduledExecutorService scheduler;

	public TrainControllerImpl() {
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new UpdateSpeedTask(), 0, 1, TimeUnit.SECONDS);
	}

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}
	@Override
	public void setEmergencyBrake(boolean EmergencyBrake){referenceSpeed=0;}

	class UpdateSpeedTask implements Runnable {
		@Override
		public void run() {
			updateReferenceSpeed();
	}

	private void updateReferenceSpeed() {
		if (step > 0) {
			referenceSpeed += step;
		}

		else if (step < 0) {
			referenceSpeed-= Math.abs(step);
		}

		if (referenceSpeed < 0)
		{
			referenceSpeed = 0;
		}
	}
}
}
