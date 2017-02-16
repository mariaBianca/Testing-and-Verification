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
import newErrorHandling.StreetLengthException;

public class UnParkMockedTest {

	@Mock
	Position position;
	
	@Spy
	Car car = new Car(null, null, 0, true, false);
	
	@Before
	public void setup(){
		/**
		 * Initialize the mocks
		 */
        MockitoAnnotations.initMocks(this);
    }
	 
	/**
	 * Testing how the car behaves when trying to unpark if it's parked
	 * The Car position is mocked.
	 * The car parked status starts as false.
	 * The parking status should be false if successfull.
	 */
    @Test
    public void carIsParkedBehaviour() {
    	//Car car = new Car(null, null, 0, true, false);
    	car.unpark();
    	when(position.getParkingStatus()).thenReturn(false);
    	Boolean result = position.getParkingStatus();
    	//assertTrue(result);
    	/**
    	 * To verify that the mocking worked we use a static method from the class Mockito
    	 * 
    	 */
    	verify(position).getParkingStatus();
    }
    
    
    /**
	 * Testing how the car behaves when trying to unpark if it's not parked
	 * The Car position is mocked.
	 * The car parked status starts as false.
	 * The parking status should be false if successfull.
	 */
    @Test
    public void carIsNotParkedBehavior() {
    	Car car = new Car(null, null, 0, false, false);
    	car.unpark();
    	when(position.getParkingStatus()).thenReturn(false);
    	Boolean result = position.getParkingStatus();
    	/**
    	 * To verify that the mocking worked we use a static method from the class Mockito
    	 * 
    	 */
    	verify(position).getParkingStatus();
    }
    
    /**
	 * Testingif the car turns left when unparking
	 * The Car position is mocked.
	 * The car parked status starts as false.
	 * The parking status should be false if successfull.
	 */
    @Test
    public void carTurnsLeftBehaviour() {
    	//Car car = new Car(null, null, 0, true, false);
    	car.unpark();
    	
    	//Mockito
    	verify(this.car, atLeastOnce()).steerLeft();
    	when(car.steerLeft()).thenReturn("The car turned left");

    	when(position.getParkingStatus()).thenReturn(false);
    	Boolean result = position.getParkingStatus();
    	
    	//Mockito
    	verify(position).getParkingStatus();
    }
    
    
}