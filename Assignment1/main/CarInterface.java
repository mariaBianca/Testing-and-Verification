package main;

/**
 * Interface of the Car.
 * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca Cindroi
 */

public interface CarInterface {
	
   /**
     * Move the car forward with one meter.
     * @return Position(data structure)
     */
    public Position moveForward();

    /**
     * Query the two ultrasound sensors and filter
     * the noise in their result .
     * @return distanceToObject (to the nearest object in the right hand sight)
     */
    public double isEmpty();

    /**
     * Move the car backward with one meter.
     * @return Position (of the car)
     */    
    public Position moveBackward();
	
    /**
     *The car moves to the beginning of the current 5 meter free stretch of parking place, or until
     *the end of the street until an empty parking spot is detected. 
     */
    public void park();
   
    /**
     *The car moves forward (and to left) to front of the parking place.
     */
    public void unpark();
    
    /**
     * @return Position (of the car)
     */
    public Position whereIs();
}
