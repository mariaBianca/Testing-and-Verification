package tests;

import static org.junit.Assert.*;

import main.Car;
import main.Position;
import main.UltrasoundSensor;

import org.junit.Before;
import org.junit.Test;

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
    	int temp[] = {1,2,3,4,2,1, 12, 232, 1};
    	UltrasoundSensor.setUltrasoundSensorOne(temp);
    	UltrasoundSensor.setUltrasoundSensorTwo(temp);

    	car = new Car(uOne, uTwo, 0, false, false);
	}
    
	
	/**
	 * TC1. Object is detected. 
	 */
    @Test
	public void isEmptyTest() {
    	int distance = car.isEmpty(uOne, uTwo);
    	assertEquals(1,distance);
	}

}
