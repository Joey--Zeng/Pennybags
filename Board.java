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
				System.out.println("You don't have any full sets.");
			    }
			    else {
				System.out.println(options);  // MIGHT BE HARDER TO GUI THIS... 
				int color = Keyboard.readInt();
				int prop = 0;
				if (color == 1) {
				    System.out.println("Which property? 1)Mediterranean Ave  3)Baltic Ave");
				}
				else if (color == 2) {
				    System.out.println("Which property? 6)Oriental Ave  7)Vermont Ave  8)Connecticut Ave");
				}
				else if (color == 3) {
				    System.out.println("Which property? 11)St.Charles Place  13)States Ave  14)Virginia Ave");
				}
				else if (color == 4) {
				    System.out.println("Which property? 16)St.James Place  18)Tennessee Ave  19)NY Ave");
				}
				else if (color == 5) {
				    System.out.println("Which property? 21)Kentucky Ace  23)Indiana Ave  24)Illinois Ave");
				}
				else if (color == 6) {
				    System.out.println("Which property? 26)Atlantic Ave  27)Vermont Ave  29)Marvin Gardens");
				}
				else if (color == 7) {
				    System.out.println("Which property? 31)Pacific Ave  32)North Carolina Ave  34)Pennsylvania Ave");
				}
				else if (color == 8) {
				    System.out.println("Which property? 37)Park Place  39)Broadwalk");
				}	
				prop = Keyboard.readInt();
				int sub = board.getTile(prop).getAddOn();
				if (sub == 5){
				    System.out.println("Property is maxed already");
				}
				else{
				    String prompt = "1)1 house  2)2 houses 3)3 houses 4)4 houses 5)Hotel   ";
				    prompt = prompt.substring(sub*11);

				    int buildings = ref.getMoney() / board.getTile(prop).getHouseCost();
				    if (buildings = 0){
					System.out.println("You don't have the money to build here");
				    }
				    if(buildings >= 5){}
				    else{
					prompt = prompt.substring(0, prompt.length()-11*(5-building));
				    }
				    System.out.println(prompt);
				    int how = Keyboard.readInt() - sub;
				    ref.loseMoney(buyHouse(board.getTile(prop), how));
				}
			    }		       			
			}// end if input = yes
		    }// ends while loop for houses
										  			
		}// end not-in-jail block 
	    }// end loop block for one person's turn
	}// end while loop block for one round of turns
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
