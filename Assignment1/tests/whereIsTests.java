package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.CarImpl;
import main.Position;
import main.UltrasoundSensor;
import newErrorHandling.StreetLengthException;

/**
 * "whereIs" test case implementation.
 * TC1. The car's position is before the end of the street.
 * TC2. The car’s position is after the beginning of the street.
 * TC3. The car is parked.
 *       
 *  @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi

 */

public class whereIsTests {
    
    CarImpl car;
    Position position;
    UltrasoundSensor uOne;
    UltrasoundSensor uTwo;
    private int maxTestArrayLenght = 10;

    /**
	 * Initialize the car. 
	 */
    @Before
    public void setup(){
        Random rand = new Random();
        int temp1[] = new int[maxTestArrayLenght];
        int temp2[] = new int[maxTestArrayLenght];
        for (int i = 0; i < maxTestArrayLenght; i++) {
            temp1[i] = rand.nextInt(199);
            temp2[i] = temp1[i];
        }
        UltrasoundSensor.setUltrasoundSensorOne(temp1);
        UltrasoundSensor.setUltrasoundSensorTwo(temp2);
        car = new CarImpl(uOne, uTwo, 0, false, false);
        
    }
    
    /**
     * TC1. The car's position is before the end of the street.
     */
    @Test(expected = StreetLengthException.class)
    public void carBeyondStreetTest() throws StreetLengthException{
        car.setPosition(800);
        car.whereIs();
    }
    
    /**
     * TC2. The car’s position is after the beginning of the street.
     */
    @Test(expected = StreetLengthException.class)
    public void carBeforeStreetStart() throws StreetLengthException{
        car.setPosition(-1);
        car.whereIs();
    }
    
    /**
     * TC3. The car is parked.
     */
    @Test
    public void isParked() throws StreetLengthException{
    	assertEquals(false, car.whereIs().getParkingStatus());
    	car.park(2,2);
    	assertEquals(true, car.whereIs().getParkingStatus());
    }
    
}
