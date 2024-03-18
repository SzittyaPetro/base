package hu.bme.mit.train.user;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainUserImpl implements TrainUser {

	private TrainController controller;
	private int joystickPosition;

	public TrainUserImpl(TrainController controller) {
		this.controller = controller;
	}

	@Override
	public boolean getAlarmFlag() {
		return false;
	}

	@Override
	public int getJoystickPosition() {
		return joystickPosition;
	}

	@Override
	public void overrideJoystickPosition(int joystickPosition) {
		this.joystickPosition = joystickPosition;
		controller.setJoystickPosition(joystickPosition);
	}


	@Override
	public void SetAlarmState(boolean alarmState)
	{
		this.alarmState = alarmState;
	}

	@Override
	public boolean getAlarmState()
	{
		return alarmState;
	}

	private void checkAlarm()
	{
		if (speedLimit < 0 || speedLimit > 500)
		{
			SetAlarmState(true);
		}
		else if (speedLimit < controller.getReferenceSpeed() * 0.5)
		{
			SetAlarmState(true);
		}
		else
		{
			SetAlarmState(false);
		}
	}
}
