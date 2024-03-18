package hu.bme.mit.train.interfaces;

public interface TrainUser {

	int getJoystickPosition();

	boolean getAlarmState();
	void SetAlarmState(boolean getAlarmState);

	void overrideJoystickPosition(int joystickPosition);

}
