package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

import java.util.Date;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private Thread trainThickThread;
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
	public void startTrainTickThread(int ThickInterval) throws
	IllegalArgumentException{
		if (trainThickThread != null){
			throw new IllegalArgumentException("Thread exists already");
		}
		this.trainThickThread= new Thread(()->{
			while (true){
				synchronized(this){
					followSpeed();
				}
				try{
					Thread.sleep(ThickInterval);
				}catch(InterruptedException e){
					System.out.println("Thread interrupted");
				}
			}
		});
	}
	@Override
	public void stopTrainTickThread(){
		if(trainThickThread!=null){
			trainThickThread.interrupt();
			trainThickThread=null;
			System.out.println("Thread stopped");
		}
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
	@Override
	public void storeTachograph(){
		tachograph.put(new Date(),this.step,referenceSpeed);
	}


}
