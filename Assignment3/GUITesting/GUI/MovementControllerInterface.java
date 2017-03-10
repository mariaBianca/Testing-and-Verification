package main;

public interface MovementControllerInterface {

	
	/**
	 * 
	  This method moves the car one meter forward
	  
	  Pre-condition: The car is between 0 and 499 meters.

	  Post-condition:  the car has moved one meter forward.
	  
	  Test-cases: 
	 * @return 
	 * @throws SensorInputException 
	
	 
	 
	

	*/
	boolean accelerate(int location) throws SensorInputException;
	
	
	/**
	 * 
	  This method moves the car one meter backward
	  
	  Pre-condition: The car is between 1 and 500 meters.

	  Post-condition: the car has moved one meter bakwards.
	  
	  Test-cases: 
	 * @return 
	 * @throws SensorInputException 
	  
	
	 
	

	*/
	boolean reverse(int location) throws SensorInputException;
	
	




	
	

	public boolean isParked() ;

	public void setParked(boolean parked);


	


	

	
}
