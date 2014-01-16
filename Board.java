import java.util.ArrayList;
import cs1.Keyboard;

public class Board {

    private static ArrayList<Tile> board;

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

    public static int role () {
	return (int) (Math.random() * 6);
    }

    public static void play () {
	initialize();

	System.out.println("Player 1, what's your name?");
	String one = Keyboard.readString();

	System.out.println("Player 2, what's your name?");
	String two = Keyboard.readString();

	Player a = new Player(one);
	Player b = new Player(two);

	while ( a.getMoney() > 0 || b.getMoney() > 0 ) {

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

	}



    }

    public static void main ( String[] args ) {
	play();
    }

}