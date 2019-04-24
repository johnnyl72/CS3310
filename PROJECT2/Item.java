package Project2;

public class Item implements Comparable<Item>{
	private double weight;
	private double profit;
	private double ratio;
	private int itemID;
	
	public Item(int itemID, double weight, double profit) {
		this.itemID = itemID;
		this.weight = weight;
		this.profit = profit;
	}
	
	public int getitemID() {
		return itemID;
	}
	public double getWeight() {
		return weight;
	}
	public double getProfit() {
		return profit;
	}
	public double getRatio() {
		this.ratio = Math.round( (profit/weight) * 100.0 ) / 100.0; 
		return ratio;
	}
	
	public int compareTo(Item param) {
		Double myRatio = Double.valueOf(getRatio());
		return myRatio.compareTo(param.getRatio());
	}
	public String toString() {
		return "Item ID: " + itemID + " Weight: " + weight + " Profit: " + profit + " Ratio: " + getRatio();
	}

}
