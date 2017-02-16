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
	
	@Before
	public void setup(){
		/**
		 * Initialize the mocks
		 */
        MockitoAnnotations.initMocks(this);
    }
	 
    @Test
    public void carShouldExecutePreProgrammedPark() throws StreetLengthException {
    	Car car = new Car(null, null, 0, false, false);
    	car.park(2, 2);
    	when(position.getParkingStatus()).thenReturn(true);
    	Boolean result = position.getParkingStatus();
    	assertTrue(result);
    	/**
    	 * To verify that the mocking worked we use a static method from the class Mockito
    	 * 
    	 */
    	verify(position).getParkingStatus();
    }

}
