import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Grid {
	private ArrayList<Integer[]> grid = new ArrayList<Integer[]>();
	private int[] gridDimension = new int[2];
	private int[] percentage = new int[3];
	
	public Grid() {
		initializeGrid();
	}

	private void defineTilePercentage() {
		
		Scanner scan = new Scanner(System.in); 
		
		do {
			System.out.println("Enter the percentage of tiles you'd like to be Gray : ");
			percentage[0] = scan.nextInt();
			
			System.out.println("Enter the percentage of tiles you'd like to be Green : ");
			percentage[1] = scan.nextInt();
			
			System.out.println("Enter the percentage of tiles you'd like to be Black : ");
			percentage[2] = scan.nextInt();
			
		}while((percentage[0] + percentage[1] + percentage[2]) != 100);
		
	}
	private void defineGridDimensions() {
		Scanner scan = new Scanner(System.in);
		
		do {
			System.out.println("Enter the desired grid width : ");
			gridDimension[0] = scan.nextInt();
			
			System.out.println("Enter the desired grid height : ");
			gridDimension[1] = scan.nextInt();
		
		}while(!(gridDimension[0] >= 1 || gridDimension[1] >= 1));
		
		
	}
	public void initializeGrid() {
		int rng;
		Integer[] choice = new Integer[2];
		Random rand = new Random();
		
		defineTilePercentage();
		defineGridDimensions();
		
		for(int i = 0; i < (gridDimension[0] * gridDimension[1]); i++){
			choice[0] = i;
			
			rng = rand.nextInt(101);
			if(rng < percentage[0]) {
				choice[1] = 0;
			}else if(rng < percentage[0] + percentage[1]) {
				choice[1] = 1;
			}else {
				choice[1] = 2;
			}
			
			grid.add(choice);
			
			System.out.println("Grid : " + choice[0] + " type : " + choice[1]);
		}
	}
}
