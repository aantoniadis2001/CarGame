import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Grid {

	private int[][] grid;
	private int[] gridDimension = new int[2];
	private int[] percentage = new int[3];
	
	public Grid(int[] dim, int[] perc) {
		int rng;
		Integer choice;
		Random rand = new Random();
		gridDimension = dim;
		percentage = perc;
		
		grid = new int[gridDimension[0]][gridDimension[1]];
		
		for(int i = 0; i < (gridDimension[0]); i++){
			for(int j = 0; j < gridDimension[1]; j++) {
				rng = rand.nextInt(101);
				if(rng < percentage[0]) {
					choice = 0;
				}else if(rng < percentage[0] + percentage[1]) {
					choice = 1;
				}else {
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
