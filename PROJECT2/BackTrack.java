package Project2;
import java.util.*;

/**
 * When each algorithm has found a solution, print out
 * which items are taken and which are not, what the maximum profit is, and how many
 * nodes were checked.
 */

public class BackTrack {

	public static double maxProfit = 0.0;	// Greated profit
	public static boolean[] bestSet;		// Best Path
	public static double W;					// Max capacity
	public static ArrayList<Item> items; 	// Stored p & w array into this one class,
											// Contains method to sort by p/w
	
	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		
		//INTIALIZE ARRAYLIST
		System.out.println("How many items there are to potentially take: ");
		int totalTake = kb.nextInt();
		items = new ArrayList<Item>(totalTake);
		
		//PROMPT MAX WEIGHT OF THE BAG
		System.out.println("What is the max weight of the bag: ");
		W = kb.nextInt();
		kb.nextLine();
				
		//ADD ITEMS TO ARRAYLIST
		for(int i = 0; i < totalTake; i++) {
			System.out.println("What is the weight of item " + (i+1));
			int weight = kb.nextInt();
			System.out.println("What is the profit of item " + (i+1));
			int profit = kb.nextInt();
			items.add(new Item(i, weight, profit));
		}
		
		//DISPLAY ARRAYLIST (Sort by ratio in nonincreasing order)
		Collections.sort(items);
		Collections.reverse(items);
		System.out.println("-----------------Current Items-----------------");
		for(Item item: items) {
			System.out.println(item.toString());
		}
		
		knapsack(0, items.get(0).getProfit(),items.get(0).getWeight());
		
		kb.close();
	}//end main
	
	public static void knapsack(int i, double profit, double weight) {
		int numBest;
		boolean[] include = new boolean[5];	//Declare size 5 for now
		
		if( weight <= W && profit > maxProfit) {
			// this set is best for far
			maxProfit = profit;
			numBest = i;
			bestSet = include;
		}
		
		if(promising(i, profit, weight)) {
			include[i + 1] = true;
			double prof = profit + items.get(i+1).getProfit();
			double weigh = weight + items.get(i+1).getWeight();
			knapsack(i +1, prof, weigh);
			include[i + 1] = false;
			knapsack(i + 1, profit, weight);
		}
		
	}
	public static boolean promising(int i, double profit, double weight) {
		
		int j, k = 0, totweight;
		double bound;
		
		if(weight >= W) {
			return false;
		}
		else
			j = i + 1;
			bound = profit;
			totweight = (int) weight;
			while( (j <= (k-1)) && (totweight + items.get(j).getWeight() <= W) ) {
				totweight = (int) (totweight + items.get(j).getWeight());
				bound = bound + + items.get(j).getProfit();
				j++;
			}
			k = j;
			if (k <= (k-1))
					bound = bound + (W -totweight ) * items.get(k).getRatio();;
			
		
		
		boolean result = bound > maxProfit;
		return result;
	}
	
}
