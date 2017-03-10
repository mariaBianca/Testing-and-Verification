package main;

public class MovementController implements MovementControllerInterface {

	private boolean isParked;
	
	public MovementController(boolean parked) throws SensorInputException {
		
		this.isParked=parked;
	}

	@Override
	public boolean accelerate(int position) throws SensorInputException {

		if(position < 500 && position>= 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean reverse(int position) throws SensorInputException {
		if(position <= 500 && position >= 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isParked() {
		return this.isParked;
	}

	public void setParked(boolean parked) {
		this.isParked = parked;
	}
}