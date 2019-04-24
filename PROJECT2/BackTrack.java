package Project2;
import java.util.*;

/**
 * When each algorithm has found a solution, print out
 * which items are taken and which are not, what the maximum profit is, and how many
 * nodes were checked.
 */

public class BackTrack {

	public static double maxProfit = 0.0;	//Initialize to 0
	public static boolean[] bestSet;			//Initialize to empty
	public static boolean[] include;
	public static double W;
	public static int numBest = 0;
	public static ArrayList<Item> items; 
	
	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		//INTIALIZE ARRAYLIST
		System.out.println("How many items there are to potentially take: ");
		int totalExisting = kb.nextInt();
		
		items = new ArrayList<Item>(totalExisting);
		//ADD ITEMS TO ARRAYLIST
		for(int i = 0; i < totalExisting; i++) {
			System.out.println("What is the weight of item " + (i+1));
			int weight = kb.nextInt();
			System.out.println("What is the profit of item " + (i+1));
			int profit = kb.nextInt();
			items.add(new Item(i, weight, profit));
			
		}
		//PROMPT MAX WEIGHT OF THE BAG
		System.out.println("What is the max weight of the bag: ");
		W = kb.nextInt();
		kb.nextLine();
		
		//DISPLAY ARRAYLIST (Sort by ratio in nonincreasing order)
		Collections.sort(items);
		Collections.reverse(items);
		System.out.println("-----------------Current Items-----------------");
		for(Item item: items) {
			System.out.println(item.toString());
		}
		
		
		
		kb.close();
	}//end main
	
	public static void knapsack(int i, double profit, double weight) {
		
		if( weight <= W && profit > maxProfit) {
			// this set is best for far
			maxProfit = profit;
			numBest = i;
			bestSet[i] = include[i];
		}
		
		if(promising(i, profit, weight)) {
			include[i + 1 ] = true;
			knapsack(i +1, profit + items.get(i+1).getProfit(), weight + items.get(i+1).getWeight());
			include[i + 1] = false;
			knapsack(i + 1, profit, weight);
		}
		
	}
	public static boolean promising(int i, double profit, double weight) {
		
		int j, k;
		int totweight;
		double bound;
		
		if(weight >= W) {
			return false;
		}
		else
			j = i + 1;
			bound = profit;
			totweight = (int) weight;
			while( j <= n && totweight + items.get(j).getWeight()) {
				totweight = (int) (totweight + items.get(j).getWeight());
				bound = bound + + items.get(j).getProfit();
				j++;
			}
			k = j;
			if (k <= n)
					bound = bound + (W -totweight ) * items.get(k).getRatio();;
			
		
		
		boolean result = bound > maxProfit;
	}
	
}
