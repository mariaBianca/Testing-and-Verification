package main;

/**
 * Construction of the ultrasound sensors
 * 
 * * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
 */

public class UltrasoundSensor {
	
	private static int ultrasoundSensorOne[];
	private static int ultrasoundSensorTwo[];
	
	/**
	 *Set the first's ultrasound sensor's value to a given value 
	 * @return 
	 * @return 
	 */
	public static void setUltrasoundSensorOne(int[] value) {
		ultrasoundSensorOne = value;
	}

	/**
	 *Get the first's ultrasound sensor's value 
	 *@return ultrasoundSenosr 
	 */
	public static int[] getUltrasonicSensorOne() {
		return ultrasoundSensorOne;
	}
	
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
}
