
public class GreenTile 
{
	private static final double EXTRA_FUEL = 1.5;
	
	public void doEffect(Car car)
	{
		car.setFuel(car.getFuel() * EXTRA_FUEL);
	}
}
