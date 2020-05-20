import java.util.Scanner;
public class Car 
{
	private double fuel;
	private int[] position = new int[2];
	
	public double getFuel()
	{
		return fuel;
	}
	public int[] getPosition()
	{
		return position;
	}
	public int getPosition(int d)
	{
		return position[d];
	}
	
	public void setFuel(double fuel)
	{
		this.fuel = fuel;
	}
	public void setPosition(int x, int y)
	{
		position[0] = x;
		position[1] = y;
	}
	public void setPositionX(int x)
	{
		position[0] = x;
	}
	public void setPositionY(int y)
	{
		position[1] = y;
	}
	
	public byte outOfFuel()
	{
		byte choice;
		choice = choice();
		
		if (choice == 0)
		{
			setPosition(0,0);
		}
		return choice;
		
	}
	public byte choice()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Press 0 to start from the begging with a random amount of fuel (1-120)");
		System.out.println("Press  1-6 to skip amount of rounds and gain 20fuel per round");
		
		return scan.nextByte();

	}
}
