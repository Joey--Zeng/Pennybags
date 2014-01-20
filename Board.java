import java.util.ArrayList;
import cs1.Keyboard;

public class Board {

    private static ArrayList<Tile> board;
    private static ArrayList<Player> players;
    /*
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
    */
    public Board(){
	players = new ArrayList<Player>();
	board = new ArrayList<Tile>();
	board.add(new Tile("Go", 0, 0));
      	board.add(new Tile( "Mediterrannean Avenue", 60, 50, 2, 10, 30, 90, 160, 250, 1));
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
	board.add(new Tile( "Pensylvania Avenue", 320,200,28,150,450,1000,1200,1400,34));	
	board.add(new Tile("Short Line", 200, 35));
	board.add(new Tile("Chance", 0, 36));
	board.add(new Tile( "Park Place", 350,200,35,175,500,1100,1300,1500,37));    
	board.add(new Tile("Luxury Tax", 0, 38));
	board.add(new Tile( "BoardWalk", 400,200,50,200,600,1400,1700,2000,39));
    }
		  
    private static int dice1;
    private static int dice2;
    private static int doubCount;

    public static void initialize() {
    }

    public static int roll () {
	System.out.println("Rolling");
	dice1 = (int) (Math.random() * 6) + 1;
	dice2 = (int) (Math.random() * 6) + 1;
	System.out.println("" + dice1 + " " + dice2);
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
		System.out.println("You have lost the game.");
                break;
	    }
	    System.out.println("Mortgage/de-house which property?" +
			       propPrint(p.getPropertyOwned()));
	    int i = Keyboard.readInt();
	    //NEED GUI IN
	    
	    if (p.getPropertyOwned().indexOf(i) > -1){
		if (getTile(i).getAddOn() >4){	 
		    System.out.println("Got " + p.sellHouse(getTile(i), 5));
		}
		else if (Board.getTile(i).getAddOn() > 1){
		    System.out.println("How many houses to sell?");
		    int h = Keyboard.readInt();		 

		    System.out.println("Got " + p.sellHouse(getTile(i), h));
		}
		else{
		    p.mort(getTile(i), p.getPropertyOwned().indexOf(i));
		    System.out.println("Mortgaged " + getTile(i).getName() +
				       "for " + getTile(i).getCost()/2);
		}
	    }
	    else
		System.out.println("You don't own that property. Try again");
        }
        return retBoo;
    }








    public static void play () { 
	new Board();

	System.out.println("Player 1, what's your name?");
	String one = Keyboard.readString();

	System.out.println("Player 2, what's your name?");
	String two = Keyboard.readString();

	players.add(new Player(one));
	players.add(new Player(two));

	while ( players.size() > 1 ) {
	    for(int i = 0; i < players.size(); i++){
		System.out.println("/n" + players.get(i).getName()+"'s Turn");
		Player ref = players.get(i);
	        if (ref.getInJail()){
		    System.out.println("Jailed");
		    ref.jailTurn();
		    if(ref.getTurnsInJail() >= 2){
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
			    System.out.println("Doubles! You are out of jail. You may move next turn");
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
			if (players.size() <= 1){
			    System.out.println("Game is over. The remaining player has won.");
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
				System.out.println("Do you want to purchase houses? (y/n)");
				yesno = Keyboard.readString();	 		
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
				int sub = board.get(prop).getAddOn();
				if (sub == 5){
				    System.out.println("Property is maxed already");
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
				    System.out.println(prompt);
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





    public static void main ( String[] args ) {
	play();
    }

}
