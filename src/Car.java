import java.util.Scanner;
public class Car 
{
	private double fuel;
	private int position;
	
	public double getFuel()
	{
		return fuel;
	}
	public int getPosition()
	{
		return position;
	}
	
	public void setFuel(double fuel)
	{
		this.fuel = fuel;
	}
	public void setPosition(int position)
	{
		this.position = position;
	}
	
	public byte outOfFuel()
	{
		byte choice;
		choice = choice();
		
		if (choice == 0)
		{
			position = 0;
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
