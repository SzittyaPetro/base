package hu.bme.mit.train.interfaces;

import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;
import java.util.Date;

public interface TrainController {

	Table<Date, Integer, Integer> tachograph = HashBasedTable.create();
	void followSpeed();

	int getReferenceSpeed();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);

	public void setEmergencyBrake(boolean EmergencyBrake);
	
	void storeTachograph();

	public void startTrainTickThread(int ThickInterval);

	public void stopTrainTickThread();

}
