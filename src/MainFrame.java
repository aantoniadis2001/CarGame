import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	private JPanel contentPanel;
	private JPanel rollPanel;
	private JPanel boardPanel;
	private JPanel statsPanel;
	
	private JLabel die;
	private JButton rollDie;
	private JLabel fuelLabel1;  
	private JLabel fuelLabel2;
	private JLabel []fuel = new JLabel[2];
	private JLabel [][]cell;
	
	private ImageIcon blueCar = new ImageIcon("./src/images/blueCar.png");
	private ImageIcon redCar = new ImageIcon("./src/images/redCar.png");
	private ImageIcon bothCars = new ImageIcon("./src/images/bothCars.png");
	
	private int[][] carPosition = new int[2][2];
	private int result;
	private boolean clicked = false;
	
	public MainFrame(Grid grid) 
	{
		this.setTitle("Car Game");
		int[] dimensions = grid.getDimension();
		//Create swing components
		contentPanel = (JPanel) this.getContentPane();
		statsPanel = new JPanel();
		boardPanel = new JPanel();
		rollPanel = new JPanel();
		
		boardPanel.setLayout(new GridLayout(dimensions[0], dimensions[1]));
		statsPanel.setLayout(new GridLayout(1, 4));
		rollPanel.setLayout(new GridLayout(2,1));
		
		fuelLabel1 = new JLabel("Player 1's fuel : ");
		fuelLabel2 = new JLabel("Player 2's fuel : ");
		fuel[0] = new JLabel("120");
		fuel[1] = new JLabel("120");
		
		rollDie = new JButton("Roll Die");
		die = new JLabel("");
		 		
		//set window options
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400, 1200);
		
		//create cells
		cell = new JLabel[dimensions[0]][dimensions[1]];
		
		for(int i = 0; i < dimensions[0]; i++ ) {
			for(int j = 0; j < dimensions[1]; j++) {
				//label start and end
				if(i == 0 && j == 0) {
					cell[i][j] = new JLabel("Start");
				}
				else if(i == (dimensions[0] - 1) && j == (dimensions[1] - 1)) {
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
		statsPanel.add(fuelLabel1);
		statsPanel.add(fuel[0]);
		statsPanel.add(fuelLabel2);
		statsPanel.add(fuel[1]);
		
		rollPanel.add(rollDie);
		rollPanel.add(die);
		
		contentPanel.add(rollPanel, BorderLayout.NORTH);
		contentPanel.add(boardPanel, BorderLayout.CENTER);
		contentPanel.add(statsPanel, BorderLayout.SOUTH);
		
		rollDie.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Random rand = new Random();
				result = rand.nextInt(6) + 1;	
				updateDieLabel(result);
				clicked = true;
			}
		});
	}
	
	private void updateDieLabel(int roll) {
		die.setText("A " + Integer.toString(roll) + " has been rolled");
	} 
	public void updateFuelLabel(int player, double fuel) {
		this.fuel[player].setText(Double.toString(fuel));
	}
	public void updateCarPosition(int player, int []destination) {
		int rotation = 0;
		RotatedIcon car, removedCar = null;
		
		if(carPosition[(player)][0] == carPosition[(player == 0 ? 1 : 0)][0] && carPosition[(player)][1] == carPosition[(player == 0 ? 1 : 0)][1])
		{
			if(player == 0) {
				if(carPosition[player][0] % 2 != 0) {
					removedCar = new RotatedIcon(redCar, 180);
				}else {
					removedCar = new RotatedIcon(redCar, 0);
				}
			}else {
				if(carPosition[player][0] % 2 != 1) {
					removedCar = new RotatedIcon(blueCar, 180);
				}else {
					removedCar = new RotatedIcon(blueCar, 0);
				}
			}
		}
		
		cell[carPosition[player][1]][carPosition[player][0]].setIcon(removedCar);
		
		if((destination[1] % 2) != 0)
			rotation = 180;	
					
		carPosition[player][0] = destination[0];
		carPosition[player][1] = destination[1];
		
		if(destination[0] == carPosition[(player == 0 ? 1 : 0)][0] && destination[1] == carPosition[(player == 0 ? 1 : 0)][1]) 
		{
			car = new RotatedIcon(bothCars, rotation);
		}
		else if(player == 0)
		{
			car = new RotatedIcon(blueCar, rotation);
		}
		else 
		{
			car = new RotatedIcon(redCar, rotation);
		}
		
		cell[destination[1]][destination[0]].setIcon(car);
	}
	
	public int getResult() 
	{
		return result;
	}
	public boolean beenClicked() 
	{
		return clicked;
	}
	public void setClickedFalse() 
	{
		clicked = false;
	}
}
