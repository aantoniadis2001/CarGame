import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Gui extends JFrame {
	public static void main(String[] args) {	
		
		Grid grid = new Grid();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GridInititalizationGui gig = new GridInititalizationGui("Set game parameters");
				gig.setVisible(true);
				
				MainFrame frame = new MainFrame("Car game", grid);
				frame.setVisible(true);
			}
		});
		
	}
}
