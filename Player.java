import java.util.ArrayList;

public class Player{
    private String name;
    private String shape;
    private ArrayList<Integer> propertyOwned;
    private ArrayList<Integer> Mortgage;
    private int money;
    private int turnsInJail;
    private boolean inJail;
    private int pos;

    public Player () {
        name = "player 1";
        money = 1500;
	pos = 0;
	turnsInJail = 0;
	inJail = false;
	propertyOwned = new ArrayList<Integer>();
	Mortgage = new ArrayList<Integer>();	
    }

    public Player ( String n ) {
	this();
        name = n;

    }

    public String getName(){
	return name;
    }
    public String getShape(){
	return shape;
    }
    public ArrayList<Integer> getPropertyOwned(){
	return propertyOwned;
    }
    public int getMoney(){
	return money;
    }
    public boolean getInJail(){
	return inJail;
    }
    public int getPos(){
	return pos;
    }

    public int getTurnsInJail(){
	return turnsInJail;
    }
    
    //-~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public int posAdd(int a){
	pos += a;
	if (pos >= 40){
	    pos = pos % 40;
	    money += 200;
	}
	return pos;
    }

    public int loseMoney(int amt){
	money -= amt;
	return amt;
    }

    public void cashIn(int amt){
	money += amt;
    }

    public int payOwner(Player p,int payment){
	p.getPaid(loseMoney(payment));
	return money;
    }
    
    public void jailed(){
	pos = 9;
	inJail = true;
	turnsInJail = 0;
    }
    
    public void jailBreak(){
	inJail = false;
    }

    public void mort(Tile T, int i){
	//	cashIn(T.getMortgage());
	//	mortgage.add(propertyOwned().remove(i));
    }

    public void deMort(Tile T, int i){
	//	loseMoney(T.getMortgage());
	//	mortgage. 
    }
    
    public void propertyInteract(Tile t){
    	if(t.getOwned() && !t.getOwner().equals(name)) {
	    System.out.println( "Paid " + t.getOwner() + " " 
				payOwner(t.getOwner(),t.calcRent()) + );
    	}// IF money < 0 ................
    	else 
	    if (money > t.getCost()){
		System.out.println("Buy " + t.getName() + " for " 
				   + t.getCost() + "?(y/n)");
		String ans = Keyboard.readString();
		if (ans.equals("y"))
		    System.out.println( buy(t));
	    }
    }

    public String buy ( Tile t ) {
        if ( t.getCost() > money )
	    return "You don't have enough money.";
        money -= t.getCost();
	propertyOwned.add( t.getIndex() );
        t.setOwner(name);
        return "You bought " + t.getName();
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

    /*
    public void buyHouse ( Tile t ) {
        money -= 100;
        t.setAdd_on (0);
    }

    public void buyHotel ( Tile t ) {
        money -= 200;
	
        t.setAdd_on (1);
    }
    
    */




