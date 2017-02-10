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
 * "MoveBackward" test case implementations.
 * TC1. The car moves backwards. 
 * TC2. Car reaches the end of the street (Position.x == 0). 
 * TC3. Car starts moving before the beginning of the street (e.g. Position.x < 0).
 * TC4. Car position is initialized beyond the end of the street (e.g. Position.x > 500).
 * 
 * * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
 */

public class MoveBackwardTests {

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
     * Initialize rules when it comes to the exception handling.
     */
	@Rule
	public final ExpectedException exception = ExpectedException.none();


	/**
	 *  TC1. The car moves backwards. (Positon.x>=1) 
	 *  @throws StreetLengthException 
	 */
	@Test
	public void moveBackwardTest() throws StreetLengthException{
		car.setPosition(1);
		car.moveBackward(uOne, uOne);
		assertEquals(0, car.getPosition().getPositionOnStreet());
	}

	/**
	 * TC2. Car reaches the end of the street. (Position.x == 0)
	 *  @throws StreetLengthException 
	 */
	@Test
	public void moveBackwardAtTheBeginningOfTheStreet() throws StreetLengthException{
		exception.expect(StreetLengthException.class);
		car.moveBackward(uOne, uTwo);
		//assertEquals(-1, car.getPosition().getPositionOnStreet());
	}
}
