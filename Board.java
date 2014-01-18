import java.util.ArrayList;
import cs1.Keyboard;

public class Board {

    private static ArrayList<Tile> board;
    private ArrayList<Player> players;

    private static Tile A = new Tile( "A", 200 );
    private static Tile B = new Tile( "B", 200 );
    private static Tile C = new Tile( "C", 200 );
    private static Tile D = new Tile( "D", 200 );
    private static Tile E = new Tile( "E", 200 );

    public static void initialize() {
	board.add(A);
	board.add(B);
	board.add(C);
	board.add(D);
	board.add(E);
    }

    public static int roll () {
	return (int) (Math.random() * 12) + 1;
    }
    public boolean double(){     // 1/6 probability that roll is a double.
    	return (Math.random < (1/6));
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


    public boolean emergencyMort(Player p){
        retBoo = true;
        while (p.getMoney() < 0){

	    if (p.isBankrupt()){
                retBoo = false;
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

	while ( players.size() > 0 ) {
	    for(int i = 0; i < players.size() i++){
		Player ref = players.get(i);
	        if (ref.inJail()){
		    






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
