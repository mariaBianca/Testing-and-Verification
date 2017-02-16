package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import main.Car;
import main.Position;
import main.UltrasoundSensor;
/**
 * Description: The car moves forward (and to the left) to the front of the parking space if the car is parked.
 * Pre-condition: The car must be parked.
 * Post_condition: The car has moved forward and to the left.
 * Test-cases:
 *           TC1. The car is parked.
 *           TC2. Car is not parked.
 *           TC3. Car is not on the street.
 */
public class unParkTests {
    
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
     * TC1 The car is parked.
     */
    @Test
    public void carisParked() {
        Car car = new Car(uOne, uTwo, 0, true, false); //*input* we should initialize before a car :)
        car.unpark();
        Boolean result = car.whereIs().getParkingStatus();
        assertNotNull(result);
        assertEquals(false ,result);
    }
    
    /*
     * TC2 The car is not parked.
     */
    @Test
    public void carisNotParked() {
        car.unpark();
        Boolean result = car.whereIs().getParkingStatus();
        assertNotNull(result);
        assertEquals(false ,result);
    }
    
    /*
     * TC3 The car turns left.
     */
    @Test
    public void carTurnsLeft() {
        String result = car.steerLeft();
        assertNotNull(result);
        assertEquals("The car turned left" ,result);
    }
    
}
