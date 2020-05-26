import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class CarGame
{	
	private static Car[] car;
	private static MainFrame frame;
	private static GreyTile grey = new GreyTile();
	private static GreenTile green = new GreenTile();
	private static BlackTile black = new BlackTile();
	private static Grid grid;
	private static NoFuelGui nfg;
	private static int[] pOutOfFuelTurns = {0,0};
	private static int[] maxDim = new int[2];
	private static int[] end = new int[2];
	private static boolean endGame = false;
	 
	public static void main(String[] args) 
	{	
		int turnCounter = 0;
		
		car = new Car[2];
		car[0] = new Car();
		car[1] = new Car();
		
		GridInitializationGui gig = new GridInitializationGui();
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
		frame = new MainFrame(grid);
		frame.setVisible(true);
		
		nfg = new NoFuelGui();
		nfg.setVisible(false);
		
		maxDim[0] = grid.getDimension(1);
		maxDim[1] = grid.getDimension(0);

		while((car[0].getPosition() != end) && (car[1].getPosition() != end))
		{
			turns(turnCounter % 2);
			turnCounter++;
		}
	}
	
	public static void turns(int p)
	{
		int diceRoll;
		int choice;
		int cellType;
		
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
			frame.updateFuelLabel(p, car[p].getFuel());
		}
		else
		{	
			if (pOutOfFuelTurns[p] != 0)
			{
				pOutOfFuelTurns[p]--;
			}
			else
			{
				while(!frame.beenClicked())
				{
					try 
					{
		                TimeUnit.MILLISECONDS.sleep(250);
		            } 
					catch (InterruptedException e) 
					{
		                e.printStackTrace();
		            }
				}
				diceRoll = frame.getResult();
				frame.setClickedFalse();

				for (int i = 0; i < diceRoll; i++)
				{
					if (car[p].getPosition() == end)
					{
						JOptionPane.showMessageDialog(null, "Congratulations player " + p + 1 + " YOU WON");
						endGame = true;
						break;
					}
					else if (car[p].getPosition(1) % 2 == 0)
					{
						if(car[p].getPosition(0) < maxDim[0] - 1)
							car[p].setPositionX(car[p].getPosition(0) + 1);
						else
							car[p].setPositionY(car[p].getPosition(1) + 1);
					}
					else
					{
						if(car[p].getPosition(0) == 0)
							car[p].setPositionY(car[p].getPosition(1) + 1);
						else
							car[p].setPositionX(car[p].getPosition(0) - 1);
					}
					
					frame.updateCarPosition(p, car[p].getPosition());
					try {
						TimeUnit.MILLISECONDS.sleep(250);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				cellType = grid.getCell(car[p].getPosition(1), car[p].getPosition(0));
				
				if (!endGame)
				{
					if(cellType == 0)
					{
						for (int i = 0; i < diceRoll; i++)
						{
							grey.doEffect(car[p]);
						}
						frame.updateFuelLabel(p, car[p].getFuel());
					}
					else if(cellType == 1)
					{
						green.doEffect(car[p]);
						frame.updateFuelLabel(p, car[p].getFuel());
					}
					else
					{
						black.doEffect(car[p]);
					}
					
					frame.updateCarPosition(p, car[p].getPosition());
				}
				
			}
		}
	}
	
	public static void findEnd()
	{
		if (maxDim[1] % 2 == 0)
		{
			end[0] = 0;
			end[1] = maxDim[1] - 1;
		}
		else
		{
			end[0] = maxDim[0] - 1;
			end[1] = maxDim[1] - 1;
		}
	}
}
