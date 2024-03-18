package hu.bme.mit.train.interfaces;

public interface TrainUser {

	int getJoystickPosition();

	boolean getAlarmState();
	void SetAlarmState(boolean getAlarmState);
	public void overrideSpeedLimit(int speedLimit);
	void overrideJoystickPosition(int joystickPosition);

}
