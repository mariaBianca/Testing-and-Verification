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
 *	"park" test case implementation.
 *  TC1. TC1. The car executes the pre-programmed reverse parallel parking.
 *  TC2. The car is already parked.
 *  TC3. The car doesn't find free parking spot.
 *       
 *  @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
 */

public class parkTests {
    
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
     * TC1. The car executes the pre-programmed reverse parallel parking.
     * @throws StreetLengthException
     */
    @Test
    public void carExecutesPreProgrammedPark() throws StreetLengthException {
        car.park(2,2);
        Boolean result = car.whereIs().getParkingStatus();
        assertNotNull(result);
        assertEquals((car.whereIs().getParkingStatus() == true) ,result);
    }
    
    /**
     * TC2. The car is already parked.
     * @throws StreetLengthException
     */
    @Test
    public void carisParked() throws StreetLengthException {
        CarImpl car = new CarImpl(null, null, 0, true, false); //*input* we should initialize before a car :)
        car.park(3,3);
        Boolean result = car.whereIs().getParkingStatus();
        assertNotNull(result);
        assertEquals((car.whereIs().getParkingStatus() == true) ,result);
    }
    
    /**
     * TC3. The car doesn't find free parking spot.
     * @throws StreetLengthException
     */
    @Test(expected = StreetLengthException.class)
    public void isEmptyShouldThrowAnExceptionDistanceoutOfScope() throws StreetLengthException {
        car.park(3,3);
    }
    
}
