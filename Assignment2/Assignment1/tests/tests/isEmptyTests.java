package tests;

import static org.junit.Assert.*;

import java.util.Random;

import main.Car;
import main.Position;
import main.UltrasoundSensor;
import newErrorHandling.StreetLengthException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * "isEmpty" test case implementations. TC1. Object is detected. TC2. Object is
 * not detected. TC3. Sensor returning consistent values. TC4. Sensor returning
 * noisy output.
 * 
 * 
 * * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca
 * Cindroi
 */
public class isEmptyTests {

	Car car;
	Position position;
	UltrasoundSensor uOne;
	UltrasoundSensor uTwo;
	private int testAverage = 0;
	private int maxTestArrayLenght = 10;

	/**
	 * Initialize the car.
	 */
	@Before
	public void setUp() {
		System.out.println("doing before");
		Random rand = new Random();
		int testArray1AvCount = 0, testArray2AvCount = 0;
		int temp1[] = new int[maxTestArrayLenght];
		int temp2[] = new int[maxTestArrayLenght];
		for (int i = 0; i < maxTestArrayLenght; i++) {
			temp1[i] = rand.nextInt(199);
			if (temp1[i] <= 200 && temp1[i] > 0) {
				testArray1AvCount++;
				testAverage += temp1[i];
			}
			temp2[i] = temp1[i];
			if (temp2[i] <= 200 && temp2[i] > 0) {
				testArray2AvCount++;
				testAverage += temp2[i];
			}
		}

		testAverage /= (testArray1AvCount + testArray2AvCount);

		// UltrasoundSensor.setUltrasoundSensorOne(temp1);
		// UltrasoundSensor.setUltrasoundSensorTwo(temp2);
		uOne.setUltrasoundSensorOne(temp1);
		uTwo.setUltrasoundSensorTwo(temp2);
		car = new Car(uOne, uTwo, 0, false, false);
	}

	/**
	 * Assign rules when it comes to the exception handling.
	 */
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	/**
	 * TC1. Object is Detected
	 */
	
	@Test
	public void distanceShouldGiveAValidOutput(){
		int distance = car.isEmpty(uOne, uTwo);
		System.out.println("Distance is " +distance);
		assertNotNull(distance);
		System.out.println("testaverage is " +testAverage);
		assertEquals(testAverage, distance);
	}
	
	/**
	 * TC2. Object is not detected
	 */
	@Test
	public void objectIsNotDetected(){
		int temp[] = {0, 0, 0, 0, 0, 0};
    	UltrasoundSensor.setUltrasoundSensorOne(temp);
    	UltrasoundSensor.setUltrasoundSensorTwo(temp);

    	Car car = new Car(uOne, uTwo, 0, false, false); 
		
		int distance = car.isEmpty(uOne, uTwo);
		assertNotNull(distance);
		assertEquals(0, distance);
	}
	
	/**
	 * TC3. Sensor returning noisy output
	 */
	@Test(expected = IllegalArgumentException.class)
	public void isEmptyShouldThrowAnExceptionDistanceoutOfScope(){
		int temp[] = {210, 200, 200, 500, 400, 700, 900, 200, 300};
    	UltrasoundSensor.setUltrasoundSensorOne(temp);
    	UltrasoundSensor.setUltrasoundSensorTwo(temp);

    	Car car = new Car(uOne, uTwo, 0, false, false); 
    	
		car.isEmpty(uOne, uTwo); //here the sensors need out of bound input
	}

	
	/**
	 * TC4. Sensors have inconsistent values.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void isEmptyHaveInconsistentValues(){
		int temp[] = {2,2,2,2,2};
		int temp2[] = {10,10,10,10,10};
    	UltrasoundSensor.setUltrasoundSensorOne(temp);
    	UltrasoundSensor.setUltrasoundSensorTwo(temp2);

    	Car car = new Car(uOne, uTwo, 0, false, false); 
    	
		car.isEmpty(uOne, uTwo); 
	}
	
	
	
}
