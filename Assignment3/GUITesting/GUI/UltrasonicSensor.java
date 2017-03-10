package main;

public class UltrasonicSensor {
	private int[] ultrasonicArray;

	public UltrasonicSensor (){
		int[] inputArray = {1,1,1,1,1};
		setUltrasonicArray(inputArray);
	}
		
	public int[] getUltrasonicArray() {
		return ultrasonicArray;
	}
	
	public void setUltrasonicArray(int[] inputArray1) {
		this.ultrasonicArray = inputArray1;
	}



}
