package tests;

import static org.junit.Assert.*;

import main.Car;
import main.Position;
import main.UltrasoundSensor;
import newErrorHandling.StreetLengthException;

import org.junit.Before;
import org.junit.Test;

/**
 * "MoveForward" test case implementations.
 * TC1. Car moves forward.
 * TC2. Car reaches the end of the street (Position.x == 500). 
 * TC3. Car starts moving before the beginning of the street (e.g. Position.x < 0).
 * TC4. Car starts moving after the end of the street (e.g. Position.x > 500).
 * 
 * * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
 */

public class MoveForwardTests {

	Car car;
	Position position;
	UltrasoundSensor uOne;
	UltrasoundSensor uTwo;
	
	/**
	 * Initialize the car. 
	 */
    @Before
	public void Initialize() {
    	int temp[] = {1,2,3,4,2,1, 12, 232, 1};
    	uOne.setUltrasoundSensorOne(temp);
    	uTwo.setUltrasoundSensorTwo(temp);

    	car = new Car(uOne, uTwo, 0, false, false);
	}
    
    /**
     * TC1 . Car moves forward. 
     * @throws StreetLengthException 
     */
	@Test
	public void moveForwardTest() throws StreetLengthException {
		car.moveForward(uOne, uTwo);
		assertEquals(1,car.getPosition().getPositionOnStreet());
	}
	
	/**
	 * TC2. Car reaches the end of the street (Position.x == 500). 
	 * @throws StreetLengthException 
	 */
	@Test
	public void moveCarAtTheEndOfTheStreet() throws StreetLengthException{
		car.setPosition(500);
		car.moveForward(uOne, uTwo);
		assertEquals(500, car.getPosition().getPositionOnStreet());
	}

}
