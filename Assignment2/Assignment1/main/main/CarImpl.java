package main;

import newErrorHandling.StreetLengthException;

/**
 * Implementation of the CarInterface.
 *
 * * @author Group1: Aseel Naji, Filip Isakovski, Antonino Sauleo, Maria-Bianca
 * Cindroi
 */

public class CarImpl implements Car {
    
    private Position position;
    @SuppressWarnings("unused")
    private UltrasoundSensor uOne;
    @SuppressWarnings("unused")
    private UltrasoundSensor uTwo;
    
    
    /**
     * "Car constructor.
     * y - boolean isParked
     * available - boolean isAvailable
     */
    public CarImpl(UltrasoundSensor uOne, UltrasoundSensor uTwo, int x, boolean parked, boolean available) {
        
        int[] tmpUOne = UltrasoundSensor.getUltrasonicSensorOne();
        int[] tmpUTwo = UltrasoundSensor.getUltrasoundSensorTwo();
        
        UltrasoundSensor.setUltrasoundSensorOne(tmpUOne);
        UltrasoundSensor.setUltrasoundSensorTwo(tmpUTwo);
        
        position = new Position(x, parked, available);
    }
    
    /**
     * Method to set the position
     * @param x
     */
    public void setPosition(int x) {
        position.setPosition(x);
    }
    
    /**
     * Method that moves the car forward from its current location.
     * @throws StreetLengthException
     */
    public void moveForward() throws StreetLengthException {
        try{
            position = moveForward(position.getPositionOnStreet());
        }catch(StreetLengthException e){
            throw e;
        }
        
    }
    /**
     * "Method implementing the "moveForward" but it moves the car forward from any inputed location
     */
    public Position moveForward(int startPosition)
    throws StreetLengthException {
        
        Position pos = position;
        pos.setPosition(startPosition);
        
        // if the car is not parked
        if (!pos.getParkingStatus()) {
            /**
             * if the car is on the street within the parameters of the street,
             * then move forward
             */
            if(pos.getPositionOnStreet() < 0 || pos.getPositionOnStreet() > 499){
                throw new StreetLengthException();
            }
            
            if (pos.getPositionOnStreet() >= 0 && pos.getPositionOnStreet() <= 499) {
                pos.setPosition(pos.getPositionOnStreet() + 1);
            } else {
                throw new StreetLengthException();
            }
        }
        
        return pos;
    }
    
    public int isEmpty(UltrasoundSensor ultrasoundOne, UltrasoundSensor ultrasoundTwo) throws IllegalArgumentException {
        int distance = 0, usArrayLenght, average = 0, count = 0;
        
        int[] us1 = UltrasoundSensor.getUltrasonicSensorOne(), us2 = UltrasoundSensor.getUltrasoundSensorTwo();
        /**
         * This compares the two arrays for the sensors and the array that has the biggest array length will be
         * the size for running the for loop
         */
        if (us1.length >= us2.length) {
            usArrayLenght = us1.length;
        } else {
            usArrayLenght = us2.length;
        }
        
        for (int i = 0; i < usArrayLenght; i++) {
            
            /**
             * Here we check if the values between the two sensors are consistent, if they are not we throw an
             * IllegalArgumentException
             */
            
            if((us1[i] > us2[i]+5) || (us1[i] < us2[i]-5) || (us2[i] < us1[i]-5) || (us2[i] > us1[i]+5)){
                throw new IllegalArgumentException("Sensors different output!");
            }
            
            /**
             * Here we check if the values are within the range, if they are not we throw an
             * IllegalArgumentException
             */
            if (us1[i] < 0 || us2[i] < 0 || us1[i] > 200 || us2[i] > 200) {
                throw new IllegalArgumentException();
            }
            
            /**
             * If the value is within the range we take that value and add it to the average.
             * We increment the counter and we will use it later for doing the average operation.
             *
             */
            if (us1[i] <= 200 && us1[i] > 0) {
                average += us1[i];
                count++;
            }
            if (us2[i] <= 200 && us2[i] > 0) {
                average += us2[i];
                count++;
            }
        }
        
        /**
         * Here we assign distance equal to the average distance
         *
         */
        if (average > 0 && count > 0) {
            distance = average / count;
        }
        
        /**
         * when everything is done we return the distance
         */
        
        return distance;
    }
    
    
    public Position moveBackward(UltrasoundSensor ultrasoundOne, UltrasoundSensor ultrasoundTwo)
    throws StreetLengthException {
        
        Position pos = getPosition();
        /**
         * If the car is not parked
         */
        
        if (!pos.getParkingStatus()) {
            /**
             * if the car is on the street within the parameters of the street,
             * then move backward
             */
            if (pos.getPositionOnStreet() >= 1 && pos.getPositionOnStreet() <= 500) {
                pos.setPosition(pos.getPositionOnStreet() - 1);
            } else
                throw new StreetLengthException();
        }
        /**
         * we return the car position.
         */
        return pos;
    }
    
    //Does the parking maneuver
    public void parkingManeuver(){
        System.out.println("Parking the car...");
        position.setParked(true);
    }
    
    //Assuming that sensors return int. values which represent meters.
    //2 = 0, 3 = 1; this is for the random generator
    public void park(int sensor1, int sensor2) throws StreetLengthException {
        int i = 0, distance = 0;
        UltrasoundSensor ultrasoundOne = new UltrasoundSensor(), ultrasoundTwo = new UltrasoundSensor();
        
        /**
         * here if the car is already parked it just returns
         */
        if(position.getParkingStatus()==true){
            return;
        }
        
        /**
         * while the car is not parked it will look for an empty spot
         */
        while(position.getParkingStatus()== false){
            /**
             * Here we depending if we input 3,3 2,2 or 1,1 our method will return different outputs of the sensors
             * check UltrasoundSensor class.
             */
            UltrasoundSensor.setUltrasoundSensorOne(UltrasoundSensor.getUltrasonicSensorOne(sensor1));
            UltrasoundSensor.setUltrasoundSensorTwo(UltrasoundSensor.getUltrasoundSensorTwo(sensor2));
            
            /**
             * Here we measure if there is something to the right
             */
            distance = isEmpty(ultrasoundOne, ultrasoundTwo);
            
            /**
             * If the distance is 0 it means there is an empty spot, we count + 1
             */
            if(distance==0){
                i++;
                try {
                    /**
                     * The car moves forward to measure the next spot
                     * If the car reaches the end of the street it throws an exception
                     */
                    moveForward(); //Moves the car forward from its current location
                } catch (StreetLengthException e) {
                    throw e;
                }
            }
            /**
             * if we counted for 5 empty spots, the car makes the pre-built parking function.
             */
            if(i==5){
                parkingManeuver();
            }
            /**
             * This will reset the counter if the car measures something in the right side.
             */
            if(distance>0){
                i=0;
                try {
                    /**
                     * The car moves forward to measure the next spot.
                     */
                    moveForward(); //Moves the car forward from its current location
                } catch (StreetLengthException e) {
                    throw e;
                }
            }
        }
    }
    
    public String steerLeft() {
        /**
         * Here we return a string so later we can test it to know if it turned left.
         */
        String left = "The car turned left";
        return left;
    }
    
    public void unpark() {
        /**
         * here we just return in case the car is not parked.
         */
        if(position.getParkingStatus() == false){
            return;
        }
        steerLeft();
        try {
            /**
             * The car moves forward
             */
            moveForward();
            position.setParked(false);
        } catch (StreetLengthException e) {
        }  
    }
    
    public Position whereIs() throws StreetLengthException {
        /**
         * If the car is outside the ranges of the street we throw an StreetLengthException
         */
        if(getPosition().getPositionOnStreet()>500 || getPosition().getPositionOnStreet()<0){
            throw new StreetLengthException();
        }
        /**
         * This returns the car position
         */
        return getPosition();
    }
    
    /**
     * Method that returns the position of the car.
     */
    public Position getPosition() {
        return position;
    }
    
    /**
     * Set method
     */
    
    public void setuOne(UltrasoundSensor uOne) {
        this.uOne = uOne;
    }
    
    /**
     * Set method
     */
    
    public void setuTwo(UltrasoundSensor uTwo) {
        this.uTwo = uTwo;
    }
}
