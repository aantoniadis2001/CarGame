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
	
	private JPanel mainWindow;
	private JPanel mainPanel;
	
	private JLabel xLabel;
	private JLabel yLabel;
	private JLabel grayLabel;
	private JLabel greenLabel;
	private JLabel blackLabel;
	
	private JTextField xText;
	private JTextField yText;
	private JTextField grayText;
	private JTextField greenText;
	private JTextField blackText;
	
	private JButton okButton;
	
	private int[] dim = new int[2];
	private int[] perc = new int[3];
	
	public GridInitializationGui(String title) {
		super(title);
		
		mainWindow = (JPanel) this.getContentPane();
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(5, 2));
		
		xLabel = new JLabel("Game board width (X = 2+) : ");
		yLabel = new JLabel("Game board height (Y = 2+) : ");
		grayLabel = new JLabel("Percentage of gray cells (0 - 100) : ");
		greenLabel = new JLabel("Percentage of green cells (0 - 100) : ");
		blackLabel = new JLabel("Percentage of black cells (0 - 100) : ");
		
		xText = new JTextField(10);
		yText = new JTextField(10);
		grayText = new JTextField(3);
		greenText = new JTextField(3);
		blackText = new JTextField(3);
		
		okButton = new JButton("OK");
		
		//Setting window settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,140);
		setResizable(false);
	
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
		mainWindow.add(okButton, BorderLayout.SOUTH);
		
		okButton.addActionListener(new ActionListener() {
			
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
