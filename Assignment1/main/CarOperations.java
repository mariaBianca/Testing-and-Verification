package main;
/**
 * The implementation of CarInterface. 
 * * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
 */

public class CarOperations implements CarInterface{

	private Position position;

	public Position moveForward(Ultrasound ultrasoundOne,Ultrasound ultrasoundTwo) throws IllegalArgumentException {
		
		position = whereIs();
		
		return position;
	}

	public double isEmpty(Ultrasound ultrasoundOne, Ultrasound ultrasoundTwo)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	public Position moveBackward(Ultrasound ultrasoundOne,
			Ultrasound ultrasoundTwo) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public void park() {
		// TODO Auto-generated method stub
		
	}

	public void unpark() {
		// TODO Auto-generated method stub
		
	}

	public Position whereIs() {

		position.getPosition();
		return position;
	}

}
