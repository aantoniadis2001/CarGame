import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Visibility;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.corba.se.spi.ior.MakeImmutable;

public class GridInitializationGui extends JFrame{
	
	private static final int X = 0;
	private static final int Y = 1;
	
	private Container mainWindow = getContentPane();
	private JPanel mainPanel = new JPanel();
	
	private JLabel xLabel = new JLabel("Game board width (X = 2+) : ");
	private JLabel yLabel = new JLabel("Game board height (Y = 2+) : ");
	private JLabel grayLabel = new JLabel("Percentage of gray cells (0 - 100) : ");
	private JLabel greenLabel = new JLabel("Percentage of green cells (0 - 100) : ");
	private JLabel blackLabel = new JLabel("Percentage of black cells (0 - 100) : ");
	
	private JTextField xText = new JTextField(10);
	private JTextField yText = new JTextField(10);
	private JTextField grayText = new JTextField(3);
	private JTextField greenText = new JTextField(3);
	private JTextField blackText = new JTextField(3);
	
	private JButton add = new JButton("OK");
	
	private int[] dim = new int[2];
	private int[] perc = new int[3];
	
	public GridInitializationGui(String title) {
		super(title);
		
		//Setting window settings
		mainWindow.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,140);
		setResizable(false);
		
		mainPanel.setLayout(new GridLayout(5,2));
	
		//Adding Swing components to window
		mainPanel.add(xLabel);
		mainPanel.add(xText);
		
		mainPanel.add(yLabel);
		mainPanel.add(yText);
		
		mainPanel.add(grayLabel);
		mainPanel.add(grayText);
		
		mainPanel.add(greenLabel);
		mainPanel.add(greenText);
		
		mainPanel.add(blackLabel);
		mainPanel.add(blackText);
		
		mainWindow.add(mainPanel,BorderLayout.CENTER);
		mainWindow.add(add, BorderLayout.SOUTH);
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				okButtonAction();				
			}
		});
		
	}
	
	private void okButtonAction() {
		
		dim[X] = Integer.parseInt(xText.getText());
		dim[Y] = Integer.parseInt(yText.getText());
		perc[0] = Integer.parseInt(grayText.getText());
		perc[1] = Integer.parseInt(greenText.getText());
		perc[2] = Integer.parseInt(blackText.getText());
		
		if(isValidData(dim, perc)) {
			setVisible(false);
		}	
		else {
			JOptionPane.showMessageDialog(null, "Invalid parameters, please try again");
			xText.setText("");
			yText.setText("");
			grayText.setText("");
			greenText.setText("");
			blackText.setText("");
		}
	}
	private boolean isValidData(int dim[], int perc[]) {
		return (dim[X] > 1 &&  dim[Y] > 1) && ((perc[0] + perc[1] + perc[2]) == 100) ? true : false;
	}
	public int[] getDim() {
		return dim;
	}
	
	public int[] getPerc() {
		return perc;
	}
 
  
}
