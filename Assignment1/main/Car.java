package main;

import newErrorHandling.StreetLengthException;

/**
 * Implementation of the CarInterface.
 * 
 * * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca
 * Cindroi
 */

public class Car implements CarInterface {

	private Position position;
	@SuppressWarnings("unused")
	private int[] uOne;
	@SuppressWarnings("unused")
	private int[] uTwo;
//	@SuppressWarnings("unused")
//	private boolean isParked;
//	@SuppressWarnings("unused")
//	private boolean isAvailable;
	

	/**
	 * "Car constructor.
	 * y - boolean isParked
	 * available - boolean isAvailable
	 */
	public Car(UltrasoundSensor uOne, UltrasoundSensor uTwo, int x, boolean y, boolean available) {

		int[] tmpUOne = UltrasoundSensor.getUltrasonicSensorOne();
		int[] tmpUTwo = UltrasoundSensor.getUltrasoundSensorTwo();

		UltrasoundSensor.setUltrasoundSensorOne(tmpUOne);
		this.uOne = UltrasoundSensor.getUltrasonicSensorOne();

		UltrasoundSensor.setUltrasoundSensorTwo(tmpUTwo);
		this.uTwo = UltrasoundSensor.getUltrasoundSensorTwo();

		position = new Position(x, y, available);
	}

	/**
	 * "Car constructor.
	 */
	public void setPosition(int x) {
		position.setPosition(x);
	}

	/**
	 * "Method implementing the "moveForward".
	 */
	public Position moveForward(UltrasoundSensor ultrasoundOne, UltrasoundSensor ultrasoundTwo)
			throws StreetLengthException {

		Position pos = getPosition();

		// if the car is not parked
		if (!pos.getParkingStatus()) {
			// if the car is on the street within the parameters of the street,
			// then move forward
			if (pos.getPositionOnStreet() >= 0 && pos.getPositionOnStreet() <= 499) {
				pos.setPosition(pos.getPositionOnStreet() + 1);
			} else {
				throw new StreetLengthException();
				// pos.setPosition(-1);
			}

			if (isEmpty(ultrasoundTwo, ultrasoundTwo) > 100) {
				position.setParked(true);
			}
		} else {
			position.setParked(false);
		}

		return pos;
	}

	public int isEmpty(UltrasoundSensor ultrasoundOne, UltrasoundSensor ultrasoundTwo) throws IllegalArgumentException {
		int distance = 0, usArrayLenght, average = 0, count = 0;

		int[] us1 = ultrasoundOne.getUltrasonicSensorOne(), us2 = ultrasoundTwo.getUltrasoundSensorTwo();

		if (us1.length >= us2.length) {
			usArrayLenght = us1.length;
		} else {
			usArrayLenght = us2.length;
		}

		for (int i = 0; i < usArrayLenght; i++) {
			if((us1[i] > us2[i]+5) || (us1[i] < us2[i]-5) || (us2[i] < us1[i]-5) || (us2[i] > us1[i]+5)){
				throw new IllegalArgumentException("Sensors different output!");
			}
			System.out.println("us1: " + us1[i] + " us2: " + us2[i]);
			if (us1[i] <= 200 && us1[i] => 0) {
				average += us1[i];
				count++;
			}
			if (us2[i] <= 200 && us2[i] => 0) {
				average += us2[i];
				count++;
			}
		}
		if (average > 0 && count > 0) {
			distance = average / count;
		}
		if (distance < 0 || distance > 200) {
			throw new IllegalArgumentException();
			// System.out.println("Average: "+distance);
		}
		return distance;
	}

	public Position moveBackward(UltrasoundSensor ultrasoundOne, UltrasoundSensor ultrasoundTwo)
			throws StreetLengthException {

		Position pos = getPosition();

		// if the car is not parked
		if (!pos.getParkingStatus()) {
			// if the car is on the street within the parameters of the street,
			// then move forward
			if (pos.getPositionOnStreet() >= 1 && pos.getPositionOnStreet() <= 500) {
				pos.setPosition(pos.getPositionOnStreet() - 1);
			} else
				throw new StreetLengthException();
		}

		return pos;
	}
	
	//Does the parking maneuver
	public void parkingManeuver(){
		System.out.println("Parking the car...");
		position.setParked(true);
	}
	
	//Assuming that sensors return int. values which represent meters.
	public void park() {
		int i = 0, distance = 0;
		UltrasoundSensor ultrasoundOne = null, ultrasoundTwo = null;
		
		int[] us1, us2;
		
		//2 = 0, 3 = 1;
		//initialize the arrays
		ultrasoundOne.setUltrasoundSensorOne(ultrasoundOne.getUltrasonicSensorOne(3));
		ultrasoundTwo.setUltrasoundSensorTwo(ultrasoundTwo.getUltrasoundSensorTwo(3));
		
		while(!position.getParkingStatus()){
			//check distance
			ultrasoundOne.setUltrasoundSensorOne(ultrasoundOne.getUltrasonicSensorOne(3));
			ultrasoundTwo.setUltrasoundSensorTwo(ultrasoundTwo.getUltrasoundSensorTwo(3));
			distance = isEmpty(ultrasoundOne, ultrasoundTwo);
			
			if(distance==0){
				i++;
				try {
					moveForward(ultrasoundOne, ultrasoundTwo);
				} catch (StreetLengthException e) {
					e.printStackTrace();
				}
			}
			
			if(i==5){
				parkingManeuver();
			}
			
			if(distance>0){
				i=0;
				try {
					moveForward(ultrasoundOne, ultrasoundTwo);
				} catch (StreetLengthException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void unpark() {
		// TODO Auto-generated method stub

	}

	/**
	 * Method that gets the position of the car.
	 */

	public Position whereIs() {

		return getPosition();
	}

	/**
	 * Method that gets the position of the car.
	 */
	public Position getPosition() {
		return position;
	}

}
