import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class Gui extends JFrame {
	public static void main(String[] args) {	
	
	NoFuelGui nfg = new NoFuelGui(" yes");
	nfg.setVisible(true);
	
	GridInitializationGui gig = new GridInitializationGui("Set game parameters");
	gig.setVisible(true);
	 
	while(gig.isVisible()) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	Grid grid = new Grid(gig.getDim(), gig.getPerc());
					
	MainFrame frame = new MainFrame("Car game", grid);
	frame.setVisible(true);
			
	}
}
