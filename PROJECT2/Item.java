package Project2;

public class Item implements Comparable<Item>{
	private double weight;
	private double profit;
	private double ratio;
	
	public Item(double profit, double weight) {
		this.weight = weight;
		this.profit = profit;
	}
	public double getWeight() {
		return weight;
	}
	public double getProfit() {
		return profit;
	}
	public double getRatio() {
		ratio = (Math.round( (profit/weight) * 100.0 ) / 100.0);
		return ratio;
	}
	
	public int compareTo(Item param) {
		Double myRatio = Double.valueOf(getRatio());
		return myRatio.compareTo(param.getRatio());
	}
	public String toString() {
		return "Weight: " + weight + " Profit: " + profit + " Ratio: " + getRatio();
	}

}
