package Project2;
import java.util.*;

/**
 * When each algorithm has found a solution, print out
 * which items are taken and which are not, what the maximum profit is, and how many
 * nodes were checked.
 */

public class BackTrack {

	public static double maxProfit;	// Greated profit
	public static String[] bestSet;		// Best Path
	public static double W;					// Max capacity
	public static ArrayList<Item> items; 	// Stored p & w array into this one class,
											// Contains method to sort nonincreasing by p/w ratio
	public static String[] include = new String[4];	//Declare size for now
	
	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		
		//INTIALIZE ARRAYLIST
		System.out.println("How many items there are to potentially take: ");
		int totalTake = kb.nextInt();
		items = new ArrayList<Item>(totalTake);
		
		//PROMPT MAX WEIGHT OF THE BAG
		System.out.println("What is the max weight of the bag: ");
		W = kb.nextInt();
		
		//Intialize
		items.add(new Item(0, 0, 0));
		
		//ADD ITEMS TO ARRAYLIST
		for(int i = 1; i < totalTake; i++) {
			System.out.println("What is the weight of item " + (i+1));
			int weight = kb.nextInt();
			System.out.println("What is the profit of item " + (i+1));
			int profit = kb.nextInt();
			items.add(new Item(i, weight, profit));
		}
		
		//DISPLAY ARRAYLIST (Sort by ratio in nonincreasing order)
		Collections.sort(items);	// Low to High
		Collections.reverse(items); // High to Low
		System.out.println("-----------------Current Items-----------------");
		for(Item item: items) {
			System.out.println(item.toString());
		}
		
		//Intialize maxprofit to $0 and bestSet to empty, aka visit (0,0)
		maxProfit = 0.0;
		bestSet = new String[totalTake];
		
		knapsack( 0, items.get(0).getProfit(), items.get(0).getProfit());
		
		// Print out max profit and which items are taken & not, how many nodes were checked
		System.out.println(maxProfit);
		for( int i = 0; i < bestSet.length; i++)
		{
		    String element = bestSet[i];
		    System.out.println( element );    
		}
		kb.close();
	}//end main
	
	public static void knapsack(int i, double profit, double weight) {
	
		
		if( weight <= W && profit > maxProfit) {
			// this set is best for far
			maxProfit = profit;
			int numBest = i;
			bestSet = include;
		}
		
		if(promising(i, profit, weight)) {
			include[i + 1] = "yes";
			knapsack(i +1, (profit + items.get(i+1).getProfit()) , ( weight + items.get(i+1).getWeight()));
			include[i + 1] = "no";
			knapsack(i + 1, profit, weight);
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
			while( (j < (items.size())) && (totweight + items.get(j).getWeight() <= W) ) {
				totweight = (totweight + items.get(j).getWeight());
				bound = bound + items.get(j).getProfit();
				j++;
			}
			k = j;
			if (k < (items.size()))
					bound = bound + (W - totweight ) * items.get(k).getRatio();;
			
		boolean result = bound > maxProfit;
		return result;
	}
	
}
