import java.util.ArrayList;

public class Player {

    private String name;
    private int money;
    private int pos;
    private ArrayList<Tile> propertyOwned;

    public Player () {
	name = "player 1";
	money = 1500;
    }

    public Player ( String n ) {
	money = 1500;
	name = n;
	pos = 0;
    }

    public String getName() {
	return name;
    }

    public int getMoney() {
	return money;
    }

    public int getPos() {
	return pos;
    }

    public void setPos(int n) {
	pos += n;
	if (pos > 5) 
	    pos -= 5;
    }

    public String toString() {
	String s = "";
	s += "Name : " + name + "\n";
	s += "You have $" + money + "\n";
	s += "You own: \n";
	
	for ( Tile t : propertyOwned ) {
	    s += t;
	}
	
	return s;
    }

    public String buy ( Tile t ) {
	if ( t.getCost() > money ) 
	    return "You don't have enough money.";
	money -= t.getCost();
       	propertyOwned.add( t );
	t.setOwner(name);
	return "You bought " + t.getName();
    }

    public void payRent ( Tile t ) {
	money -= t.calculateRent();
    }
    public void buy_house ( Tile t ) {
	money -= 100;
	t.setAdd_on (0);
    }

    public void buy_hotel ( Tile t ) {
	money -= 200;
	t.setAdd_on (1);
    }



}