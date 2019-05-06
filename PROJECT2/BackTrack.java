package Project2;
import java.util.*;

/**
 * When each algorithm has found a solution, print out
 * which items are taken and which are not, what the maximum profit is, and how many
 * nodes were checked.
 */

public class BackTrack {

	public static double maxProfit;						// Greatest profit
	public static String[] bestSet;						// Best Path
	public static double W;								// Max capacity of the bag
	public static ArrayList<Item> items; 				// Stored p & w array into this one class,
														// Contains method to sort nonincreasing by p/w ratio
	public static String[] include;
	
	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		
		//INTIALIZE ARRAYLIST
		System.out.println("How many items there are to potentially take: ");
		int totalTake = kb.nextInt();
		items = new ArrayList<Item>(totalTake);
		
		bestSet = new String[totalTake];
		include = new String[totalTake];
		
		//PROMPT MAX WEIGHT OF THE BAG
		System.out.println("What is the max weight of the bag: ");
		W = kb.nextInt();
		//ADD ITEMS TO ARRAYLIST
		for(int i = 1; i <= totalTake; i++) {
			System.out.println("What is the profit of item " + (i));
			int profit = kb.nextInt();
			System.out.println("What is the weight of item " + (i));
			int weight = kb.nextInt();
			items.add(new Item(i, profit, weight));
		}
		
		//DISPLAY ARRAYLIST (Sort by ratio in nonincreasing order)
		Collections.sort(items);	// Low to High
		Collections.reverse(items); // High to Low
		System.out.println("-----------------Current Items-----------------");
		for(Item item: items) {
			System.out.println(item.toString());
		}
		
		knapsack(0, items.get(0).getProfit(), items.get(0).getWeight());
		
		// Print out max profit and which items are taken & not, how many nodes were checked
		System.out.println(maxProfit);
		
		for(int i = 0; i < bestSet.length; i++) {
			System.out.println(bestSet[i]);
		}
		kb.close();
	}//end main
	
	public static void knapsack(int i, double profit, double weight) {
	
		if( weight <= W && profit > maxProfit) {
			// this set is best so far
			maxProfit = profit;
			bestSet = include;
			int numBest = i;
		}
		
		if(promising(i, profit, weight)) {
			include[i+1] = "yes";
			knapsack(i+1,(profit+items.get(i+1).getProfit()),(weight+items.get(i+1).getWeight()));
			include[i+1] = "no";
			knapsack(i+1,profit,weight);
		}
		
	}
	public static boolean promising(int i, double profit, double weight) {
		
		int j; 
		int k; 
		double totweight;
		double bound;
		
		if(weight >= W) {
			return false;
		}
		else
			j = i + 1;
			bound = profit;
			totweight = weight;
			while((j <= (items.size() -1)) && (totweight + items.get(j).getWeight() <= W)) {
				totweight = (totweight + items.get(j).getWeight());
				bound = bound + items.get(j).getProfit();
				j++;
			}
			k = j;
			if (k <= (items.size() - 1))
					bound = bound + (W-totweight) * (items.get(k).getRatio());;
			
		return bound > maxProfit;
	}
	
}
