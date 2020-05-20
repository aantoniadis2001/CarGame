import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	final static int X = 0;
	final static int Y = 1;
	
	private JPanel mainWindow;
	
	private JPanel rollPanel;
	private JPanel boardPanel;
	private JPanel infoPanel;
	
	private JLabel die;
	private JLabel fuelLabel1; 
	private JLabel fuelLabel2;
	private JLabel []fuel = new JLabel[2];
	private JLabel [][]cell;
	
	public MainFrame(String title, Grid grid) {
		super(title);
		
		//Create swing components
		mainWindow = (JPanel) this.getContentPane();
		
		rollPanel = new JPanel();
		infoPanel = new JPanel();
		
		rollPanel.setLayout(new GridLayout(2,1));
		infoPanel.setLayout(new GridLayout(1, 4));
		
		fuelLabel1 = new JLabel("Player 1's fuel : ");
		fuelLabel2 = new JLabel("Player 2's fuel : ");
		fuel[0] = new JLabel("120");
		fuel[1] = new JLabel("120");
		
		die = new JLabel("Hit roll to start the game");
		//get grid dimensions
		final int[] dimensions = grid.getDimensions();
		 
		//create grid
		boardPanel = new JPanel(new GridLayout(dimensions[X], dimensions[Y]));
		
		//set window options
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,1000);
		
		//create cells
		cell = new JLabel[dimensions[X]][dimensions[Y]];
		
		for(int i = 0; i < dimensions[X]; i++ ) {
			for(int j = 0; j < dimensions[Y]; j++) {
				//label start and end
				if(i == 0 && j == 0) {
					cell[i][j] = new JLabel("Start");
				}
				else if(i == (dimensions[X] - 1) && j == (dimensions[Y] - 1)) {
					cell[i][j] = new JLabel("End");
				}
				else {
					cell[i][j] = new JLabel(" ");
				}
				//set cell properties
				cell[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				cell[i][j].setOpaque(true);
							
				//fill grid in
				switch(grid.getCell(i,j)) {
					case 0:
						cell[i][j].setBackground(Color.GRAY);
						break;
					case 1:
						cell[i][j].setBackground(Color.GRAY);
						break;
					case 2:
						cell[i][j].setBackground(Color.BLACK);
						break;
			}
				boardPanel.add(cell[i][j]);
			}
			
		}
		//add components
		infoPanel.add(fuelLabel1);
		infoPanel.add(fuel[0]);
		infoPanel.add(fuelLabel2);
		infoPanel.add(fuel[1]);
		
		mainWindow.add(die, BorderLayout.NORTH);
		mainWindow.add(boardPanel, BorderLayout.CENTER);
		mainWindow.add(infoPanel, BorderLayout.SOUTH);
	}
	
	public void updateDieLabel(int roll) {
		die.setText("A " + Integer.toString(roll) + " has been rolled");
	}
	public void updateFuelLabel(int player, int fuel) {
		this.fuel[player].setText(Integer.toString(fuel));
	}
	
}
