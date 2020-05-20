import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.javafx.geom.transform.BaseTransform.Degree;

public class MainFrame extends JFrame {
	
	final static int X = 1;
	final static int Y = 0;
	
	private JPanel mainWindow;
	
	private JPanel rollPanel;
	private JPanel boardPanel;
	private JPanel infoPanel;
	
	private JLabel die;
	private JLabel fuelLabel1; 
	private JLabel fuelLabel2;
	private JLabel []fuel = new JLabel[2];
	private JLabel [][]cell;
	
	private ImageIcon blueCar = new ImageIcon("./src/images/blueCar.png");
	private ImageIcon redCar = new ImageIcon("./src/images/redCar.png");
	private ImageIcon bothCars = new ImageIcon("./src/images/bothCars.png");
	
	private int[][] carPosition = new int[2][2];
	
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
		setSize(500, 500);
		
		//create cells
		cell = new JLabel[dimensions[X]][dimensions[Y]];
		
		for(int i = 0; i < dimensions[X]; i++ ) {
			for(int j = 0; j < dimensions[Y]; j++) {
				//label start and end
				if(i == 0 && j == 0) {
					cell[i][j] = new JLabel("Start");
				}
				else if(i == (dimensions[X] - 1) && j == (dimensions[Y] - 1)) {
					if(i % 2 == 1) {
						cell[i][0].setText("End");
						cell[i][j] = new JLabel();
					}else {
						cell[i][j] = new JLabel("End");
					}
				}
				else {
					cell[i][j] = new JLabel();
				}
				//set cell properties
				System.out.println(i +" " + j);
				cell[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				cell[i][j].setOpaque(true);
							
				//fill grid in
				switch(grid.getCell(i,j)) {
					case 0:
						cell[i][j].setBackground(Color.GRAY);
						break;
					case 1:
						cell[i][j].setBackground(Color.GREEN);
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
	public void updateCarPosition(int player, int []destination) {
		int rotation = 0, temp;
		RotatedIcon car;
		
		if((destination[X] % 2) != 0) {
			rotation = 180;	
			temp = Math.abs((cell[0].length - 1) - destination[0]);	
		}else {
			temp = destination[0];
		}
		
//		System.out.println("car pos " +carPosition[(player == 0 ? 1 : 0)][0] + " " + carPosition[(player)][1] + " " + player);
//		System.out.println("Destination " + destination[0] + " " + destination[1] + " " + (player == 0 ? 1 : 0) + "\n");
			
		if(destination[0] == carPosition[(player == 0 ? 1 : 0)][0] && destination[1] == carPosition[(player == 0 ? 1 : 0)][1]) {
			car = new RotatedIcon(bothCars, rotation);
//			System.out.println("VICTORY");
		}
		if(player == 0){
			car = new RotatedIcon(blueCar, rotation);
		}else {
			car = new RotatedIcon(redCar, rotation);
		}
		
		cell[carPosition[player][1]][carPosition[player][0]].setIcon(null);
		cell[destination[1]][temp].setIcon(car);
		
		
		carPosition[player][0] = temp;
		carPosition[player][1] = destination[1];
	}
}
