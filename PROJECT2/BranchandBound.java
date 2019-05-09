package Project2;
import java.util.*;

/**
 * When each algorithm has found a solution, print out
 * which items are taken and which are not, what the maximum profit is, and how many
 * nodes were checked.
 */

public class BranchandBound {

	public static int nodesChecked = 0;
	public static double W;								// Max weight of bag
	public static double maxProfit;					// Greatest profit
	public static double[] p = {40,30,50,10};
	public static double[] w = {2,5,10,5};

	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		/*
		//INTIALIZE ARRAYLIST
		//System.out.println("How many items there are to potentially take: ");
		//int totalTake = kb.nextInt();
		items = new ArrayList<Node>(4);
		
		//PROMPT MAX WEIGHT OF THE BAG
		//System.out.println("What is the max weight of the bag: ");
		//W = kb.nextInt();
		W = 16;
		/*
		//ADD ITEMS TO ARRAYLIST
		for(int i = 1; i <= totalTake; i++) {
			System.out.println("What is the profit of item " + (i));
			int profit = kb.nextInt();
			System.out.println("What is the weight of item " + (i));
			int weight = kb.nextInt();
			items.add(new Item(profit, weight));
		}
		
		items.add(new Node(40, 2));
		items.add(new Node(10, 5));
		items.add(new Node(50, 10));
		items.add(new Node(30, 5));
		Collections.sort(items);
		//DISPLAY ARRAYLIST (Sort by ratio in nonincreasing order)	
		System.out.println("-----------------Current Items-----------------");
		for(Node item: items) {
			System.out.println(item.toString());
		}
		*/
		
	
		
		W = 16;
		knapsack3(0, p, w);
		
		kb.close();
	}// end main
	public static void knapsack3(int n, double[] p, double[] w) {
	
		PriorityQueue<Node> PQ = new PriorityQueue<Node>(); //Initialize
		Node u = new Node();
		Node v = new Node();
		v.level = 0;			//Initialize v to the root
		v.profit = 0;			//Initialize v to the root
		v.weight = 0;			//Initialize v to the root
		maxProfit = 0;			//Initialize maxprofit to 0
		v.bound = bound(v);		//Compute v bound
		PQ.add(v);				//Insert root to PQ
		
		while(!PQ.isEmpty()) {
			PQ.remove(v);

		}
		
		if(v.bound > maxProfit) {
			//Remove node from front priority and assign it to v, visit left child
			u.level = v.getLevel() + 1;
			u.weight = v.weight + w[u.level];
			u.profit = v.profit + p[u.level];
			u.bound = bound(u);
			
			if(u.weight <= W && u.profit > maxProfit) {
				maxProfit = u.profit;
			}
			// Determine if we should visit its children
			if(u.bound > maxProfit) {
				PQ.add(u);
			}
			u.weight = v.weight;
			u.profit = v.profit;
			u.bound = bound(u);

			if(u.bound > maxProfit)
				PQ.add(u);	
		}
		System.out.println(maxProfit);
	}
	
	public static double bound(Node u) {
		int j;
		int k;
		double totweight;
		double result;
		
		if(u.weight >= W) {
			return 0;
		}
		else {
			result = u.profit;
			j = (u.level + 1);
			totweight = u.weight;
			while( j  <= (w.length -1) && (totweight + w[j] <= W)) {
				totweight = (totweight + w[j]);
				result = result + p[j];
				j++;
			}
		}
		k = j;
		if( k <= (w.length-1)) {
			result = result + ((W-totweight) * (p[k] / w[k]));
		}
		return result;
			
	}//end bound
	
	
}
