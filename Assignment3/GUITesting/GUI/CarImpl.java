package main;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * implementation of the Car (interface)
 *
 */

public class CarImpl implements Car {

	private UltrasonicSensor ultraSonicSensor1;
	private UltrasonicSensor ultraSonicSensor2;
	private MovementController movementController;
	private ArrayList<Integer> parkingPlaces = new ArrayList<>();
	private int counter=0;
	private int position;
	
	public CarImpl(int location, boolean parked, ArrayList<Integer> parkingPlaces) {
		
		setSensor1(new UltrasonicSensor());
		setSensor2(new UltrasonicSensor());
		this.position = location;
		
		this.parkingPlaces = parkingPlaces;
//		for(int i = 0; i<this.parkingPlaces.length; i++){
//			System.out.println(this.parkingPlaces[i]);
//		}

		try {
			setMovementController(new MovementController(parked));
		} catch (SensorInputException e) {
			e.printStackTrace();
		}

		
	}
		public void setSensor2(UltrasonicSensor ultrasonicSensor) {
			this.ultraSonicSensor2 = ultrasonicSensor;		
	}
		public void setSensor1(UltrasonicSensor ultrasonicSensor) {
			this.ultraSonicSensor1 = ultrasonicSensor;
	}


	/**
	 * moveForward method
	 * moves forward if the car is not parked
	 */
	public int moveForward() throws SensorInputException {
		
		if(!movementController.isParked()){
			if(position < 500 && position >= 0){
				if(movementController.accelerate(position)){
					position++;
				};
				
				int result = isEmpty();
				if(result>100){
					ArrayList<Integer> counterArray = getParkingPlaces();				
					counterArray.set(counter, 1);
					counter++;
					setCounter(counterArray);
				}else{
					ArrayList<Integer> counterArray = getParkingPlaces();
					counterArray.set(counter, 0);
					counter++;
					setCounter(counterArray);
				}
			}
		}
		return getLocation();
	}
	
	/**
	 * isEmpty implementation
	 */
	public int isEmpty() throws SensorInputException {
		int sensor1av = 0;
		int sensor2av = 0;
		int result = 0;
		boolean sensor1active=true;
		boolean sensor2active=true;
		int lastValue1 = getSensor1().getUltrasonicArray()[0];
		int lastValue2 = getSensor2().getUltrasonicArray()[0];
		
		//array check
		for(int i = 0;i<getSensor1().getUltrasonicArray().length;i++){
			if(getSensor1().getUltrasonicArray()[i] < 0 || getSensor1().getUltrasonicArray()[i]>200){
				throw new SensorInputException("Bad input from sensor 1!");	
			}
		}
		for(int i = 0;i<getSensor2().getUltrasonicArray().length;i++){
			if(getSensor2().getUltrasonicArray()[i] <0 || getSensor2().getUltrasonicArray()[i]>200){
				throw new SensorInputException("Bad input from sensor 2!");
			}
		}
		
		for(int i = 0;i<5;i++){
			if(getSensor1().getUltrasonicArray()[i]+10 < lastValue1 || getSensor1().getUltrasonicArray()[i]-10 > lastValue1){
				sensor1active = false;	
			}
			if(getSensor2().getUltrasonicArray()[i]+10 < lastValue2 || getSensor2().getUltrasonicArray()[i]-10 > lastValue2){
				sensor2active = false;	
			}

			sensor1av += getSensor1().getUltrasonicArray()[i];
			sensor2av += getSensor2().getUltrasonicArray()[i];
			
			lastValue1 = getSensor1().getUltrasonicArray()[i];
			lastValue2 = getSensor2().getUltrasonicArray()[i];
		}
		sensor1av = sensor1av/5;
		sensor2av = sensor2av/5;

		if(sensor1active && sensor2active){
			result += sensor1av;
			result += sensor2av;
			result /=2;
		}else if(!sensor1active && sensor2active){
			result += sensor2av;
		}else if(sensor1active && !sensor2active){
			result += sensor1av;
		}else if(!sensor1active && !sensor2active){
			throw new SensorInputException("Data not reliable!");
		}
		return result;
	}
	public UltrasonicSensor getSensor2() {
		return this.ultraSonicSensor2;
		
	}


	public UltrasonicSensor  getSensor1() {
		return this.ultraSonicSensor1;
	}


	/**
	 * moveBackward implementation
	 * moves the car backwards
	 */
	public void moveBackward() throws SensorInputException {
		if(!getMovementController().isParked()){
			if(getLocation() <=500 && getLocation()> 0){
				if(movementController.reverse(position)){
					position--;
				}
			} 
		}
	}
	
	public void parkingManuever(){
		if(this.getLocation() < 500){
			movementController.setParked(true);
		}
	}
	
	public void park() throws SensorInputException {
		if(parkingPlaces.get(this.getLocation()) == 0){
			parkingManuever();
		}
	}
	
	/**
	 * implementation of unPark
	 * unparks the car
	 */
	public void unPark() {
		
		movementController.setParked(false);
	}
	
	/**
	 * whereIs implementation
	 * returns the location of the car
	 */
	public int whereIs() {
		return getLocation();
	}
	
	public MovementController getMovementController() {
		return movementController;
	}
	public void setMovementController(MovementController movementController) {
		this.movementController = movementController;
	}
	
	public ArrayList<Integer> getParkingPlaces() {
		
		return this.parkingPlaces;
	}

	public void setCounter(ArrayList<Integer> i) {
		this.parkingPlaces=i;
	}
	
	public int getLocation() {
		return this.position;
		
	}

	public void setLocation(int location) throws SensorInputException {

		if(location < 0 || location > 500){
			throw new SensorInputException("Wrong input!");
		}else{
			
			this.position=location;
		}
	}
	
}
