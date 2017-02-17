package main;

/**
 * Object that defines the position of the car on the X/Y axes, the situation of the parking
 * spaces detected.
 * 
 * * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
 */
public class Position {


	/**
	 * The car moves on the X-axis.
	 * The parked-boolean can be false or true, which represent parked or not parked. 
	 * Available represents if the car finds a place to park or not. 
	 */
	private int x;
	private boolean parked;
	@SuppressWarnings("unused")
	private boolean available;
	
	/**
	 * Method that constructs the position.
	 * */
	public Position(int x, boolean y, boolean available){
		setPosition(x);
		setParked(y);
	}
	
	/**
	 * Method that sets the position.
	 * */
	public void setPosition(int x){
		this.x = x;
	}
	
	/**
	 * Method that gets the position on the X-axis.
	 */
	public int getPositionOnStreet(){
		return this.x;
	}
	
	/**
	 * Method that returns the parking status.
	 */
	public boolean getParkingStatus(){
		return parked;
		
	}
	
	/**
	 * Method that sets the parking status.
	 */
	public void setParked(boolean parked){
		this.parked = parked;
	}
}
