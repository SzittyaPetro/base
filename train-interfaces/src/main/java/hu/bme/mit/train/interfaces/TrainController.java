package hu.bme.mit.train.interfaces;

public interface TrainController {

	void followSpeed();

	int getReferenceSpeed();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);

	public void setEmergencyBrake(boolean EmergencyBrake);

	public void startTrainTickThread(int ThickInterval);

	public void stopTrainTickThread();

}
