package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GraphicalUI {

	public static void main(String[] args) {
		CarImpl car;
		
		//parking spots
		ArrayList<Integer> parkingSpotsArray = new ArrayList<>();
		for(int i=0; i < 502; i++){
			int j = ThreadLocalRandom.current().nextInt(0, 2);
			parkingSpotsArray.add(new Integer(j));
		}
		
		car = new CarImpl(15, false, parkingSpotsArray);
		
		JLabel movedPos=new JLabel();
		movedPos.setBounds(40, 120, 120, 40);
		movedPos.setForeground(Color.BLACK);
		movedPos.setText("STARTING POSITION");
		
		JFrame frame = new JFrame("CarGUI");
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(new Dimension(600, 300));
        frame.pack();

        JTextField carPosition = new JTextField(5);
		carPosition.setBounds(450, 20, 120, 20);
		carPosition.setText(""+car.getLocation());
		carPosition.setEditable(false);
        
		JTextField parkinSpots = new JTextField(5);
		parkinSpots.setEditable(false);
		parkinSpots.setForeground(Color.RED);
		parkinSpots.setText("NO");
		parkinSpots.setBounds(450, 200, 120, 20);
		
		JTextField freeSpots = new JTextField(5);
		freeSpots.setEditable(false);
		if(parkingSpotsArray.get(car.getLocation()) == 0){
			freeSpots.setForeground(Color.GREEN);
			freeSpots.setText("FREE");
		}else{
			freeSpots.setForeground(Color.RED);
			freeSpots.setText("TAKEN");
		}
		freeSpots.setBounds(450, 110, 120, 20);
		
		JButton moveForward = new JButton("^   Forward   ^");
		moveForward.setBounds(20, 20, 120, 30);
		
		JButton moveBackward = new JButton("v Backward v");
		moveBackward.setBounds(20, 60, 120, 30);
		
		JButton park = new JButton("Park");
		park.setBounds(20, 200, 120, 40);
		JButton unPark = new JButton("Unpark");
		unPark.setBounds(20, 250, 120, 40);
		
		moveForward.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent ae){
		    	   int i = car.getLocation();
		           try {
					car.moveForward();
				} catch (SensorInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   		carPosition.setText(""+car.getLocation());
		   		if(i == car.getLocation()){
		   			movedPos.setForeground(Color.RED);
		   			movedPos.setText("CANNOT MOVE");
		   		}else{
		   			movedPos.setForeground(Color.BLUE);
		   			movedPos.setText("MOVED FORWARDS");
		   		}
		   		if(parkingSpotsArray.get(car.getLocation()) == 0){
					freeSpots.setForeground(Color.GREEN);
					freeSpots.setText("FREE");
				}else{
					freeSpots.setForeground(Color.RED);
					freeSpots.setText("TAKEN");
				}
		       } 
		    });
		
		moveBackward.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent ae){
		           int i = car.getLocation();
		    	   try {
					car.moveBackward();
				} catch (SensorInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   		carPosition.setText(""+car.getLocation());
		   		if(i == car.getLocation()){
		   			movedPos.setForeground(Color.RED);
		   			movedPos.setText("CANNOT MOVE");
		   		}else{
		   			movedPos.setForeground(Color.BLUE);
		   			movedPos.setText("MOVED BACK");
		   		}
		   		if(parkingSpotsArray.get(car.getLocation()) == 0){
					freeSpots.setForeground(Color.GREEN);
					freeSpots.setText("FREE");
				}else{
					freeSpots.setForeground(Color.RED);
					freeSpots.setText("TAKEN");
				}
		       } 
		    });
		
		park.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent ae){
		           try {
					car.park();
				} catch (SensorInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		           if(car.getMovementController().isParked()){
		        	   parkinSpots.setForeground(Color.GREEN);
		        	   parkinSpots.setText("YES");
		           }else{
		        	   parkinSpots.setForeground(Color.RED);
		        	   parkinSpots.setText("NO");
		           }
		       } 
		    });
		
		unPark.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent ae){
		    	   car.unPark();

		           if(car.getMovementController().isParked()){
		        	   parkinSpots.setForeground(Color.GREEN);
		        	   parkinSpots.setText("YES");
		           }else{
		        	   parkinSpots.setForeground(Color.RED);
		        	   parkinSpots.setText("NO");
		           }
		       } 
		    });
		
		
		frame.getContentPane().add(panel);
		
		JLabel parkedLabel=new JLabel("Parked: ");
		parkedLabel.setBounds(350, 200, 120, 20);
		panel.add(parkedLabel);
		panel.add(parkinSpots);
		
		JLabel freeSpot=new JLabel("Free parking spot: ");
		freeSpot.setBounds(300, 110, 120, 20);
		panel.add(freeSpot);
		panel.add(freeSpots);
		
		
		JLabel positionLabel=new JLabel("Position: ");
		positionLabel.setBounds(350, 20, 120, 20);
		panel.add(positionLabel);
		panel.add(carPosition);
		
		panel.add(movedPos);
		
		panel.add(moveForward);
		panel.add(moveBackward);
		panel.add(park);
		panel.add(unPark);
		
        frame.setVisible(true);

	}
}