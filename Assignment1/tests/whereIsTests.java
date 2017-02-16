package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.Car;
import main.Position;
import main.UltrasoundSensor;
import newErrorHandling.StreetLengthException;

/**
 * Description: When a 5 meter free stretch of parking place is detected,
 * the car moves to the beginning of the stretch and does a pre-programmed
 * reverse parallel parking maneuver. If no parking space is detected, the
 * car moves to the end of the street until a 5 meter of free parking space is found.
 * Pre-condition: There must be 5 meter of free parking space detected.
 * Post-condition: The car does reverse parallel parking maneuver.
 * Test-cases:
 *       TC1. The car has detected free parking space.
 *       TC2. The car is already parked.
 *       TC3. Car doesn’t find free parking space.
 */

public class WhereIsTest {
    
    Car car;
    Position position;
    UltrasoundSensor uOne;
    UltrasoundSensor uTwo;
    private int maxTestArrayLenght = 10;
    
    @Before
    public void setup(){
        Random rand = new Random();
        int temp1[] = new int[maxTestArrayLenght];
        int temp2[] = new int[maxTestArrayLenght];
        for (int i = 0; i < maxTestArrayLenght; i++) {
            temp1[i] = rand.nextInt(199);
            temp2[i] = temp1[i];
        }
        uOne.setUltrasoundSensorOne(temp1);
        uTwo.setUltrasoundSensorTwo(temp2);
        car = new Car(uOne, uTwo, 0, false, false);
        
    }
    
    /*
     * TC1 The cars’ position isn’t beyond the end of the street.
     */
    @Test(expected = StreetLengthException.class)
    public void carBeyondStreetTest() throws StreetLengthException{
        car.setPosition(800);
        car.whereIs();
    }
    
    /*
     * TC2 The cars’ position isn’t beyond the beginning of the street.
     */
    @Test(expected = StreetLengthException.class)
    public void carBeforeStreetStart() throws StreetLengthException{
        car.setPosition(-1);
        car.whereIs();
    }
    
    /*
     * TC3 The car is parked.
     */
    @Test
    public void isParked() throws StreetLengthException{
    	assertEquals(false, car.whereIs().getParkingStatus());
    	car.park(2,2);
    	assertEquals(true, car.whereIs().getParkingStatus());
    }
    
}
