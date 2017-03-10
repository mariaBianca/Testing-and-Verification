package main;

public interface Car {

	
	/**
	 * 
	 * @throws SensorInputException 
	*/
	
public int isEmpty() throws SensorInputException;
	
	/** 
	 * 
	 * @throws SensorInputException 
	*/
	public int moveForward() throws SensorInputException;
	
	public void moveBackward() throws SensorInputException;
	
	/**
	 * 
	 * @throws SensorInputException 
	*/
	public void park() throws SensorInputException;

	public void unPark();
	
	public int whereIs();
	
	public int getLocation();
	
}
