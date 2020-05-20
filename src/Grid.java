import java.util.Random;

public class Grid {
	final static int X = 1;
	final static int Y = 0;
	
	private int[][] grid;
	private int[] gridDimension = new int[2];
	private int[] percentage = new int[3];
	
	public Grid(int[] dim, int[] perc) {
		int rng;
		int choice;
		Random rand = new Random();
		gridDimension = dim;
		percentage = perc;
		
		grid = new int[gridDimension[X]][gridDimension[Y]];
		
		for(int i = 0; i < (gridDimension[X]); i++){
			for(int j = 0; j < gridDimension[Y]; j++) {
				rng = rand.nextInt(101);
				if(rng < percentage[0] || (i == 0 && j == 0) || (i == gridDimension[0] - 1 && j == gridDimension[1] - 1)) {
					choice = 0;
				} 
				else if(rng < percentage[0] + percentage[1]) {
					choice = 1;
				}
				else {
					choice = 2;
				}
				
				grid[i][j]  = choice;
			}	
			
		}
	}
 
	public int[] getDimensions() {
		return gridDimension;
	}
	public int getCell(int x, int y) {
		return grid[x][y];
	}

}
