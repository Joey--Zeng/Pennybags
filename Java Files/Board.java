import java.util.ArrayList;
import cs1.Keyboard;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.*;

public class Board extends JFrame{
    private JFrame frame; // top level frame
    private static JPanel panel; // panel for buttons and misc
    private static JPanel buttonPanel; // panel for buttons and misc
    private JPanel bgPanel; // panel for monopoly board
    public static JPanel roll; // roll button
    private static DefaultListModel listModel; // list
    private JList list; // list holder
    public static JTextArea action;
    public static ArrayList<JButton> buttons;
    private static ArrayList<Tile> board;
    private static ArrayList<Player> players;
    public static ImageIcon img;
    private static int i; 
 

    public Board(){
	players = new ArrayList<Player>();
	board = new ArrayList<Tile>();
	board.add(new Tile("Go", 0, 0));
	board.add(new Tile( "Mediterranean Avenue", 60, 50, 2, 10, 30, 90, 160, 250, 1));
	board.add(new Tile("Community Chest", 0, 2));
	board.add(new Tile( "Baltic Avenue", 60, 50, 4,20,60,180,320,450,3));
	board.add(new Tile("Income Tax", 0 , 4));
	board.add(new Tile("Reading RR", 200, 5));
	board.add(new Tile( "Oriental Avenue", 100,50,6,30,90,270,400,550,6));
	board.add(new Tile("Chance", 0, 7));    
	board.add(new Tile( "Vermont Avenue", 100, 50,6,30,60,270,400,550,8));
	board.add(new Tile( "Conneticut Avenue", 120,50,8,40,100,300,450,600,9));
	board.add(new Tile("Jail", 0, 10));
	board.add(new Tile( "St. Charles Place", 140,100,10,50,150,450,625,750,11));
	board.add(new Tile("Electric Company", 150, 12));
	board.add(new Tile( "States Avenue", 140,100,10,50,150,450,625,750,13));
	board.add(new Tile( "Virginia Avenue", 160,100,12,60,180,500,700,900,14));   
	board.add(new Tile("Pennsylvania RR", 200, 15));
	board.add(new Tile( "St. James Place", 180,100,14,70,200,550,750,950,16));
	board.add(new Tile("Community Chest", 0, 17));
	board.add(new Tile( "Tennessee Avenue", 180,100,14,70,200,550,750,950,18));
	board.add(new Tile( "New York Avenue", 200,100,16,80,220,600,800,1000,19));
	board.add(new Tile("Free Parking", 0, 20));
	board.add(new Tile( "Kentucky Avenue", 220,150,18,90,250,700,875,1050,21));	
	board.add(new Tile("Chance", 0, 22));
	board.add(new Tile( "Indiana Avenue", 220,150,18,90,250,700,875,1050,23));
	board.add(new Tile( "Illinois Avenue", 240,150,20,100,300,750,925,1100,24));
	board.add(new Tile("B&O RR", 200, 25));
	board.add(new Tile( "Atlantic Avenue", 260,150,22,110,330,800,975,1150,26));
	board.add(new Tile( "Ventnor Avenue", 260,150,22,110,330,800,975,1150,27));
	board.add(new Tile("Water Works", 150, 28));
	board.add(new Tile( "Marvin Avenue", 280,150,24,120,360,850,1025,1200,29));	
	board.add(new Tile("GoToJ", 0, 30));
	board.add(new Tile( "Pacific Avenue", 300,200,26,130,390,900,1100,1275,31));
	board.add(new Tile( "North Carolina Avenue", 300,200,26,130,390,900,1100,1275,32));	
	board.add(new Tile("Community Chest", 0, 33));
	board.add(new Tile( "Pennsylvania Avenue", 320,200,28,150,450,1000,1200,1400,34));	
	board.add(new Tile("Short Line", 200, 35));
	board.add(new Tile("Chance", 0, 36));
	board.add(new Tile( "Park Place", 350,200,35,175,500,1100,1300,1500,37));    
	board.add(new Tile("Luxury Tax", 0, 38));
	board.add(new Tile( "BoardWalk", 400,200,50,200,600,1400,1700,2000,39));
	display();
    }
		  
    private static int dice1;
    private static int dice2;
    private static int doubCount;


    public static int roll () {
	action.append("\nRolling");
	dice1 = (int) (Math.random() * 6) + 1;
	dice2 = (int) (Math.random() * 6) + 1;
	action.append("\nRolled " + dice1 + " and " + dice2);
	return dice1 + dice2;
    }
	
    public static boolean doubs(){     // 1/6 probability that roll is a double.
    	return dice1 == dice2; 
    }
    
    public static Tile getTile(int i){
    	return board.get(i);
    }
    
    public static String propPrint(ArrayList<Integer> a){
    	String retStr = "";
    	for (int i = 0; i < a.size(); i++){
    		retStr += a.get(i) + ") " + getTile(a.get(i)).getName() + "  ";
    	}
    	return retStr + "(imput the number)";
    }

    public static void removeP(Player p){
	for(int i: p.getMortgage()){
	    Board.getTile(i).renew();
	}
	players.remove(players.indexOf(p));
    }

    public static boolean emergencyMort(Player p){
        boolean retBoo = true;
        while (p.getMoney() < 0){

	    if (p.isBankrupt()){
                retBoo = false;
				JOptionPane.showMessageDialog(roll, "You have lost the game.");
				System.exit(0);
                break;
	    }
		
		String ix = JOptionPane.showInputDialog(roll, "Mortgage/de-house which property?" + propPrint(p.getPropertyOwned()));
	    
		if (ix == null){
			retBoo = false;
			JOptionPane.showMessageDialog(roll, "You have lost the game.");
			System.exit(0);
            break;
		}
		
		int i = Integer.parseInt( JOptionPane.showInputDialog(roll, "Mortgage/de-house which property?" + propPrint(p.getPropertyOwned())) );
		
	    if (p.getPropertyOwned().indexOf(i) > -1){
		if (getTile(i).getAddOn() >4){	 
		    action.append("Got " + p.sellHouse(getTile(i), 5));
		    JOptionPane.showMessageDialog(roll, "Got " + p.sellHouse(getTile(i), 5));
		}
		else if (Board.getTile(i).getAddOn() > 1){
		    JOptionPane.showMessageDialog(roll, "How many houses to sell?");
		    int h = Integer.parseInt( JOptionPane.showInputDialog("How many houses to sell?"));
		    JOptionPane.showMessageDialog(roll, "Got " + p.sellHouse(getTile(i), h));
		}
		else{
		    p.mort(getTile(i), p.getPropertyOwned().indexOf(i));
		    JOptionPane.showMessageDialog(roll, "Mortgaged " + getTile(i).getName() +
				       " for " + getTile(i).getCost()/2);
			action.append("Mortgaged " + getTile(i).getName() +
				       " for " + getTile(i).getCost()/2);
		}
	    }
	    else
		JOptionPane.showMessageDialog(roll, "You don't own that property");
        }
        return retBoo;
    }

	private void display(){ // creates GUI
		//create initial top-level frame
		frame = new JFrame("Monopoly by Pennybags");
		frame.setLayout(new BorderLayout(25,25));
		
		//create button panel
		panel = new JPanel();
		//panel.setPreferredSize(new Dimension(200,600));
		panel.setLayout(new GridLayout(5,0,0,15)); // first number is number of elements(buttons) allowed
		
		//MENU CREATION
		//========================================================================================================
				//create menu
				JMenuBar menu = new JMenuBar();
				
				//create option in menu
				JMenu dropDown = new JMenu("Menu");
				dropDown.setMnemonic(KeyEvent.VK_M);
				dropDown.setToolTipText("ALT + M");
				
				JMenuItem exit = new JMenuItem("Exit"); // Exit button
				exit.setMnemonic(KeyEvent.VK_E);
				exit.setToolTipText("Close this program. (ALT + E)");
				
				JMenuItem credit = new JMenuItem("Credits"); // Credits
				
				//add functionality to Exit
				exit.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent event) {
						int result = JOptionPane.showConfirmDialog(roll, "Are you sure you want to quit?", "Exit", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) { 
							System.exit(0);
						}
				  }
				 });
				 
				//add functionality to credit
				credit.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent event) {
					   JOptionPane.showMessageDialog(roll, "<html><center><b>Hyunwoo Jeung, Johnathan Yan, Joey Zeng\nPeriod 9", "Credits", JOptionPane.INFORMATION_MESSAGE);
				  }
				 });
				
				//add options
				dropDown.add(credit);
				dropDown.add(exit);
				
				//add dropdown
				menu.add(dropDown);
				
				frame.setJMenuBar(menu);// frame is the top level container.
				
		//========================================================================================================
		
		//CREATES MONOPOLY BOARD(bgPanel)
		//========================================================================================================
				//create background panel
				bgPanel = new JPanel();
				bgPanel.setLayout(new BorderLayout(0,0));
				
				//create tiles on the top
				JPanel north = new JPanel();
				JButton b20 = new JButton("<html><center>20<br><b>Free<br>Parking</html>");
				JButton b21 = new JButton("<html><center>21<br><b>Kentucky<br>Avenue<br>$220</html>");
				b21.setForeground(Color.red);
				JButton b22 = new JButton("<html><center>22<br><b>Chance</html>");
				b22.setToolTipText("Draw a card!");
				JButton b23 = new JButton("<html><center>23<br><b>Indiana<br>Avenue<br>$220</html>");
				b23.setForeground(Color.red);
				JButton b24 = new JButton("<html><center>24<br><b>Illinois<br>Avenue<br>$240</html>");
				b24.setForeground(Color.red);
				JButton b25 = new JButton("<html><center>25<br><b>B&O<br>RR<br>$200</html>");
				JButton b26 = new JButton("<html><center>26<br><b>Atlantic<br>Avenue<br>$260</html>");
				b26.setForeground(Color.yellow);
				JButton b27 = new JButton("<html><center>27<br><b>Ventnor<br>Avenue<br>$260</html>");
				b27.setForeground(Color.yellow);
				JButton b28 = new JButton("<html><center>28<br><b>Water<br>Works<br>$150</html>");
				JButton b29 = new JButton("<html><center>29<br><b>Marvin<br>Gardens<br>$280</html>");
				b29.setForeground(Color.yellow);
				JButton b30 = new JButton("<html><center>30<br><b>Go To<br>Jail</html>");
				b30.setToolTipText("How unfortunate. GO TO JAIL!");
				b20.setPreferredSize(new Dimension(85,110));
				b21.setPreferredSize(new Dimension(85,110));
				b22.setPreferredSize(new Dimension(85,110));
				b23.setPreferredSize(new Dimension(85,110));
				b24.setPreferredSize(new Dimension(85,110));
				b25.setPreferredSize(new Dimension(85,110));
				b26.setPreferredSize(new Dimension(85,110));
				b27.setPreferredSize(new Dimension(85,110));
				b28.setPreferredSize(new Dimension(85,110));
				b29.setPreferredSize(new Dimension(85,110));
				b30.setPreferredSize(new Dimension(85,110));
				north.add(b20);
				north.add(b21);
				north.add(b22);
				north.add(b23);
				north.add(b24);
				north.add(b25);
				north.add(b26);
				north.add(b27);
				north.add(b28);
				north.add(b29);
				north.add(b30);
				
				//create tiles on the bottom
				JPanel south = new JPanel();
				JButton b0 = new JButton("<html><center>0<br><b>GO</b></center></html>");
				JButton b1 = new JButton("<html><center>1<br />Medi.<br />Avenue<br>$60</center></html>");
				b1.setForeground(Color.blue);
				b1.setHorizontalTextPosition(SwingConstants.CENTER);
				JButton b2 = new JButton("<html><center>2<br><b>Commun.<br>Chest</html>");
				b2.setToolTipText("Draw a card!");
				JButton b3 = new JButton("<html><center>3<br><b>Baltic<br>Avenue<br>$60</html>");
				b3.setForeground(Color.blue);
				JButton b4 = new JButton("<html><center>4<br><b>Income<br>Tax</html>");
				b4.setToolTipText("Pay $200");
				JButton b5 = new JButton("<html><center>5<br><b>Reading<br>R.R.<br>$200</html>");
				JButton b6 = new JButton("<html><center>6<br><b>Oriental<br>Avenue<br>$100</html>");
				b6.setForeground(Color.cyan);
				JButton b7 = new JButton("<html><center>7<br><b>Chance</html>");
				b7.setToolTipText("Draw a card!");
				JButton b8 = new JButton("<html><center>8<br><b>Vermont<br>Avenue<br>$100</html>");
				b8.setForeground(Color.cyan);
				JButton b9 = new JButton("<html><center>9<br><b>Connnect.<br>Avenue<br>$120</html>");
				b9.setForeground(Color.cyan);
				JButton b10 = new JButton("<html><center>10<br><b>Jail<br>or<br>Visiting</html>");
				b0.setPreferredSize(new Dimension(85,110));
				b1.setPreferredSize(new Dimension(85,110));
				b2.setPreferredSize(new Dimension(85,110));
				b3.setPreferredSize(new Dimension(85,110));
				b4.setPreferredSize(new Dimension(85,110));
				b5.setPreferredSize(new Dimension(85,110));
				b6.setPreferredSize(new Dimension(85,110));
				b7.setPreferredSize(new Dimension(85,110));
				b8.setPreferredSize(new Dimension(85,110));
				b9.setPreferredSize(new Dimension(85,110));
				b10.setPreferredSize(new Dimension(85,110));
				south.add(b10);
				south.add(b9);
				south.add(b8);
				south.add(b7);
				south.add(b6);
				south.add(b5);
				south.add(b4);
				south.add(b3);
				south.add(b2);
				south.add(b1);
				south.add(b0);
				
				//create tiles on the right
				JPanel east = new JPanel();
				east.setLayout(new GridLayout(9,0));
				JButton b31 = new JButton("<html><center>31<br><b>Pacific<br>Avenue<br>$300</html>");
				b31.setForeground(Color.green);
				JButton b32 = new JButton("<html><center>32<br><b>N.C.<br>Avenue<br>$300</html>");
				b32.setForeground(Color.green);
				JButton b33 = new JButton("<html><center>33<br><b>Commun.<br>Chest</html>");
				b33.setToolTipText("Draw a card!");
				JButton b34 = new JButton("<html><center>34<br><b>Penn.<br>Avenue<br>$320</html>");
				b34.setForeground(Color.green);
				JButton b35 = new JButton("<html><center>35<br><b>Short<br>Line<br>$200</html>");
				JButton b36 = new JButton("<html><center>36<br><b>Chance</html>");
				b36.setToolTipText("Draw a card!");
				JButton b37 = new JButton("<html><center>37<br><b>Park<br>Place<br>$350</html>");
				b37.setForeground(Color.blue);
				JButton b38 = new JButton("<html><center>38<br><b>Luxury<br>Tax</html>");
				b38.setToolTipText("Pay $75.");
				JButton b39 = new JButton("<html><center>39<br><b>Board<br>walk<br>$400</html>");
				b39.setForeground(Color.blue);
				b31.setPreferredSize(new Dimension(90,70));
				b32.setPreferredSize(new Dimension(90,70));
				b33.setPreferredSize(new Dimension(90,70));
				b34.setPreferredSize(new Dimension(90,70));
				b35.setPreferredSize(new Dimension(90,70));
				b36.setPreferredSize(new Dimension(90,70));
				b37.setPreferredSize(new Dimension(90,70));
				b38.setPreferredSize(new Dimension(90,70));
				b39.setPreferredSize(new Dimension(90,70));
				east.add(b31);
				east.add(b32);
				east.add(b33);
				east.add(b34);
				east.add(b35);
				east.add(b36);
				east.add(b37);
				east.add(b38);
				east.add(b39);
				
				//create tiles on the left
				JPanel west = new JPanel();
				west.setLayout(new GridLayout(9,0));
				JButton b11 = new JButton("<html><center>11<br><b>St. Charles<br>Place<br>$140</html>");
				b11.setForeground(Color.magenta);
				JButton b12 = new JButton("<html><center>12<br><b>Elec.<br>Company<br>$150</html>");
				JButton b13 = new JButton("<html><center>13<br><b>States<br>Avenue<br>$140</html>");
				b13.setForeground(Color.magenta);
				JButton b14 = new JButton("<html><center>14<br><b>Virginia<br>Avenue<br>$160</html>");
				b14.setForeground(Color.blue);
				JButton b15 = new JButton("<html><center>15<br><b>Penn.<br>R.R.<br>$200</html>");
				JButton b16 = new JButton("<html><center>16<br><b>St. James<br>Place<br>$180</html>");
				b16.setForeground(Color.orange);
				JButton b17 = new JButton("<html><center>17<br><b>Commun.<br>Chest</html>");
				b17.setToolTipText("Draw a card!");
				JButton b18 = new JButton("<html><center>18<br><b>Tennessee<br>Avenue<br>$180</html>");
				b18.setForeground(Color.orange);
				JButton b19 = new JButton("<html><center>19<br><b>New York<br>Avenue<br>$200</html>");
				b19.setForeground(Color.orange);
				b11.setPreferredSize(new Dimension(90,70));
				b12.setPreferredSize(new Dimension(90,70));
				b13.setPreferredSize(new Dimension(90,70));
				b14.setPreferredSize(new Dimension(90,70));
				b15.setPreferredSize(new Dimension(90,70));
				b16.setPreferredSize(new Dimension(90,70));
				b17.setPreferredSize(new Dimension(90,70));
				b18.setPreferredSize(new Dimension(90,70));
				b19.setPreferredSize(new Dimension(90,70));
				west.add(b19);
				west.add(b18);
				west.add(b17);
				west.add(b16);
				west.add(b15);
				west.add(b14);
				west.add(b13);
				west.add(b12);
				west.add(b11);
				
				//creates center monopoly image
				JPanel bg = new JPanel();
				JLabel background=new JLabel(new ImageIcon("monopoly.jpg"));
                bg.add(background);
				
				//sets background of bgPanel to monopoly board
				bgPanel.setLayout(new BorderLayout()); // allows for division of content
				bgPanel.add(north, BorderLayout.NORTH);
				bgPanel.add(south, BorderLayout.SOUTH);
				bgPanel.add(east, BorderLayout.EAST);
				bgPanel.add(west, BorderLayout.WEST);
				bgPanel.add(bg, BorderLayout.CENTER);
				
				buttons = new ArrayList<JButton>();
				buttons.add(b0);
				buttons.add(b1);
				buttons.add(b2);
				buttons.add(b3);
				buttons.add(b4);
				buttons.add(b5);
				buttons.add(b6);
				buttons.add(b7);
				buttons.add(b8);
				buttons.add(b9);
				buttons.add(b10);
				buttons.add(b11);
				buttons.add(b12);
				buttons.add(b13);
				buttons.add(b14);
				buttons.add(b15);
				buttons.add(b16);
				buttons.add(b17);
				buttons.add(b18);
				buttons.add(b19);
				buttons.add(b20);
				buttons.add(b21);
				buttons.add(b22);
				buttons.add(b23);
				buttons.add(b24);
				buttons.add(b25);
				buttons.add(b26);
				buttons.add(b27);
				buttons.add(b28);
				buttons.add(b29);
				buttons.add(b30);
				buttons.add(b31);
				buttons.add(b32);
				buttons.add(b33);
				buttons.add(b34);
				buttons.add(b35);
				buttons.add(b36);
				buttons.add(b37);
				buttons.add(b38);
				buttons.add(b39);
				

		//========================================================================================================
	
		//CREATES BUTTONS
		//========================================================================================================
				//create panel
				roll = new JPanel();
				buttonPanel = new JPanel();
				buttonPanel.setLayout(new GridLayout(2,2));
				
				//create roll button
				/* testing function
				JButton test = new JButton("test!");
				test.setPreferredSize(new Dimension(100,100));
				test.setEnabled(true);
				test.addActionListener(new ActionListener() {
				   public void actionPerformed(ActionEvent event) {
					   action.append("test\n");
				  }
				 });
				 */
				final JButton endTurn = new JButton("End Turn");
				
				//create space for aesthetic purpose
				final JButton _roll = new JButton("Roll the dice!");
				//space.setPreferredSize(new Dimension(100,100));
				_roll.setVisible(true);
				_roll.setEnabled(false);
				_roll.setToolTipText("Rolls the dice.");
				_roll.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
					    rooll();
						_roll.setEnabled(false);
						endTurn.setEnabled(true);
					}
				});
	
				endTurn.setVisible(true);
				endTurn.setEnabled(false);
				endTurn.setToolTipText("Ends your turn.");
				endTurn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
					    endTurnn();
						_roll.setEnabled(true);
						endTurn.setEnabled(false);
					}
				});

				final JButton deMort = new JButton("Demortgage");
				deMort.setVisible(true);
				deMort.setEnabled(false);
				deMort.setToolTipText("Demortgages a property.");
				deMort.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
					    deMort();
					}
				});
				
				final JButton buyProp = new JButton("Buy House");
				buyProp.setVisible(true);
				buyProp.setEnabled(false);
				buyProp.setToolTipText("Builds on your property.");
				buyProp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
					    buyHouse();
					}
				    });
				
				//create roll button
				final JButton newGame = new JButton("Start Game!");
				//newGame.setPreferredSize(new Dimension(100,100));
				newGame.setToolTipText("Starts new game.");
				newGame.setEnabled(true);
				newGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						play();
						newGame.setEnabled(false);
						_roll.setEnabled(true);
						deMort.setEnabled(true);
						buyProp.setEnabled(true);
					}
				});
				
				//adds roll button
				panel.add(newGame);   
				buttonPanel.add(_roll);
				buttonPanel.add(endTurn);
				buttonPanel.add(buyProp);
				buttonPanel.add(deMort);
				panel.add(buttonPanel);
				panel.add(roll);
				//panel.add(test);	
		//========================================================================================================
		
		//CREATES TEXTFIELD & ACTION LIST
		//========================================================================================================
				//create player list
				Font font = new Font("Tahoma", Font.PLAIN, 12);
				listModel = new DefaultListModel(); // actual list
				listModel.setSize(7);
				
				//adds to list
				listModel.set(0, "Player List");
				listModel.set(1, "=================================");
				/* test
				listModel.addElement("Player 1 : $1500 dollars");
				*/
				
				//adds list to listHolder
				list = new JList(listModel); // holds list
				list.setVisibleRowCount(5);
				list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				list.setFont(font);
				list.setForeground(Color.BLACK);
				list.setToolTipText("Player list");
				list.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.green));
				list.setEnabled(true);
				
				//create action list
				action = new JTextArea("Action List\n==============\n");
				action.setFont(font);
				action.setForeground(Color.BLACK);
				action.setRows(5);
				action.setToolTipText("Recent actions.");
				action.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.green));
				action.setLineWrap(true);
				action.setEditable(false);
				action.setEnabled(true);
				action.setCaretPosition(action.getDocument().getLength());
				JScrollPane actionScroll = new JScrollPane(action);
				actionScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				
				//adds buttons
				panel.add(list);	
				panel.add(actionScroll);
		//========================================================================================================
		
		//ADD PANELS TO FRAME
		//========================================================================================================
				frame.add(panel, BorderLayout.WEST);
				frame.add(bgPanel, BorderLayout.CENTER);
		//========================================================================================================
		
		//SET ATTRIBUTES
		//========================================================================================================
				frame.setTitle("Monopoly by Pennybags");
				//frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame.setResizable(true);
				//frame.setSize(1000,1000);
				frame.setVisible(true);
				frame.pack();
		//========================================================================================================
	}





    public static void rooll(){
	listModel.set(2, players.get(0).getName() + " : $" + players.get(0).getMoney());
	listModel.set(3, "Property Owned: " + players.get(0).getPropertyOwned());
	listModel.set(4, players.get(1).getName() + " : $" + players.get(1).getMoney());
	listModel.set(5, "Property Owned: " + players.get(1).getPropertyOwned());
	Player ref = players.get(i);
	
	if (ref.getInJail()){
	    action.append("\nJailed");
	    JOptionPane.showMessageDialog(roll, ref.getName() + " is jailed.");
	    ref.jailTurn();
	    if(ref.getTurnsInJail() >= 2){
		ref.jailBreak();
	    }
	    else if (ref.getCard()){ 
		ref.loseCard();
		ref.jailBreak();
		action.append("Used card to get out of jail");
	    }
	    else{			
		roll();
		if(doubs()){
		    ref.jailBreak();
		    action.append("Snake Eyes! You are out of jail. You may move next turn.");
		    JOptionPane.showMessageDialog(roll, "Snake Eyes! You are out of jail. You may move next turn.");
			}
		else if (ref.getMoney() >= 50){
		    int ans = JOptionPane.showConfirmDialog(roll, "Bail for 50?", "Bail", JOptionPane.YES_NO_OPTION);
		    if (ans == JOptionPane.YES_OPTION){
			ref.loseMoney(50);
			ref.jailBreak();
		    }
		}
	    }
	}// end inJail block
	else{ // not in jail / free to move...
	    doubCount = 0;
	    for (int dum = 1; dum == 1; dum ++){
		buttons.get(ref.getPos()).setIcon(null);
		buttons.get(ref.getPos()).setHorizontalTextPosition(SwingConstants.CENTER);
		ref.posAdd(roll());
		
		if (i == 0){
		    img = new ImageIcon("p1.jpg");
		}
		
		else{
		    img = new ImageIcon("p2.gif");
		}
		
		buttons.get(ref.getPos()).setIcon(img);
		buttons.get(ref.getPos()).setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		int abc = ref.propertyInteract(board.get(ref.getPos()), players, dice1, dice2);
		ref.propertyInteract(board.get(abc), players, dice1, dice2);	
		buttons.get(ref.getPos()).setIcon(null);
		buttons.get(ref.getPos()).setHorizontalTextPosition(SwingConstants.CENTER);
		buttons.get(ref.getPos()).setIcon(img);
		buttons.get(ref.getPos()).setHorizontalTextPosition(SwingConstants.CENTER);
		listModel.set(2, players.get(0).getName() + " : $" + players.get(0).getMoney());
		listModel.set(3, "Property Owned: " + players.get(0).getPropertyOwned());
		listModel.set(4, players.get(1).getName() + " : $" + players.get(1).getMoney());
		listModel.set(5, "Property Owned: " + players.get(1).getPropertyOwned());

		if (dice1 == dice2){
		    if (doubCount >= 2){
			ref.jailed();
			break;
		    }
		    else{
			doubCount ++;
			dum--;
			JOptionPane.showMessageDialog(roll, "Doubles! Roll again.");
		    }
		}
		
		if (ref.getMoney() < 0){
		    if (!emergencyMort(ref)){
			removeP(ref); // REPLACE THIS WITH METHOD TO FREE ALL PROPS...
		    }
		}
		if (players.size() <= 1){
		    JOptionPane.showMessageDialog(roll, "Game is over. The remaining player has won.");
		    break;
		}					      
	    }// end roll and interact (loops if double)
	}
	

	
    }// end rooll()
    
    public static void buyHouse(){
	Player ref = players.get(i);
	String options = "Color? ";
	if (ref.checkBrown()){
	    options += " 1)Brown ";
	}
	if (ref.checkLBlue()){
	    options += " 2)Light Blue ";
	}
	if (ref.checkPink()){
	    options += " 3)Pink ";
	}
	if (ref.checkOrange()){
	    options += " 4)Orange ";
	}
	if (ref.checkRed()){
	    options += " 5)Red ";
	}
	if (ref.checkYellow()){
	    options += " 6)Yellow ";
	}
	if (ref.checkGreen()){
	    options += " 7)Green ";
	}
	if (ref.checkBlue()){
	    options += " 8)Blue ";
		}
	if (options.equals("Color? ")){
	    options = "You have no full sets";
		}		
	int color = Integer.parseInt( JOptionPane.showInputDialog(roll, options) ); 
	int prop = 0;
	if (color == 1) {
	    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 1)Mediterranean Ave  3)Baltic Ave") );
	}
	else if (color == 2) {
	    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 6)Oriental Ave  7)Vermont Ave  8)Connecticut Ave"));
	}
	else if (color == 3) {
	    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 11)St.Charles Place  13)States Ave  14)Virginia Ave"));
	}
	else if (color == 4) {
	    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 16)St.James Place  18)Tennessee Ave  19)NY Ave"));
	}
	else if (color == 5) {
	    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 21)Kentucky Ace  23)Indiana Ave  24)Illinois Ave"));
	}
	else if (color == 6) {
	    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 26)Atlantic Ave  27)Vermont Ave  29)Marvin Gardens"));
	}
	else if (color == 7) {
	    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 31)Pacific Ave  32)North Carolina Ave  34)Pennsylvania Ave"));
	}
	else if (color == 8) {
	    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 37)Park Place  39)Broadwalk"));
	}	
	int sub = board.get(prop).getAddOn();
	if (sub == 5){
	    JOptionPane.showMessageDialog(null, "Property is maxed already");
	}
	else{
	    String prompt = "1)1 House  2)2 Houses 3)3 Houses 4)4 Houses 5)A Hotel  ";
	    prompt = prompt.substring(sub*11);
	    
	    int buildings = ref.getMoney() / board.get(prop).getHouseCost();
	    if (buildings == 0){
		prompt = "You don't have the money to build here";
		JOptionPane.showMessageDialog(null, prompt);
	    }
	    else{
		if(buildings >= 5){}
		else{
		    prompt = prompt.substring(0, prompt.length()-11*(5-buildings-sub));
		}
		int _how = Integer.parseInt(JOptionPane.showInputDialog(null, prompt));
		ref.buyHouse(board.get(prop), _how - sub);
	    }
	}

    }// end buyHouse()		


    public static void endTurnn(){
	Player ref = players.get(i);
	if (ref.getMoney() < 0){
	    if (!emergencyMort(ref)){
		removeP(ref); // REPLACE THIS WITH METHOD TO FREE ALL PROPS...
	    }
	}
	if (i == players.size()-1){
	    i = 0;
	}
	else{ 
	    i++;
	}	
	action.append("\n\n" + players.get(i).getName()+"'s Turn");	
    }

    public static void deMort(){
	Player ref = players.get(i);
	if (!ref.getMortgage().isEmpty()){
	    int num = Integer.parseInt(JOptionPane.showInputDialog(roll, propPrint(ref.getMortgage())));
	    ref.deMort(board.get(num), ref.getMortgage().indexOf(num));
	}
	else
	    JOptionPane.showMessageDialog(null, "You don't have mortgaged properties");

    }
		

    public static void updateL(){}
	


    public static void play () { 
	
	String one = JOptionPane.showInputDialog(roll, "Player 1, what's your name?", "Player 1");
	String two = JOptionPane.showInputDialog(roll, "Player 2, what's your name?", "Player 2");

	players.add(new Player(one));
	players.add(new Player(two));
	int i = 0;
	listModel.set(2, players.get(0).getName() + " : $" + players.get(0).getMoney());
	listModel.set(3, "Property Owned: " + players.get(0).getPropertyOwned());
	listModel.set(4, players.get(1).getName() + " : $" + players.get(1).getMoney());
	listModel.set(5, "Property Owned: " + players.get(1).getPropertyOwned());
	action.append("\n\n" + players.get(i).getName()+"'s Turn");
    }

	/*
	while ( players.size() > 1 ) {
	    for(int i = 0; i < players.size(); i++){
		listModel.set(2, one + " : $" + players.get(0).getMoney());
		listModel.set(3, "Property Owned: " + players.get(0).getPropertyOwned());
		listModel.set(4, two + " : $" + players.get(1).getMoney());
		listModel.set(5, "Property Owned: " + players.get(1).getPropertyOwned());
		action.append("\n\n" + players.get(i).getName()+"'s Turn");
		Player ref = players.get(i);
		
	        if (ref.getInJail()){
		    action.append("\nJailed");
			JOptionPane.showMessageDialog(roll, ref.getName() + " is jailed.");
		    ref.jailTurn();
		    if(ref.getTurnsInJail() >= 2){
			ref.jailBreak();
		    }
		    else if (ref.getCard()){ 
			ref.loseCard();
			ref.jailBreak();
			action.append("Used card to get out of jail");
		    }
		    else{			
			roll();
			if(doubs()){
			    ref.jailBreak();
			    action.append("Snake Eyes! You are out of jail. You may move next turn.");
				JOptionPane.showMessageDialog(roll, "Snake Eyes! You are out of jail. You may move next turn.");
			}
			else if (ref.getMoney() >= 50){
			    int ans = JOptionPane.showConfirmDialog(roll, "Bail for 50?", "Bail", JOptionPane.YES_NO_OPTION);
			    if (ans == JOptionPane.YES_OPTION){
					ref.loseMoney(50);
					ref.jailBreak();
			    }
			}
		    }
		}// end inJail block
		else{ // not in jail / free to move...
		    doubCount = 0;
		    for (int dum = 1; dum == 1; dum ++){
				buttons.get(ref.getPos()).setIcon(null);
				buttons.get(ref.getPos()).setHorizontalTextPosition(SwingConstants.CENTER);
				ref.posAdd(roll());
				
				if (i == 0){
					img = new ImageIcon("p1.jpg");
				}

				else{
					img = new ImageIcon("p2.gif");
				}
					
				buttons.get(ref.getPos()).setIcon(img);
				buttons.get(ref.getPos()).setHorizontalTextPosition(SwingConstants.CENTER);
				
				if (dice1 == dice2){
					if (doubCount >= 2){
					ref.jailed();
					break;
					}
					else{
					doubCount ++;
					dum--;
					}
			}
			int abc = ref.propertyInteract(board.get(ref.getPos()), players, dice1, dice2);
			ref.propertyInteract(board.get(abc), players, dice1, dice2);	
			if (ref.getMoney() < 0){
			    if (!emergencyMort(ref)){
			        removeP(ref); // REPLACE THIS WITH METHOD TO FREE ALL PROPS...
			    }
			}
			if (players.size() <= 1){
			    JOptionPane.showMessageDialog(roll, "Game is over. The remaining player has won.");
			    break;
			}					      
		    }// end roll and interact (loops if double)
		    String yesno = "y";
		    while (yesno.equals("y")){
			if (yesno.equals("y")){
			    String options = "Color? ";
			    if (ref.checkBrown()){
				options += " 1)Brown ";
			    }
			    if (ref.checkLBlue()){
				options += " 2)Light Blue ";
			    }
			    if (ref.checkPink()){
				options += " 3)Pink ";
			    }
			    if (ref.checkOrange()){
				options += " 4)Orange ";
			    }
			    if (ref.checkRed()){
				options += " 5)Red ";
			    }
			    if (ref.checkYellow()){
				options += " 6)Yellow ";
			    }
			    if (ref.checkGreen()){
				options += " 7)Green ";
			    }
			    if (ref.checkBlue()){
			        options += " 8)Blue ";
			    }
			    if (options.equals("Color? ")){
			        yesno = "n";
			    }
			    else {
				int ans = JOptionPane.showConfirmDialog(roll, "Do you want to purchase houses?", "Purchase House?", JOptionPane.YES_NO_OPTION);
			    if (ans == JOptionPane.YES_OPTION){ 		
				JOptionPane.showMessageDialog(roll, options);  // STILL NEED TO GUI THIS.... Just make a text box
				int color = Keyboard.readInt();   // STILL NEED TO GUI THIS.... and the input is int called "color"
				int prop = 0;
				if (color == 1) {
				    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 1)Mediterranean Ave  3)Baltic Ave") );
				}
				else if (color == 2) {
				    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 6)Oriental Ave  7)Vermont Ave  8)Connecticut Ave"));
				}
				else if (color == 3) {
				    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 11)St.Charles Place  13)States Ave  14)Virginia Ave"));
				}
				else if (color == 4) {
				    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 16)St.James Place  18)Tennessee Ave  19)NY Ave"));
				}
				else if (color == 5) {
				    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 21)Kentucky Ace  23)Indiana Ave  24)Illinois Ave"));
				}
				else if (color == 6) {
				    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 26)Atlantic Ave  27)Vermont Ave  29)Marvin Gardens"));
				}
				else if (color == 7) {
				    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 31)Pacific Ave  32)North Carolina Ave  34)Pennsylvania Ave"));
				}
				else if (color == 8) {
				    prop = Integer.parseInt( JOptionPane.showInputDialog(roll, "Which property? 37)Park Place  39)Broadwalk"));
				}	
				int sub = board.get(prop).getAddOn();
				if (sub == 5){
				    JOptionPane.showMessageDialog(null, "Property is maxed already");
				}
				else{
				    String prompt = "1)1 house  2)2 houses 3)3 houses 4)4 houses 5)Hotel   ";
				    prompt = prompt.substring(sub*11);

				    int buildings = ref.getMoney() / board.get(prop).getHouseCost();
				    if (buildings == 0){
					prompt = "You don't have the money to build here";
				    }
				    if(buildings >= 5){}
				    else{
					prompt = prompt.substring(0, prompt.length()-11*(5-buildings));
				    }
				    JOptionPane.showMessageDialog(null, prompt);
				    int how = Keyboard.readInt() - sub;
				    ref.buyHouse(board.get(prop), how);
				}
			    }		       			
			}// end if input = yes
		    }// ends while loop for houses
										  			
		}// end not-in-jail block 
	    }// end loop block for one person's turn
	}// end while loop block for one round of turns
	}
	*/
    



	    /*
	    //Player a's turn
	    System.out.println( "" + a.getName() + "'s turn" );
	    System.out.println("Your status:\n");
	    System.out.println(a);
	    a.setPos(role());
	    System.out.println( "\nYou're in" + board.get(a.getPos()));
	    System.out.println( "Do you want to buy" + ((board.get(a.getPos())).getName()) + "? ( y or n )");
		
	    String response = Keyboard.readString();
	    if ( response.equals("y") )
	    	a.buy(board.get(a.getPos()));

	    System.out.println( "Your status:\n" + a );

	    //Player b's turn
	    System.out.println( "" + b.getName() + "'s turn" );
	    System.out.println("Your status:\n");
	    System.out.println(b);
	    b.setPos(role());
	    System.out.println( "\nYou're in" + board.get(b.getPos()));
	    System.out.println( "Do you want to buy" + ((board.get(b.getPos())).getName()) + "? ( y or n )");
		
	    response = Keyboard.readString();
	    if ( response.equals("y") )
		b.buy(board.get(b.getPos()));

	    System.out.println("Your status:\n" + b );
	    */




	/*
    public static void main ( String[] args ) {
	new Board();
    }
	*/

}
