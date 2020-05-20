import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class CarGame
{	
	private static Car[] car;
	private static GreyTile grey = new GreyTile();
	private static GreenTile green = new GreenTile();
	private static BlackTile black = new BlackTile();
	private static Grid grid;
	private static NoFuelGui nfg;
	private static int[] pOutOfFuelTurns = {0,0};
	private static int[] maxDim;
	private static int diceRoll;
	
	public static void main(String[] args) 
	{	
		int turnCounter = 1;
		
		car = new Car[2];
		car[0] = new Car();
		car[1] = new Car();
		
		GridInitializationGui gig = new GridInitializationGui("Set game parameters");
		gig.setVisible(true);
		 
		while(gig.isVisible()) 
		{
			try 
			{
				TimeUnit.SECONDS.sleep(1);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
		grid = new Grid(gig.getDim(), gig.getPerc());				
		MainFrame frame = new MainFrame("Car Game", grid);
		frame.setVisible(true);
		
		nfg = new NoFuelGui("You are out of fuel!");
		nfg.setVisible(false);
		
		maxDim = grid.getDimensions();
		maxDim[0]++;
		maxDim[1]++;
		
		while((car[0].getPosition() != maxDim) && (car[1].getPosition() != maxDim))
		{
			if(turnCounter % 2 != 0)
			{
				turns(0);
			}
			else
			{
				turns(1);
			}
			turnCounter++;
		}
		
		if(car[0].getPosition() != maxDim)
		{
			JOptionPane.showMessageDialog(null, "Congratulations player 1, YOU WON");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Congratulations player 2, YOU WON");
		}
	}
	
	public static void turns(int p)
	{
		int choice;
		int cellType = grid.getCell(car[p].getPosition(0), car[p].getPosition(1));
		int borderCounter;
		diceRoll = 0;
		
		if(car[p].getFuel() == 0)
		{
			nfg.setVisible(true);
			while(nfg.isVisible())
			{
				try 
				{
					TimeUnit.SECONDS.sleep(1);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
			choice = nfg.getRounds();
			if(choice == 0)
			{
				car[p].setPosition(0, 0);
				car[p].setFuel(20 + (int)(Math.random() * ((120 - 20) + 20)));
			}
			else
			{
				pOutOfFuelTurns[p] = choice;
				car[p].setFuel(choice * 20);
			}
		}
		else
		{
				
			if (pOutOfFuelTurns[p] != 0)
			{
				pOutOfFuelTurns[p]--;
			}
			else
			{
				rollDice();
				borderCounter = maxDim[0] - (car[p].getPosition(0) + diceRoll);
				if(borderCounter >= 0 )
				{
					car[p].setPositionX(car[p].getPosition(0) + diceRoll);
				}
				else
				{
					car[p].setPosition(maxDim[0] + borderCounter, car[p].getPosition(1) + 1);
					
				}
			}
			cellType = grid.getCell(car[p].getPosition(0), car[p].getPosition(1));
			switch (cellType)
			{
				case 0:
					grey.doEffect(car[p]);
					break;
				case 1:
					green.doEffect(car[p]);
					break;
				case 2:
					black.doEffect(car[p]);
					break;
			}
		}
	}
	
	public static void rollDice()
	{
		diceRoll = 1 + (int)(Math.random() * ((6 - 1) + 1));
	}
}
