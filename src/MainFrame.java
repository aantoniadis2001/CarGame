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
	
	private Container mainWindow = getContentPane();
	
	private JPanel boardPanel;
	private JPanel infoPanel = new JPanel(new GridLayout(1,4));
	
	private JButton roll = new JButton("Roll dice");
	
	private JLabel fuelLabel1 = new JLabel("Player 1's fuel : ");
	private JLabel fuelLabel2 = new JLabel("Player 2's fuel : ");
	private JLabel fuelPlayer1 = new JLabel("120");
	private JLabel fuelPlayer2 = new JLabel("110");
	private JLabel [][]cell;
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
		setSize(1000,1000);
		
		//create cells
		cell = new JLabel[dimensions[X]][dimensions[Y]];
		
		for(int i = 0; i < dimensions[X]; i++ ) {
			for(int j = 0; j < dimensions[Y]; j++) {
				//label start and end
				if(i == 0 && j == 0) {
					cell[i][j] = new JLabel("Start");
				}else if(i == (dimensions[X] - 1) && j == (dimensions[Y] - 1)) {
					cell[i][j] = new JLabel("End");
				}else {
					cell[i][j] = new JLabel(" ");
				}
				//set cell properties
				cell[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				cell[i][j].setOpaque(true);
							
				//fill grid in
				switch(grid.getCell(i,j)) {
					case 0:
						cell[i][j].setBackground(Color.gray);
						break;
					case 1:
						cell[i][j].setBackground(Color.green);
						break;
					case 2:
						cell[i][j].setBackground(Color.black);
						break;
			}
				boardPanel.add(cell[i][j]);
			}
			
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
