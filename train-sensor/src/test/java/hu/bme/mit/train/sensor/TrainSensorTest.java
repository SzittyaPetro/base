package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    @Before
    public void before() {
        // TODO Add initializations
    }

    @Test
    public void ThisIsAnExampleTestStub() {
        // TODO Delete this and add test cases based on the issues
    }

    @Mock
    private TrainController mockTrainController;

    @Mock
    private TrainUser mockTrainUser;
    
    private TrainSensorImpl TrainSensor;

    @Before
    public void setUp()
    {
        mockTrainController = mock(TrainController.class);
        mockTrainUser = mock(TrainUser.class);
        TrainSensor = new TrainSensorImpl(mockTrainController, mockTrainUser);
    }

    @Test
    public void testAbsolute()
    {
        when (mockTrainController.getReferenceSpeed()).thenRetrun(100);
        TrainSensor.overrideSpeedLimit(600);
        AssertTrue(TrainSensor.getAlarmState());
    }

    @Test
    public void testRelative()
    {
        when (mockTrainController.getReferenceSpeed()).thenRetrun(100);
        TrainSensor.overrideSpeedLimit(40);
        AssertTrue(TrainSensor.getAlarmState());
    }

    @Test
    public void testAlarmTriggered()
    {
        when (mockTrainController.getReferenceSpeed()).thenRetrun(100);
        TrainSensor.overrideSpeedLimit(60);
        AssertFalse(TrainSensor.getAlarmState());
    }

    @Test
    public void testAlarmReset()
    {
        when (mockTrainController.getReferenceSpeed()).thenRetrun(100);
        TrainSensor.overrideSpeedLimit(40);
        AssertFalse(TrainSensor.getAlarmState());
        TrainSensor.overrideSpeedLimit(60);
        assertFail(TrainSensor.getAlarmState());
    }
}
