import java.util.Random;
public class GreyTile 
{	
	public void doEffect(Car car)
	{
		car.setFuel(car.getFuel() - generateFuelCost());
	}
	private int generateFuelCost()
	{
		Random r = new Random();
		return r.nextInt(3) + 1;
	}
}
