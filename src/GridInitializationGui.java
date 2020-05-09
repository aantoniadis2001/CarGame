import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sun.corba.se.spi.ior.MakeImmutable;

public class GridInitializationGui extends JFrame{
	
	Container mainWindow = getContentPane();
	
	JLabel xLabel = new JLabel("Game board width (X) : ");
	JLabel yLabel = new JLabel("Game board height (Y) : ");
	JLabel grayLabel = new JLabel("Percentage of gray cells (0 - 100) : ");
	JLabel greenLabel = new JLabel("Percentage of green cells (0 - 100) : ");
	JLabel blackLabel = new JLabel("Percentage of black cells (0 - 100) : ");
	
	JTextField xText = new JTextField(10);
	JTextField yText = new JTextField(10);
	JTextField grayText = new JTextField(3);
	JTextField greenText = new JTextField(3);
	JTextField blackText = new JTextField(3);
	
	public GridInitializationGui(String title) {
		super(title);
		
		//Setting window settings
		mainWindow.setLayout(new GridLayout(5,2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,140);
		setResizable(false);
		
		//Adding Swing components to window
		mainWindow.add(xLabel);
		mainWindow.add(xText);
		
		mainWindow.add(yLabel);
		mainWindow.add(yText);
		
		mainWindow.add(grayLabel);
		mainWindow.add(grayText);
		
		mainWindow.add(greenLabel);
		mainWindow.add(greenText);
		
		mainWindow.add(blackLabel);
		mainWindow.add(blackText);
		
		
		
		
	}
	

}
