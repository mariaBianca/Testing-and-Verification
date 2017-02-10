package main;

import newErrorHandling.StreetLengthException;

/**
 * Implementation of the CarInterface.
 * 
 * * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
 */

public class Car implements CarInterface{

	private Position position;
	@SuppressWarnings("unused")
	private int[] uOne;
	@SuppressWarnings("unused")
	private int[] uTwo;
	
	/**
	 * "Car constructor.
	 */
    public Car(UltrasoundSensor uOne, UltrasoundSensor uTwo, int x, 
    		boolean y, boolean available ){
    	
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
    public void setPosition(int x){
    	position.setPosition(x);
    }
    
	/**
	 * "Method implementing the "moveForward".
	 */
	public Position moveForward(UltrasoundSensor ultrasoundOne,UltrasoundSensor ultrasoundTwo)
			throws StreetLengthException {
		
		Position pos = getPosition();
		
		//if the car is not parked
		if (!pos.getParkingStatus()){
			//if the car is on the street within the parameters of the street, then move forward
			if (pos.getPositionOnStreet() >= 0 && pos.getPositionOnStreet() <= 499){
				pos.setPosition(pos.getPositionOnStreet()+1);
			}
			else pos.setPosition(-1);

			if(isEmpty(ultrasoundTwo, ultrasoundTwo)>100){
				position.setParked(true);
			}
			}else{
				position.setParked(false);
		}	
		
		return pos;
		}

	public int isEmpty(UltrasoundSensor ultrasoundOne, UltrasoundSensor ultrasoundTwo)
			throws IllegalArgumentException {

		for (int i = 0; i<5; i++ ){
			
		}
		
		return distance;
	}

	public Position moveBackward(UltrasoundSensor ultrasoundOne, UltrasoundSensor ultrasoundTwo)
			throws StreetLengthException {
		
		Position pos = getPosition();
		
		//if the car is not parked
		if (!pos.getParkingStatus()){
			//if the car is on the street within the parameters of the street, then move forward
			if (pos.getPositionOnStreet() >= 1 && pos.getPositionOnStreet() <= 500){
				pos.setPosition(pos.getPositionOnStreet()-1);
			}
			else throw new StreetLengthException();
		}	
		
		return pos;
	}

	public void park() {
		// TODO Auto-generated method stub
		
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
	public Position getPosition(){
		return position;
	}

}
