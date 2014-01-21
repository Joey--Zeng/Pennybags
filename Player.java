import java.util.ArrayList;
import cs1.Keyboard;

public class Player{
    private String name;
    private String shape;
    private ArrayList<Integer> propertyOwned;
    private ArrayList<Integer> mortgage;
    private int money;
    private int turnsInJail;
    private boolean inJail;
    private int pos;
    private int addOns;
    private boolean card;


    public Player () {
        name = "player 1";
	shape = "circle";
        money = 1500;
        pos = 0;
        turnsInJail = 0;
        inJail = false;
        propertyOwned = new ArrayList<Integer>();
        mortgage = new ArrayList<Integer>();        
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
    public ArrayList<Integer> getMortgage(){
	return mortgage;
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
    
    public boolean getCard(){
	return card;
    }
    
    //-~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void loseCard(){
	card = false;
    }

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

    public int cashIn(int amt){
        money += amt;
	return amt;
    }

    public int payOwner(Player p, int payment){
        return p.cashIn(loseMoney(payment));
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
	cashIn(T.getCost()/2);
	mortgage.add(propertyOwned.remove(i));
    }
    
    public void deMort(Tile T, int i){
	loseMoney(T.getCost()/2);
	propertyOwned.add(mortgage.remove(i));      
    }

    public String buy ( Tile t ) {
        if ( t.getCost() > money )
	    return "You don't have enough money.";
        money -= t.getCost();
        propertyOwned.add( t.getPos() );
        t.setOwner(this);
        return "You bought " + t.getName();
    }

    public void jailTurn(){
	turnsInJail++;
    }



   public String toString() {
        String s = "";
        s += "Name : " + name + "\n";
        s += "You have $" + money + "\n";
        s += "You own: \n";
        
	/*       for ( Tile t : propertyOwned ) {
         s += t;
        }
	*/
        
        return s;
    }

    public void advanceTo(int i){
	int add = i + 40 - this.getPos();
	if (add >= 40)
	    add -= 40;
	this.posAdd(add);
    }

    public int propertyInteract(Tile t, ArrayList<Player> playerss, int dice1, int dice2){
	int retInt = 0;
	int pos = t.getPos();

	if (pos == 7 || pos == 22 || pos == 36)	    
	    retInt = chanceInteract(playerss);
	else if (pos == 2 || pos == 17 || pos == 33)
	    communityInteract(playerss);
	else if (pos == 4)
	    incomeInteract();
	else if (pos == 38)
	    luxuryInteract();
	else if (pos == 0 || pos == 10 || pos == 20){}
	else if (pos == 30){
	    this.jailed();
	}

	else{
	    if(t.getOwned()) {
		if(!t.getOwner().getName().equals(name)){
		    if (t.getOwner().getPropertyOwned().contains(t.getPos())){
			if(t.getPos() == 12 || t.getPos() == 28){ // then its a utility
			    System.out.println( "Paid " + t.getOwner().getName() + " " +
						utilityPay(t, dice1, dice2));
			}
			else if(t.getPos() % 10 == 5){ // then its a RR
			    System.out.println( "Paid " + t.getOwner().getName() + " " +
						RRPay(t) );		    
			}
			else{
			    System.out.println( "Paid " + t.getOwner().getName() + " " +
						payOwner(t.getOwner(),t.calculateRent()) );
			}
		    }
		}
	    }// IF money < 0 ................
	    else{
		if (money > t.getCost()){
		    System.out.println("Buy " + t.getName() + " for "
				       + t.getCost() + "?(y/n)");
		    String ans = Keyboard.readString();
		    if (ans.equals("y"))
			System.out.println( buy(t));
		}
	    }
	}
	return retInt;
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

    public int chanceInteract(ArrayList<Player> playerss){
	int retInt = 0;
	System.out.println("Chance!");
	int x = (int)(Math.random()*16);
	if (x == 8){
	    System.out.println("Go to jail");
	    jailed();
	}
	else if (x < 8){
	    if (x == 4){
		System.out.println("Move to nearest RailRoad");
		int newPos = ((getPos() +5)/10)*10 + 5;
		this.posAdd(newPos - getPos());
		retInt = getPos();
	    }
	    else if (x > 4){
		if (x == 6){		    
		    card = true;
		    System.out.println("Get Out of Jail Free card");
		}
		else if (x == 5){
		    System.out.println("Bank pays you dividend of 50!");
		    this.cashIn(50);
		}
		else{ // x must == 7
		    System.out.println("Go back three spaces.");
		    this.posAdd(-3);
		    retInt = this.getPos();

		}
	    }
	    else{ // x < 4
		if (x == 2){
		    System.out.println("Advance to St. Charles Place");
		    int add = 51 - this.getPos();
		    if (add >= 40)
			add -= 40;
		    this.posAdd(add);
		    retInt = 11;
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
		    retInt = 24;
		}
		else{
		    System.out.println("Advance to nearest Utility");
		    if (this.getPos() > 12 && this.getPos() < 28)
			this.advanceTo(28);
		    else
			this.advanceTo(12);
		    retInt = this.getPos();
		}
	    }
	}
	else{
	    if(x == 12){
		System.out.println("Take a walk to the Boardwalk");
		this.advanceTo(39);
		retInt = this.getPos();
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
		    this.advanceTo(5);
		    retInt = this.getPos();
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
	return retInt;
    }// end chance
    

    public void communityInteract(ArrayList<Player> playerss){
	System.out.println("Community Chest!");
	int x = (int)(Math.random()*17);
	if (x == 8){
	    System.out.println("Income tax refund - Collect 20");
	    this.cashIn(20);
	}
	else if (x < 8){
	    if (x < 4){
		if ( x == 0){
		    System.out.println("Advance to Go");
		    this.advanceTo(0);		 
		}
		else if (x == 1){
		    System.out.println("Bank error in your favor - Collect 200!");
		    this.cashIn(200);
		}
		else if (x == 2){	  
		    System.out.println("Doctor's fees - pay 50");
		    this.loseMoney(50);
		}
		else{// x == 3
		    System.out.println("From sale of stock, you gain 50");
		    this.cashIn(50);
		}
	    }
	    else{ //x >= 4
		if (x == 4){
		    System.out.println("Get Out of Jail Free card");
		    card = true;			
		}
		else if (x == 5){
		    System.out.println("Go to Jail");
		    this.jailed();
		}
		else if (x == 6){
		    System.out.println("Grand Opera Night - Collect 50 from each player for seats");
		    this.collect(playerss, 50);
		}
		else{  // x == 7
		    System.out.println("Holiday Fund matures - Receive 100");
		    this.cashIn(100);
		}
	    }
	}
	else {  // x > 8
	    if (x <= 12){
		if (x == 9){
		    System.out.println("It's your birthday - Collect 10 from each player");
		    this.collect(playerss, 10);
		}
		else if (x == 10){
		    System.out.println("Life insurance matures - Collect 100");
		    this.cashIn(100);
		}
		else if (x == 11){
			System.out.println("Pay hospital fees of 100");
			this.loseMoney(100);
		}
		else{ //x == 12
		    System.out.println("Pay school fees of 150");
		    this.loseMoney(150);
		}		
	    }
	    else{  // x > 12
		if (x == 13){
		    System.out.println("Receive 25 in consultancy fees");
		    this.cashIn(25);
		}
		else if (x == 14){
		    System.out.println("You are assessed for street repairs- 40 per house, 200 per hotel");
		    this.loseMoney(this.getAddOns() * 40);
		}
		else if (x == 15){
		    System.out.println("Second place in beauty contest - Collect 10");
		    this.cashIn(10);
		}
		else{ // x == 16
		    System.out.println("You inherit 100");
		    this.cashIn(100);
		}
	    }
	}
    }

    public int utilityPay(Tile t, int a, int b){
        int retInt = 0;
	if (t.getOwner().getPropertyOwned().contains(12) &&
	    t.getOwner().getPropertyOwned().contains(28)){
	    this.payOwner(t.getOwner(), 10*(a+b));
	    retInt = 10*(a+b);
	}
	else{
	    this.payOwner(t.getOwner(), 4*(a+b));
	    retInt = 4*(a+b);
	}
	return retInt;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public int RRPay(Tile t){
	int retInt = 0;
	int mult = 1;
	if (t.getOwner().getPropertyOwned().contains(5))
	    mult *= 2;
	if (t.getOwner().getPropertyOwned().contains(15))
	    mult *= 2;
	if (t.getOwner().getPropertyOwned().contains(25))
	    mult *= 2;
	if (t.getOwner().getPropertyOwned().contains(35))
	    mult *= 2;
	mult /= 2;
	this.payOwner(t.getOwner(), 25*mult);
	return 25*mult;
    }
    
    public void collect(ArrayList<Player> playerss, int i){
	for (Player p : playerss){
	    if (!p.getName().equals(this.getName())){
		this.cashIn(i);
		p.loseMoney(i);
		System.out.println("Collected" + i + "from" + p.getName());
	    }
	}
    }

    
    public int buyHouse ( Tile t, int i ) {
	money -= t.getHouseCost() * i;
	t.setAddOn(t.getAddOn() + i);
	addOns += i;
	return t.getHouseCost()*i;
    }
    
    public int sellHouse ( Tile t, int i) {
	money -= t.getHouseCost() * i;
	t.setAddOn(t.getAddOn() - 1);
	addOns -= i;
	return t.getHouseCost() * i;
    }

    public boolean isBankrupt(){
	boolean retBoo = false;
	if (money <= 0 && propertyOwned.size() == 0)
	    retBoo = true;
	return retBoo;
    }

    public boolean checkBrown(){
	return (propertyOwned.contains(1) &&
		propertyOwned.contains(3));
    }
    public boolean checkLBlue(){
	return (propertyOwned.contains(6) &&
		propertyOwned.contains(8) &&
		propertyOwned.contains(9));
    }
    public boolean checkPink(){
	return (propertyOwned.contains(11) &&
		propertyOwned.contains(13) &&
		propertyOwned.contains(14));
    }
    public boolean checkOrange(){
    	return (propertyOwned.contains(16) &&
		propertyOwned.contains(18) &&
		propertyOwned.contains(19));
    }
    public boolean checkRed(){
	return (propertyOwned.contains(21) &&
		propertyOwned.contains(23) &&
		propertyOwned.contains(24));
    }
    public boolean checkYellow(){
	return (propertyOwned.contains(26) &&
		propertyOwned.contains(27) &&
		propertyOwned.contains(29));
    }
    public boolean checkGreen(){
    	return (propertyOwned.contains(31) &&
		propertyOwned.contains(32) &&
		propertyOwned.contains(34));
    }
    public boolean checkBlue(){
    	return (propertyOwned.contains(37) &&
		propertyOwned.contains(39));
    }


}// end class Player
    
	
