public class Tile {

    private String owner;
    private String name;
    private int cost;
    private int rent;
    private int[] add_on = { 0, 0 };
    private boolean owned = false;


    public Tile ( String n, int c ) {
	name = n;
	cost = c;
	rent = (int) (c * 0.5);
    }

    public String getName () {
	return name;
    }

    public int getCost () {
	return cost;
    }

    public int getRent () {
	return rent;
    }

    public String setOwner ( String n ) {
	owner = n;
	owned = true;
	return owner;
    }

    public void setAdd_on ( int n ) {
	add_on[n] += 1;
    }

    public String toString() {
	String s = "";
	s += "   " + name + "\n";
	s += "   Cost:" + cost + "\n";
	s += "   rent:" + rent + "\n";
	s += "   " + add_on[0] + " houses\n";
	s += "   " + add_on[1] + " hotels\n";
	return s;
    }

    public int calculateRent () {
	rent += add_on[0] * 100;
	rent += add_on[0] * 200;
	return rent;
    }



}