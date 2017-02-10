package tests;

import static org.junit.Assert.*;

import main.Car;
import main.Position;
import main.UltrasoundSensor;
import newErrorHandling.StreetLengthException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
* "isEmpty" test case implementations.
* TC1. Object is detected.
* TC2. Object is not detected.
* TC3. Sensor returning consistent values.
* TC4. Sensor returning noisy output. 

* 
* * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
*/
public class isEmptyTests {

	Car car;
	Position position;
	UltrasoundSensor uOne;
	UltrasoundSensor uTwo;
	
	/**
	 * Initialize the car. 
	 */
    @Before
	public void Initialize()  {
    	int temp1[] = {1,2,3,4,2,1, 12, 232, 1};
    	int temp2[] = {2,3,4,5,1,3, 13, 10, 2};
    	UltrasoundSensor.setUltrasoundSensorOne(temp1);
    	UltrasoundSensor.setUltrasoundSensorTwo(temp2);

    	car = new Car(uOne, uTwo, 0, false, false);
	}
    
    /**
     * Assign rules when it comes to the exception handling.
     */
	@Rule
	public final ExpectedException exception = ExpectedException.none();
    
	
	/**
	 * TC1. Object is detected. 
	 */
    @Test
	public void isEmptyTest() throws IllegalArgumentException {
    	int distance = car.isEmpty(uOne, uTwo);
		//exception.expect(IllegalArgumentException.class);
    	assertEquals(4,distance);
	}

}
