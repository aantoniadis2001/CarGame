import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NoFuelGui extends JFrame
{
	private JPanel contentPanel;
	private JPanel mainPanel;
	
	private JLabel label;
	private JTextField numberText;
	private JButton okButton;
	
	private int rounds;
	
	public NoFuelGui()
	{
		this.setTitle("Your are out of Fuel!");
		
		contentPanel = (JPanel) this.getContentPane();
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 2));
		
		label = new JLabel("Rounds to wait (0 returns you to start) : ");
		numberText = new JTextField(3);
		okButton = new JButton("OK");
		
		setSize(500,90);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel.add(label);
		mainPanel.add(numberText);
		
		contentPanel.add(mainPanel, BorderLayout.CENTER);
		contentPanel.add(okButton, BorderLayout.SOUTH);
		
		okButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				okAction();	
			}
		});
	}
	
	private void okAction() 
	{	
		if(rounds < 0 || rounds > 6) 
		{
			JOptionPane.showMessageDialog(null, "Invalid parameters, please try again");
			numberText.setText("");
		}
		else 
		{
			setVisible(false);
		}
		rounds = Integer.parseInt(numberText.getText());	
	}
	
	public int getRounds() 
	{
		return rounds;
	}
}
