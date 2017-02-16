package newErrorHandling;

	/** 
	 * Creates a customized Exception for the case in which the car attempts to move outside
	 * the street bounds.
	 * 
	 *  @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
	*/
	public class StreetLengthException extends Exception{


		private static final long serialVersionUID = 6989320739245639400L;
		
		public StreetLengthException(String message){
			super(message);
		};

		public StreetLengthException() throws StreetLengthException{
			//throw new IndexOutOfBoundsException("Car cannot move outside the street length.");
			throw new StreetLengthException("Car cannot move outside the street length.");
		}
	}
