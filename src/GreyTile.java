import java.util.Random;
public class GreyTile 
{	
	private int fuelCost;
	public void doEffect(Car car)
	{
		fuelCost = generateFuelCost();
		if(car.getFuel() - fuelCost > 0)
			car.setFuel(car.getFuel() - fuelCost);
		else
			car.setFuel(0);
	}
	private int generateFuelCost()
	{
		Random r = new Random();
		return r.nextInt(3) + 1;
	}
}
