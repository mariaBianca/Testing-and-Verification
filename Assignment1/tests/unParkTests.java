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
 * 	"unpark" test case implementation.
 *  TC1. The car is parked.
 *  TC2. Car is not parked.
 *  TC3. Car steers left.
 *           
 *  @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
 */
public class unParkTests {
    
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
     * TC1 The car is parked.
     * @throws StreetLengthException
     */
    @Test
    public void carisParked() throws StreetLengthException {
        CarImpl car = new CarImpl(uOne, uTwo, 0, true, false); //*input* we should initialize before a car :)
        car.unpark();
        Boolean result = car.whereIs().getParkingStatus();
        assertNotNull(result);
        assertEquals(false ,result);
    }
    
    /**
     * TC2 The car is not parked.
     * @throws StreetLengthException
     */
    @Test
    public void carisNotParked() throws StreetLengthException {
        car.unpark();
        Boolean result = car.whereIs().getParkingStatus();
        assertNotNull(result);
        assertEquals(false ,result);
    }
    
    /**
     * TC3 The car steers left.
     * @throws StreetLengthException
     */
    @Test
    public void carTurnsLeft() {
        String result = car.steerLeft();
        assertNotNull(result);
        assertEquals("The car turned left" ,result);
    }
    
}
