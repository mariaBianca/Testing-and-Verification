package main;


/**
 * 
 * implementation of the Car (interface)
 *
 */

public class CarImpl implements Car {

	private UltrasonicSensor ultraSonicSensor1;
	private UltrasonicSensor ultraSonicSensor2;
	private MovementController movementController;
	private int[] parkingPlaces;
	private int counter=0;
	private int position;
	
	public CarImpl(int location, boolean parked) {
		
		setSensor1(new UltrasonicSensor());
		setSensor2(new UltrasonicSensor());
		this.position = location;

		try {
			setMovementController(new MovementController(parked));
		} catch (SensorInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setCounter(new int[501]);
		
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
					int[] counterArray = getCounter();				
					counterArray[counter] = 1;
					counter++;
					setCounter(counterArray);
				}else{
					int[] tmp = getCounter();
					tmp[counter] = 0;
					counter++;
					setCounter(tmp);
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
		if(!getMovementController().isParked()){	//if the car is not parked at a certain position
			if(getLocation() <=500 && getLocation()> 0){
				if(movementController.reverse(position)){
					position--;
				}
			} 
		}
	}
	
	public void parkingManuever(){
		//park
	}
	
	public void park() throws SensorInputException {
		
		parkingManuever();
		movementController.setParked(true);	
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
	
	public int[] getCounter() {
		
		return this.parkingPlaces;
	}

	public void setCounter(int[] i) {
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
