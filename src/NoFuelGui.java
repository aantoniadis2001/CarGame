import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NoFuelGui extends JFrame{
	
	private Container mainWindow = getContentPane();
	private JPanel buttonWindow = new JPanel(new GridLayout(1,2));
	
	private JLabel label;
	private JTextField numberText;
	private JButton ok;
	
	private int rounds;
	
	public NoFuelGui(String title) {
		super(title);
		
		mainWindow = (JPanel) this.getContentPane();
		buttonWindow = new JPanel();
		buttonWindow.setLayout(new GridLayout(1, 4));
		
		label = new JLabel("Rounds to wait (0 returns you to start) : ");
		numberText = new JTextField();
		ok = new JButton("OK");
		
		setSize(500,90);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buttonWindow.add(label);
		buttonWindow.add(numberText);
		
		mainWindow.add(buttonWindow, BorderLayout.CENTER);
		mainWindow.add(ok, BorderLayout.SOUTH);
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				okAction();
				
			}
		});
	}
	
	private void okAction() {
		rounds = Integer.parseInt(numberText.getText());
		
		if(rounds < 0 || rounds > 6) {
			JOptionPane.showMessageDialog(null, "Invalid parameters, please try again");
			numberText.setText("");
		}
		else {
			setVisible(false);
		}
		
	}
	
	public int getRounds() {
		return rounds;
	}
}
