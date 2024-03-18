package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;
	private boolean alarmState;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
		this.alarmState = false;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		checkAlarm();
		controller.setSpeedLimit(speedLimit);
	}

	@Override
	public void SetAlarmState(boolean alarmState)
	{
		this.user.SetAlarmState(alarmState);
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
