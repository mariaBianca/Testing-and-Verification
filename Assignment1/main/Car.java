package main;

/**
 * Implementation of the CarInterface.
 * 
 * * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
 */

public class Car implements CarInterface{

	private Position position;
	private int[] uOne;
	private int[] uTwo;
	
	/**
	 * "Car constructor.
	 */
    public Car(UltrasoundSensor uOne, UltrasoundSensor uTwo, int x, 
    		boolean y, boolean available ){
    	
    	int[] tmpUOne = uOne.getUltrasonicSensorOne();
    	int[] tmpUTwo = uTwo.getUltrasoundSensorTwo();

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
			throws IllegalArgumentException {
		
		Position pos = getPosition();
		
		//if the car is not parked
		if (!pos.getParkingStatus()){
			//if the car is on the street within the parameters of the street, then move forward
			if (pos.getPositionOnStreet() >= 0 && pos.getPositionOnStreet() <= 499){
				pos.setPosition(pos.getPositionOnStreet()+1);
			}
			//if the car is not within the parameters of the street
			else{
				throw new IndexOutOfBoundsException("Car cannot move beyond street length.");
			}
		}
			
		
		return pos;
		
			
		}

	public double isEmpty(UltrasoundSensor ultrasoundOne, UltrasoundSensor ultrasoundTwo)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Position moveBackward(UltrasoundSensor ultrasoundOne, UltrasoundSensor ultrasoundTwo)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
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
