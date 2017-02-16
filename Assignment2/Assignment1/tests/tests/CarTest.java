package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import main.Car;
import main.Position;
import newErrorHandling.StreetLengthException;

public class CarTest {
    
    
    @Mock
    Position position;
    private Car car; //We are re-using the class by making it a Field
    
    @Before
    public void setup(){
        /**
         * Initialize the mocks
         */
        MockitoAnnotations.initMocks(this);
        car = new Car(null, null, 0, false, false);
    }
    
    /**
     * TC1 The car executes the pre-programmed reverse parallel parking.
     */
    @Test
    public void carShouldExecutePreProgrammedPark() throws StreetLengthException {
        //Car car = new Car(null, null, 0, false, false);
        car.park(2, 2);
        /**
         * Set up the expectations
         */
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
     * TC2 The car is already parked
     */
    @Test
    public void carisNotParked() throws StreetLengthException {
        Car car = new Car(null, null, 0, true, false);
        car.park(2, 2);
        /**
         * Set up the expectations
         */
        when(position.getParkingStatus()).thenReturn(false);
        Boolean result = position.getParkingStatus();
        assertFalse(result);
        /**
         * To verify that the mocking worked we use a static method from the class Mockito
         *
         */
        verify(position).getParkingStatus();
    }
    
    
    /**
     * TC3 the car never finds parking spot
     */
    
    @SuppressWarnings("unchecked")
    @Test(expected = StreetLengthException.class)
    public void isEmptyShouldThrowAnExceptionDistanceoutOfScope() throws StreetLengthException {
        car.park(3,3);
        /**
         * Set up the expectations
         */
        when(position.getParkingStatus()).thenThrow(StreetLengthException.class);
        
    }
    
    
}
