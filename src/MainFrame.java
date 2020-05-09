import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.scene.layout.Border;

public class MainFrame extends JFrame {
	
	final static int X = 0;
	final static int Y = 1;
	
	Container mainWindow = getContentPane();
	
	JPanel boardPanel;
	JPanel infoPanel = new JPanel(new GridLayout(1,4));
	
	JButton roll = new JButton("Roll dice");
	
	JLabel fuelLabel1 = new JLabel("Player 1's fuel : ");
	JLabel fuelLabel2 = new JLabel("Player 2's fuel : ");
	JLabel fuelPlayer1 = new JLabel("120");
	JLabel fuelPlayer2 = new JLabel("110");
	
	public MainFrame(String title, Grid grid) {
		super(title);
		
		//get grid dimensions
		final int []dimensions = grid.getDimensions();
		final int size = dimensions[X] * dimensions[Y];
		
		//create grid
		boardPanel = new JPanel(new GridLayout(dimensions[X], dimensions[Y]));
		
		//set window options
		mainWindow.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		
		//create cells
		JLabel []cell = new JLabel[size];
		
		for(int i = 0; i < size; i++ ) {
			
			//label start and end
			if(i == 0) {
				cell[i] = new JLabel("Start");
			}else if(i == size - 1) {
				cell[i] = new JLabel("End");
			}else {
				cell[i] = new JLabel(" ");
			}
			//set cell properties
			cell[i].setBorder(BorderFactory.createLineBorder(Color.black));
			cell[i].setOpaque(true);
						
			//fill grid in
			switch(grid.getCell(i)) {
				case 0:
					cell[i].setBackground(Color.gray);
					break;
				case 1:
					cell[i].setBackground(Color.green);
					break;
				case 2:
					cell[i].setBackground(Color.black);
					break;
			}
			boardPanel.add(cell[i]);
		}
		//add components
		infoPanel.add(fuelLabel1);
		infoPanel.add(fuelPlayer1);
		infoPanel.add(fuelLabel2);
		infoPanel.add(fuelPlayer2);
		
		mainWindow.add(roll, BorderLayout.NORTH);
		mainWindow.add(boardPanel, BorderLayout.CENTER);
		mainWindow.add(infoPanel, BorderLayout.SOUTH);
	}
	
}
