import java.util.concurrent.TimeUnit;

public class CarGame
{	
	private static int diceRoll;
	
	public static void main(String[] args) 
	{	
		int turnCounter = 1;
		int p1OutOfFuelTurns = 0;
		int p2OutOfFuelTurns = 0;
		
		Car []car = new Car[2];
		car[0] = new Car();
		car[1] = new Car();
		
		int[] p1Position = car[0].getPosition();
		int[] p2Position = car[1].getPosition();
		
		
		
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
		
		Grid grid = new Grid(gig.getDim(), gig.getPerc());				
		MainFrame frame = new MainFrame("Car game", grid);
		frame.setVisible(true);
		
		NoFuelGui nfg = new NoFuelGui(" yes");
		nfg.setVisible(false);
		
		int[] maxDim = grid.getDimensions();
		int borderCounter;
		int cellType;
		
		GreyTile grey = new GreyTile();
		GreenTile green = new GreenTile();
		BlackTile black = new BlackTile();
		int choice;
		
		while((car[0].getPosition() == maxDim) && (car[1].getPosition() == maxDim))
		{
			if(turnCounter % 2 != 0)
			{
				cellType = grid.getCell(car[0].getPosition(0), car[0].getPosition(1));
				
				if(car[0].getFuel() == 0)
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
						car[0].setPosition(0, 0);
						car[0].setFuel(20 + (int)(Math.random() * ((120 - 20) + 20)));
					}
					else
					{
						p1OutOfFuelTurns = choice;
						car[0].setFuel(choice * 20);
					}
				}
				
				if (p1OutOfFuelTurns != 0)
				{
					p1OutOfFuelTurns--;
					break;
				}
				else
				{
					rollDice();
					borderCounter = maxDim[1] - (car[0].getPosition(1) + diceRoll);
					if(borderCounter >= 0 )
					{
						car[0].setPositionX(car[0].getPosition(0) + diceRoll);
					}
					else
					{
						car[0].setPosition(maxDim[0] + borderCounter, car[0].getPosition(1) + 1);
						
					}
				}
				cellType = grid.getCell(car[0].getPosition(0), car[0].getPosition(1));
				switch (cellType)
				{
					case 0:
						grey.doEffect(car[0]);
					case 1:
						green.doEffect(car[0]);
					case 2:
						black.doEffect(car[0]);
				}
			}
			
		}
	}
	public static void rollDice()
	{
		diceRoll = 1 + (int)(Math.random() * ((6 - 1) + 1));
	}
}
