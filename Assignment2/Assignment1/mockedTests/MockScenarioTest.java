package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

import main.Car;
import main.Position;
import main.UltrasoundSensor;
import newErrorHandling.StreetLengthException;

public class MockScenarioTest {

	
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
    	Car car = new Car(null, null, 0, true, false);
    	UltrasoundSensor uOne = new UltrasoundSensor();
    	UltrasoundSensor uTwo = new UltrasoundSensor();
    	
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
     * 7. The car tries to park, but never finds a spot.
     */
    
    @SuppressWarnings("unchecked")
	@Test(expected = StreetLengthException.class)
    public void isEmptyShouldThrowAnExceptionDistanceoutOfScope() throws StreetLengthException {
    	when(position.getPositionOnStreet()).thenReturn(-15);
    	car.whereIs();
    	when(position.getPositionOnStreet()).thenReturn(2);
    	car.park(2,2);
    	car.park(3,3);
    	car.unpark();
    	car.unpark();
    	car.park(3,3);
    	
    	/**
    	 * Set up the expectations, we don't need to verify or assert any value.
    	 */
    	when(position.getParkingStatus()).thenThrow(StreetLengthException.class);
    	
    }
}
