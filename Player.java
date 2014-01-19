import java.util.ArrayList;
import cs1.Keyboard;

public class Player{
    private String name;
    private String shape;
    private ArrayList<Integer> propertyOwned;
    private ArrayList<Integer> Mortgage;
    private int money;
    private int turnsInJail;
    private boolean inJail;
    private int pos;
    private int addOns;


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

    public int getAddOns(){
	return addOns;
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
        pos = 10;
        inJail = true;
        turnsInJail = 0;
    }
    
    public void jailBreak(){
        inJail = false;
    }

    public void mort(Tile T, int i){
        //        cashIn(T.getMortgage());
        //        mortgage.add(propertyOwned().remove(i));
    }

    public void deMort(Tile T, int i){
        //        loseMoney(T.getMortgage());
        //        mortgage.
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

    public void advanceTo(int i){
	int add = i + 40 - this.getPos();
	if (add >= 40)
	    add -= 40;
	this.addPos(add);
    }

    public void propertyInteract(Tile t, ArrayList<Player> playerss){
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
    
    public void jailInteract(){
	this.jailed();
    }
    
    public void luxuryInteract(){
	this.loseMoney(75);
    }
    
    public void incomeInteract(){
	System.out.println("Income tax! Pay 1)10% or 2)200");
        String a = Keyboard.readString();
	if (a.equals("1"))
	    this.loseMoney((int)(this.getMoney()/10));
	else if (a.equals("2"))
	    this.loseMoney(200);
	else
	    System.out.println("Try again, type 1 or 2");
    }

    public void chanceInteract(Arraylist<Players> playerss){
	System.out.println("Chance!");
	int x = (int)Math.random()*16;
	if (x == 8){
	    System.out.println("Go to jail");
	    jailed();
	}
	else if (x < 8){
	    if (x == 4){
		System.out.println("Move to nearest RailRoad");
		int newPos = ((getPos() +5)/10)*10 + 5;
		this.posAdd(newPos - getPos());
	    }
	    else if (x > 4){
		if (x == 6){// GET OUT OF JAIL IMPLEMENTATION
		    System.out.println("Got 50");
		    this.cashIn(50);
		}
		else if (x == 5){
		    System.out.println("Bank pays you dividend of 50!");
		    this.cashIn(50);
		}
		else{ // x must == 7
		    System.out.println("Go back three spaces.");
		    this.posAdd(-3);
		    // **************Need to make it interact with...******
		}
	    }
	    else{ // x < 4
		if (x == 2){
		    System.out.println("Advance to St. Charles Place");
		    int add = 51 - this.getPos();
		    if (add >= 40)
			add -= 40;
		    this.posAdd(add);
		}
		else if (x == 0){
		    System.out.println("Advance to Go");
		    this.posAdd(40 - this.getPos());
		}
		else if (x == 1){
		    System.out.println("Advance to Illinois Ave");
		    int add = 64 - this.getPos();
		    if (add >= 40)
			add -= 40;
		    this.posAdd(add);
		}
		else{
		    System.out.println("Advance to nearest Utility");
		    if (this.getPos() > 12 && this.getPos() < 28)
			this.advanceTo(28);
		    else
			this.advanceTo(12);
		}
	    }
	}
	else{
	    if(x == 12){
		System.out.println("Take a walk to the Boardwalk");
		this.advanceTo(39);
	    }
	    else if (x < 12){
		if (x == 10){
		    System.out.println("Pay poor tax of 15");
		    this.loseMoney(15);
		}
		else if (x == 9){
		    System.out.println("Make general repairs on all of your properties, 25 for each house, 125 for each hotel");
		    int repairs = this.getAddOns() * 25; 
		    this.loseMoney(repairs);
		}
		else {
		    System.out.println("Take a trip on the Reading(Reading Railroad)");
		    this.advanceTo(25);
		}
	    }
	    else {
		if (x == 14){
		    System.out.println("Your building loan matures- Collect 150");
		    this.cashIn(150);
		}
		else if (x == 13){
		    System.out.println("You have been elected chairman pay 50 to each player");
		    for(Player asd: playerss){   // NOT SURE IF IT WORKS *********************
			this.payOwner(asd, 50);
		    }
		    
		}
		else{ // x == 15
		    System.out.println("You have won a crossword competition - Collect 100");
		    this.cashIn(100);
		    
		}
	    }
	}
    }// end chance


    public void communityInteract(){
	// MY IMPLEMENTATION HERE
    }
	
		

    public void buyHouse ( Tile t, int i ) {
	money -= t.getHouseCost() * i;
	t.setAddOn(t.getAddOn() + i);
	addOns += i;
    }
    
    public void sellHouse ( Tile t, int i) {
	money -= t.getHouseCost() * i;
	t.setAddOn(t.getAddOn() - 1);
	addOns -= i;	
    }
    
    
