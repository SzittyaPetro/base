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
        TrainSensor = new TrainSensorImpl(mockTrainController, mockTrainUser);
    }

    @Test
    public void ThisIsAnExampleTestStub() {
        // TODO Delete this and add test cases based on the issues
    }

    private TrainController mockTrainController;

    private TrainUser mockTrainUser;
    
    private TrainSensorImpl TrainSensor;

    @Test
    public void testAbsolute()
    {
        when (mockTrainController.getReferenceSpeed()).thenReturn(100);
        TrainSensor.overrideSpeedLimit(600);
        verify(true, TrainSensor.getAlarmState());
    }

    @Test
    public void testRelative()
    {
        when (mockTrainController.getReferenceSpeed()).thenReturn(100);
        TrainSensor.overrideSpeedLimit(40);
        verify(true, TrainSensor.getAlarmState());
    }

    @Test
    public void testAlarmTriggered()
    {
        when (mockTrainController.getReferenceSpeed()).thenReturn(100);
        TrainSensor.overrideSpeedLimit(60);
        verify(false,TrainSensor.getAlarmState());
    }

    @Test
    public void testAlarmReset()
    {
        when (mockTrainController.getReferenceSpeed()).thenReturn(100);
        TrainSensor.overrideSpeedLimit(40);
        verify(false,TrainSensor.getAlarmState());
        TrainSensor.overrideSpeedLimit(60);
        verify(false,TrainSensor.getAlarmState());
    }
}
