package main;

import java.util.Random;

/**
 * Definition of the UltrasoundSensor object.
 *
 * * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
 */

public class UltrasoundSensor {
    
    private static int ultrasoundSensorOne[];
    private static int ultrasoundSensorTwo[];
    
    /**
     *Set the first's ultrasound sensor's value to a given value
     * @param value[]
     */
    public static void setUltrasoundSensorOne(int[] value) {
        ultrasoundSensorOne = value;
    }
    
    /**
     *Get the first ultrasound sensor's value
     *@return ultrasoundSensorOne
     */
    public static int[] getUltrasonicSensorOne() {
        return ultrasoundSensorOne;
    }
    
    /**
     *Set the second ultrasound sensor's value to a given value
     * @param value[]
     */
    public static void setUltrasoundSensorTwo(int[] value) {
        ultrasoundSensorTwo = value;
    }
    
    /**
     *Get the first's ultrasound sensor's value
     *@return ultrasoundSenosr
     */
    public static int[] getUltrasoundSensorTwo() {
        return ultrasoundSensorTwo;
    }
    
    /**
     * This method is depending if the user chooses 1 2 or 3 is returned with a random number, 
     * array with 0 values or with a fixed number 150.
     * @param random
     * @return array
     */
    public static int[] getUltrasonicSensorOne(int random) {
        Random rand = new Random();
        int array[]={rand.nextInt(2), rand.nextInt(2)}, array2[] = {0, 0}, array3[] = {150, 150};
        if(random == 1){
            return array;
        }else if (random == 2){
            return array2;
        }else{
            return array3;
        }
        
    }
    
    /**
     * This method is depending if the user chooses 1 2 or 3 is returned with a random number,
     *  array with 0 values or with a fixed number 150.
     * @param random
     * @return array
     */
    public static int[] getUltrasoundSensorTwo(int random) {
        Random rand = new Random();
        int array[]={rand.nextInt(2), rand.nextInt(2)}, array2[] = {0, 0}, array3[] = {150, 150};
        if(random == 1){
            return array;
        }else if (random == 2){
            return array2;
        }else{
            return array3;
        }
    }
}
