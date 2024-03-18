package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    @Before
    public void before() {
        mockTrainController = mock(TrainController.class);
        mockTrainUser = mock(TrainUser.class);
        trainSensor = new TrainSensorImpl(mockTrainController, mockTrainUser);
    }

    @Test
    public void ThisIsAnExampleTestStub() {
        // TODO Delete this and add test cases based on the issues
    }

    private TrainController mockTrainController;

    private TrainUser mockTrainUser;
    
    private TrainSensorImpl trainSensor;

    @Test
    public void testAbsolute()
    {
        when (mockTrainController.getReferenceSpeed()).thenReturn(400);
        trainSensor.overrideSpeedLimit(600);
        verify(mockTrainUser,times(1)).SetAlarmState(true);
    }

    @Test
    public void testRelative()
    {
        when (mockTrainController.getReferenceSpeed()).thenReturn(100);
        trainSensor.overrideSpeedLimit(40);
        verify(mockTrainUser,times(1)).SetAlarmState(true);
    }

    @Test
    public void testAlarmTriggered()
    {
        when (mockTrainController.getReferenceSpeed()).thenReturn(100);
        trainSensor.overrideSpeedLimit(60);
        verify(mockTrainUser,times(1)).SetAlarmState(false);
    }

    @Test
    public void testAlarmReset()
    {
        when (mockTrainController.getReferenceSpeed()).thenReturn(100);
        trainSensor.overrideSpeedLimit(40);
        verify(mockTrainUser,times(1)).SetAlarmState(true);
        trainSensor.overrideSpeedLimit(60);
        verify(mockTrainUser,times(1)).SetAlarmState(false);
    }
}
