package newErrorHandling;

	/** 
	 * Creates a customized Exception for the case in which the car attempts to move outside
	 * the street bounds.
	 * 
	 *  @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
	*/
	public class StreetLengthException extends Exception{

		public StreetLengthException(){
			throw new IndexOutOfBoundsException("Car cannot move outside the street length.");
		}
	}
