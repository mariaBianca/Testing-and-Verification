package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GraphicalUI {

	public static void main(String[] args) {
		CarImpl car;

		car = new CarImpl(1, false);
		
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
		           try {
					car.moveForward();
				} catch (SensorInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   		carPosition.setText(""+car.getLocation());
		       } 
		    });
		
		moveBackward.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent ae){
		           try {
					car.moveBackward();
				} catch (SensorInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   		carPosition.setText(""+car.getLocation());
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
		
		JLabel positionLabel=new JLabel("Position: ");
		positionLabel.setBounds(350, 20, 120, 20);
		panel.add(positionLabel);
		panel.add(carPosition);
		
		panel.add(moveForward);
		panel.add(moveBackward);
		panel.add(park);
		panel.add(unPark);
		
        frame.setVisible(true);

	}
}
