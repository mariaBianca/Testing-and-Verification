package tests;

import static org.junit.Assert.*;

import java.util.Random;

import main.Car;
import main.Position;
import main.UltrasoundSensor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * "isEmpty test case implementations. TC1. Object is detected. TC2. Object is
 * not detected. TC3. Sensor returning consistent values. TC4. Sensor returning
 * noisy output.
 * 
 * TC1. Object is detected.
 * TC2. Object is not detected.
 * TC3. Sensor returning consistent values.
 * TC4. Sensor returning noisy output. 
 *
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
	public void Initialize() {
		Random rand = new Random();

		int testArray1AvCount = 0, testArray2AvCount = 0;
		int temp1[] = new int[maxTestArrayLenght];
		int temp2[] = new int[maxTestArrayLenght];
		for (int i = 0; i < maxTestArrayLenght; i++) {
			temp1[i] = rand.nextInt(250);
			if (temp1[i] <= 200 && temp1[i] > 0) {
				testArray1AvCount++;
				testAverage += temp1[i];
			}
			temp2[i] = rand.nextInt(250);
			if (temp2[i] <= 200 && temp2[i] > 0) {
				testArray2AvCount++;
				testAverage += temp2[i];
			}
		}

		testAverage /= (testArray1AvCount + testArray2AvCount);

		// UltrasoundSensor.setUltrasoundSensorOne(temp1);
		// UltrasoundSensor.setUltrasoundSensorTwo(temp2);
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
		if (distance < 0 || distance > 200) {
			exception.expect(IllegalArgumentException.class);
		}
		// System.out.println("Average test: "+ distance);
		assertEquals(testAverage, distance);
	}

	/**
	 * TC2. Object is not detected.
	*/
	
	/**
    *TC3. Sensor returning consistent values.
    */
      
	/**
	 * TC4. Sensor returning noisy output. 
    */

}
