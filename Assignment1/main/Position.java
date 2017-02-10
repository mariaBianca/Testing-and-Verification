package main;

/**
 * Object that defines the position of the car on the X/Y axes, the situation of the parking
 * spaces detected.
 * 
 * * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
 */
public class Position {
	
	private int x;
	private boolean available;
	private boolean y;

	/**
	 * The car moves on the X-axis.
	 * The Y-axis can be false or true, which represent parked or not parked. 
	 * Available represents if the car finds a place to park or not. 
	 */
	
	//Construct the position
	public Position(int x, boolean y, boolean available){
		setPosition(x);
		setParked(y);
	}
	
	
	//Set the position
	private void setPosition(int x){
		if (x >= 0 && x <= 500){
			this.x = x;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	//Return the position on the X-axis
	public int getPosition(){
		return x;
	}
	
	//Return the parking status of the park
	public boolean getParkingStatus(){
		return y;
		
	}
	
	//Set Parked
	public boolean setParked(boolean parked){
		
		return this.y;
	}
	
	//Return the status of the available parking spots
	public boolean getParkingSpots(){
		return this.available;
	}

}
