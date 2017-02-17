package mockedTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import main.Car;
import main.CarImpl;
import main.Position;
import main.UltrasoundSensor;
import newErrorHandling.StreetLengthException;

public class MockScenarioTest {
    
    
    @Mock
    Position position;
    private CarImpl car; //We are re-using the class by making it a Field
    private UltrasoundSensor uOne;
    private UltrasoundSensor uTwo;
    
    
    @Before
    public void setup(){
        /**
         * Initialize the mocks
         */
        MockitoAnnotations.initMocks(this);
        car = new CarImpl(uOne, uTwo, 0, false, false);
    }
    
    /**
     * Scenario 1 - The car moves until it finds an empty parking spot.
     * The car parks when it finds the empty spot.
     * Starting from position 0;
     */
    @Test
    public void carShouldExecutePreProgrammedPark() throws StreetLengthException {
        //moves the car forward until a spot is found
        //when a spot is found, it moves forward for 5m and parks in the spot
        car.park(2, 2);
        /**
         * Set up the expectations
         */
        
        //checks if the car is parked
        when(position.getParkingStatus()).thenReturn(true);
        Boolean result = position.getParkingStatus();
        assertTrue(result);
        /**
         * To verify that the mocking worked we use a static method from the class Mockito
         *
         */
        verify(position).getParkingStatus();
    }
    
    /**
     * Scenario 2 - The car is parked. It unparks and thengoes to the end of the street.
     * Moves back 10 meters and tries to park again.
     */
    @Test
    public void carUnparksAndMoves() throws StreetLengthException {
        Car car = new CarImpl(null, null, 0, true, false);
        new UltrasoundSensor();
        new UltrasoundSensor();
        
        //check if car is parked
        when(position.getParkingStatus()).thenReturn(true);
        
        //unpark car
        car.unpark();
        when(position.getParkingStatus()).thenReturn(false);
        
        //go to the end of the street
        when(position.getPositionOnStreet()).thenReturn(499);
        int intRes = position.getPositionOnStreet();
        assertEquals(499, intRes);
        verify(position).getPositionOnStreet();
        
        //go back 10 meters and try to park the car again
        when(position.getPositionOnStreet()).thenReturn(490);
        position = car.moveBackward(position);
        car.park(2, 2);
        when(position.getParkingStatus()).thenReturn(true);
        
        /**
         * To verify that the mocking worked we use a static method from the class Mockito
         *
         */
        verify(position).getParkingStatus();
    }
    
    
    /**
     * Scenario 3
     * 1. Car out of street boundaries
     * 2. Car is put back on the street manually.
     * 3. The car parks
     * 4. The car tries to park while parked
     * 5. The car unparks
     * 6. The car tries to unpark while unparked
     * 7. Car moves backward
     * 8. The car tries to park, but never finds a spot.
     */
    
    @SuppressWarnings("unchecked")
    @Test(expected = StreetLengthException.class)
    public void isEmptyShouldThrowAnExceptionDistanceoutOfScope() throws StreetLengthException {
        
        when(position.getPositionOnStreet()).thenReturn(-15);
        //whereIs doesn't allow out-of-Bounds check
        car.getPosition();
        
        car.setPosition(2);
        car.whereIs();
        
        
        car.park(2,2);
        car.park(3,3);
        car.unpark();
        car.unpark();
        car.moveBackward();
        car.park(3,3);
        verify(position).getParkingStatus();
        
        //TODO: SENSORS RANDOM CHECK && OUT OF BOUNDS CHECK && CAR OUT OF BOUNDS CHECK
        
        /**
         * Set up the expectations, we don't need to verify or assert any value.
         */
        when(position.getParkingStatus()).thenThrow(StreetLengthException.class);
        
    }
    
    /**
     * TC3. Sensor returning noisy output
     * @throws StreetLengthException
     * @throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void isEmptyShouldThrowAnExceptionDistanceoutOfScope2() throws IllegalArgumentException, StreetLengthException{
        int temp[] = {210, 200, 200, 500, 400, 700, 900, 200, 300};
        UltrasoundSensor.setUltrasoundSensorOne(temp);
        UltrasoundSensor.setUltrasoundSensorTwo(temp);
        
        Car car = new CarImpl(uOne, uTwo, 0, false, false);
        
        car.isEmpty(uOne, uTwo); //here the sensors need out of bound input
    }
    
    
    /**
     * TC4. Sensors have inconsistent values.
     * @throws StreetLengthException
     * @throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void isEmptyHaveInconsistentValues() throws IllegalArgumentException, StreetLengthException{
        int temp[] = {2,2,2,2,2};
        int temp2[] = {10,10,10,10,10};
        UltrasoundSensor.setUltrasoundSensorOne(temp);
        UltrasoundSensor.setUltrasoundSensorTwo(temp2);
        
        Car car = new CarImpl(uOne, uTwo, 0, false, false); 
        
        car.isEmpty(uOne, uTwo); 
    }
    
    /**
     * TC5. Sensors have inconsistent values.
     * @throws StreetLengthException
     * @throws IllegalArgumentException
     */
    @Test
    public void moveBackwardThrowsException() throws StreetLengthException{
    	when(position.getPositionOnStreet()).thenReturn(-15);
        car.moveBackward();
        new Position(750, false, false);
    }
    
    /**
     * TC5. Sensors have inconsistent values.
     * @throws StreetLengthException
     * @throws IllegalArgumentException
     */
    @Test
    public void moveBackwardThrowsException2() throws StreetLengthException{
    	//when(position.getPositionOnStreet()).thenReturn(-15);
        Position pos = new Position(750, false, false);
        car.moveBackward(pos);
    }
    
    @Test
    public void whereIsException() throws StreetLengthException {
    	 when(position.getPositionOnStreet()).thenReturn(750);
         //whereIs doesn't allow out-of-Bounds check
         
         car.whereIs();
        
    }
    
    @Test(expected = StreetLengthException.class)
    public void moveForwardException() throws StreetLengthException {
        car.moveForward(750);
        
    }
    @Test(expected = StreetLengthException.class)
    public void moveForwardException2() throws StreetLengthException {
        car.moveForward(-200);
        
    }
}
