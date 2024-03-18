package hu.bme.mit.train.interfaces;

public interface TrainSensor {

	int getSpeedLimit();

	void overrideSpeedLimit(int speedLimit);

	boolean getAlarmState();
	void SetAlarmState(boolean getAlarmState);
}
