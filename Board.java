import java.util.ArrayList;
import cs1.Keyboard;

public class Board {

    private static ArrayList<Tile> board;
    private ArrayList<Player> players;

    private static Tile MediterraneanAve = new Tile( "Mediterrannean Avenue", 60 );
    private static Tile BalticAve = new Tile( "Baltic Avenue", 60 );
    private static Tile OrientralAve = new Tile( "Oriental Avenue", 100 );
    private static Tile VermontAve = new Tile( "Vermont Avenue", 100 );
    private static Tile ConneticutAve = new Tile( "Conneticut Avenue", 120 );
    private static Tile StCharlesPlace = new Tile( "St. Charles Place", 140 );
    private static Tile StatesAve = new Tile( "States Avenue", 140 );
    private static Tile VirginiaAve = new Tile( "Virginia Avenue", 160 );
    private static Tile StJamesPlace = new Tile( "St. James Place", 180 );
    private static Tile TennesseeAve = new Tile( "Tennessee Avenue", 180 );
    private static Tile NewYorkAve = new Tile( "New York Avenue", 200 );
    private static Tile KentuckyAve = new Tile( "Kentucky Avenue", 220 );
    private static Tile IndianaAve = new Tile( "Indiana Avenue", 220 );
    private static Tile IllinoisAve = new Tile( "Illinois Avenue", 240 );
    private static Tile AtlanticAve = new Tile( "Atlantic Avenue", 260 );
    private static Tile VentnorAve = new Tile( "Ventnor Avenue", 260 );
    private static Tile MarvinAve = new Tile( "Marvin Avenue", 280 );
    private static Tile PacificAve = new Tile( "Pacific Avenue", 300 );
    private static Tile NorthCarolinaAve = new Tile( "North Carolina Avenue", 300 );
    private static Tile PennsylvaniaAve = new Tile( "Pensylvania Avenue", 320 );
    private static Tile ParkPlace = new Tile( "Park Place", 350 );
    private static Tile BoardWalk = new Tile( "BoardWalk", 400 );

    private int dice1;
    private int dice2;
    private int doubCount;

    public static void initialize() {
	board.add(A);
	board.add(B);
	board.add(C);
	board.add(D);
	board.add(E);
    }

    public static int roll () {
	dice1 = (int) (Math.random() * 6) + 1;
	dice2 = (int) (Math.random() * 6) + 1;
	System.out.println("" + dice1 + " " + dice2);
	return dice1 + dice2;
    }
    public boolean doubs(){     // 1/6 probability that roll is a double.
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

    public void removeP(Player p){
	for(int i: p.getMortgage()){
	    board.getTile(i).renew();
	}
	players.remove(players.indexOf(p));
    }

    public boolean emergencyMort(Player p){
        retBoo = true;
        while (p.getMoney() < 0){

	    if (p.isBankrupt()){
                retBoo = false;
		System.out.println("You have lost the game.");
                break;
	    }
	    System.out.println("Mortgage/de-house which property?" +
			       propPrint(p.getPropertyOwned()));
	    int i = Keyboard.readInt();
	    //NEED GUI IN
	    
	    if (p.getPropertyOwned().indexOf(i) > -1){
		if (getTile(i).getAddOn() >4){	 
		    int cashh = getTile(i).sellHouses(5);
		    p.cashIn(cashh);
		    System.out.println("Got " + cashh);
		}
		else if (Board.getTile(i).getAddOn() > 1){
		    System.out.println("How many houses to sell?");
		    int h = Keyboard.readInt();		 
		    int cash = getTile(i).sellHouses(x);
		    p.cashIn(cash);
		    System.out.println("Got " + cash);
		}
		else{
		    mort(getTile(i), p.getProperyOwned().indexOf(i));
		    System.out.println("Mortgaged " + getTile(i).getName() +
				       "for " + getTile(i).getMortgage());
		}
	    }
	    else
		System.out.println("You don't own that property. Try again");
        }
        return retBoo;
    }








    public static void play () {
	initialize();

	System.out.println("Player 1, what's your name?");
	String one = Keyboard.readString();

	System.out.println("Player 2, what's your name?");
	String two = Keyboard.readString();

	Player a = new Player(one);
	Player b = new Player(two);
	players.add(a);
	players.add(b);

	while ( players.size() > 1 ) {
	    for(int i = 0; i < players.size() i++){
		Player ref = players.get(i);
	        if (ref.inJail()){
		    ref.jailTurn();
		    if(ref.turnsInJail >= 2){
			ref.jailBreak();
		    }
		    else if (ref.getCard()){ 
			ref.loseCard();
			ref.jailBreak();
			System.out.println("Used card to get out of jail");
		    }
		    else{			
			roll();
			if(doubs()){
			    ref.jailBreak();
			    System.out.println("Doubles! You are out of jail. You may move next turn")
			}
			else if (ref.getMoney() >= 50){
			    System.out.println("Bail for 50? (y/n)");
			    String ans = Keyboard.readString();
			    if (ans.equals("y")){
				ref.loseMoney(50);
				ref.jailBreak();
			    }
			}
		    }
		}// end inJail block
		else{ // not in jail / free to move...
		    doubCount = 0;
		    for (int dum = 1; dum == 1; dum ++){
			ref.posAdd(roll());
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
			if (players.size <= 1){
			    System.out.println("Game is over. The remaining player has won.");
			    break;
			}					      
		    }// end roll and interact (loops if double)
		    String yesno = "y";
		    while (yesno.equals("y")){
			System.out.println("Do you want to purchase houses(if you have a full set only)? (y/n)");
			yesno = Keyboard.readString();			
			System.out.println("Color? 1)Brown  2)Light Blue  3)Pink  4)Orange  5)Red  6)Yellow  7)Green  8)Blue");
			String color = Keyboard.readString();
			if (color.equals("1")) {
			    
			}
			else if (color.equals("2")) {}
			else if (color.equals("3")) {}
			else if (color.equals("4")) {}
			else if (color.equals("5")) {}
			else if (color.equals("6")) {}
			else if (color.equals("7")) {}
			else if (color.equals("8")) {}
		    }
			
			    
			
		}// end not-in-jail block (one player's entire turn)
	    }
	}
    }




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

	}



    }

    public static void main ( String[] args ) {
	play();
    }

}
