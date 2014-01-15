import java.util.ArrayList;

public class Player{
    private String shape;
    private ArrayList<Integer> propertyOwned;
    private int money;
    private boolean inJail;
    private int pos;
    
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

    public int posAdd(int a){
	pos += a;
	if (pos >= 40){
	    pos = pos % 40;
	    money += 200;
	}
	return pos;
    }
    
    //-~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public int loseMoney(int amt){
	money -= amt;
	return amt;
    }

    public void getPaid(int amt){
	money += amt;
    }

    public int payOwner(Player p,int payment){
	p.getPaid(loseMoney(payment));
	return money;
    }
    
    