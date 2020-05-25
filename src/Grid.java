import java.util.Random;

public class Grid 
{
	private int[][] grid;
	private int[] maxDim = new int[2];
	private int[] percentage = new int[3];

	public Grid(int[] dim, int[] perc)
	{
		int rng;
		int choice;
		Random rand = new Random();
		maxDim = dim;
		percentage = perc;
		
		grid = new int[maxDim[0]][maxDim[1]];
		
		for(int i = 0; i < (maxDim[0]); i++)
		{
			for(int j = 0; j < maxDim[1]; j++) 
			{
				rng = rand.nextInt(100);
				if(rng < percentage[0] || (i == 0 && j == 0) || (i == maxDim[0] - 1 && j == maxDim[1] - 1)) 
				{
					choice = 0;
				} 
				else if(rng < percentage[0] + percentage[1]) 
				{
					choice = 1;
				}
				else 
				{
					choice = 2;
				}
				
				grid[i][j]  = choice;
			}
		}
	}
	
	//Getters
	public int[] getDimension()
	{
		return maxDim;
	}
	public int getCell(int x, int y) 
	{
		return grid[x][y];
	}
}