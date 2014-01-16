import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.*;

public class GUI extends JFrame{
	private JPanel panel;
	private JPanel bgPanel;
	Image img = new ImageIcon("monopoly.jpg").getImage();
	private JButton roll;

	public GUI() {
		display();
		pack();
	}
	
	private void display(){
		//create initial top-level panel
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);
		
		//MENU CREATION
		//========================================================================================================
				//create menu
				JMenuBar menu = new JMenuBar();
				
				//create option in menu
				JMenu dropDown = new JMenu("Menu");
				dropDown.setMnemonic(KeyEvent.VK_M);
				dropDown.setToolTipText("ALT + M");
				
				//create options inside dropDown
				JMenuItem start = new JMenuItem("Start"); // Start button
				start.setMnemonic(KeyEvent.VK_S);
				start.setToolTipText("Start a new game! (ALT + S)");
				
				JMenuItem exit = new JMenuItem("Exit"); // Exit button
				exit.setMnemonic(KeyEvent.VK_E);
				exit.setToolTipText("Close this program. (ALT + E)");
				
				JMenuItem credit = new JMenuItem("Credits"); // Credits
				
				//add functionality to Exit
				exit.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent event) {
						JOptionPane.showMessageDialog(panel, "Are you sure you want to quit?", "Exit", JOptionPane.QUESTION_MESSAGE);
					   System.exit(0);
				  }
				 });
				 
				//add functionality to credit
				credit.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent event) {
					   JOptionPane.showMessageDialog(panel, "Hyunwoo Jeung, Johnathan Yan, Joey Zeng\nPeriod 9", "Credits", JOptionPane.INFORMATION_MESSAGE);
				  }
				 });
				
				//add options
				dropDown.add(start);
				dropDown.add(credit);
				dropDown.add(exit);
				
				//add dropdown
				menu.add(dropDown);
				
				setJMenuBar(menu);
				
		//========================================================================================================
		
		//CREATES MONOPOLY BOARD
		//========================================================================================================
				//create background panel
				bgPanel = new JPanel();	
				
				//sets background of bgPanel to monopoly board
				bgPanel.setLayout(new BorderLayout());
					JLabel background=new JLabel(new ImageIcon("monopoly.jpg"));
					bgPanel.add(background);
				
				//add bgPanel inside of top-level  panel
				panel.add(bgPanel, BorderLayout.CENTER);
		//========================================================================================================
	
		//CREATES BUTTONS
		//========================================================================================================
				//create roll button
				roll = new JButton("Roll!");
				roll.setLocation(200,200);
				roll.setSize(roll.getPreferredSize());
				roll.setToolTipText("Roll the dice. Is only enabled during your turn.");
				roll.setEnabled(false);
				
				//adds roll button
				panel.add(roll, BorderLayout.SOUTH);	
		//========================================================================================================
		
		//set attributes
		setTitle("Monopoly by Pennybags");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000,1000);
		
	}
		
	
	public static void main(String[] args){
		GUI test = new GUI();
		test.setVisible(true);
		
	}
}
	
	
		