package main;

public class Assignment1 {
		
	//move forward if the position is < 499
	public double MoveForward(double position){
		
		if (position < 499){
			return position+1;
		}
		else 
			return position;
	}
	
	//check the ultrasounds, make their average
	//if any of the ultrasound is very noisy, then ignore it
	public int isEmpty(UltraSound uOne, UltraSound uTwo){
		int dUOne = 0;
		int dUTwo = 0;
		
		for (int i = 0; i < 5; i++){
			if (!uOne.veryNoisy()){
				dUOne += uOne.checkDistance();
			}
			if (!uTwo.veryNoisy()){
				dUTwo += uTwo.checkDistance();
				}
			}
		
		return (dUOne + dUTwo)/5;
	}
	
	//move backward if the position is >= 1
	public double MoveBackward(double position){
		
		if (position >= 1){
			return position - 1;
		}
		else 
			return position;
	}
	
	

}
