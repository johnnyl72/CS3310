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
	
	public static ArrayList<Node> items;
	
	public static int counter = 0;
	
	

	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		
		//INTIALIZE ARRAYLIST
		//System.out.println("How many items there are to potentially take: ");
		//int totalTake = kb.nextInt();
		items = new ArrayList<Node>(5);
		
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
		*/
		items.add(new Node(0, 0));
		items.add(new Node(40, 2));
		items.add(new Node(30, 5));
		items.add(new Node(50, 10));
		items.add(new Node(10, 5));
	
		
		//Collections.sort(items);
		
		//DISPLAY ARRAYLIST (Sort by ratio in nonincreasing order)	
		System.out.println("-----------------Current Items-----------------");
		for(Node item: items) {
			System.out.println(item.toString());
		}
		
		
	
		knapsack3(0, items.get(0).getProfit(), items.get(0).getWeight());
		
		kb.close();
	}// end main
	public static void knapsack3(int n, double p, double w) {

		PriorityQueue<Node> PQ = new PriorityQueue<Node>(); //Initialize
		PQ.clear();
		Node u = new Node();
		Node v = new Node();
		
		v.level = 0;			//Initialize v to the root
		v.profit = 0.0;			//Initialize v to the root
		v.weight = 0.0;			//Initialize v to the root
		double maxprofit = 0.0;
		v.bound = bound(v);		//Compute v bound
		
		System.out.println("bound: " + v.bound);
		System.out.println("profit: " + v.profit);
		System.out.println("weight: " + v.weight);
		System.out.println("--");
		PQ.add(v);				//Insert root to PQ
		
		while(!PQ.isEmpty()) { 
			PQ.poll();	//deque
			
			if(v.bound > maxprofit) {
				//Remove node from front priority and assign it to v, visit left child
				u.level = v.level + 1;
				u.weight = v.weight + items.get(u.level).weight;
				u.profit = v.profit + items.get(u.level).profit;
				u.bound = bound(u);
				System.out.println("bound: " + u.bound);
				System.out.println("profit: " + u.profit);
				System.out.println("weight: " + u.weight);
				System.out.println("--");
				if(u.weight <= W && u.profit > maxprofit) {
					maxprofit = u.profit;
				}
				// Insert u to PQ
				if(u.bound > maxprofit) {
					PQ.add(u);
				}
				//Visit right child
				u.level = v.level +1;
				u.weight = v.weight;
				u.profit = v.profit;
				u.bound = bound(u);
				System.out.println("bound: " + u.bound);
				System.out.println("profit: " + u.profit);
				System.out.println("weight: " + u.weight);
				System.out.println("--");
	
				if(u.bound > maxprofit)
					PQ.add(u);
				
			}
		}
			// ONLY DOES FIRST LEVEL

	}
	
	public static float bound(Node u) {
		int j;
		int k;
		double weight;
		float bound;
		
		if(u.weight >= W)
			return 0;
		else {
			bound = (float) u.profit;
			j = u.level + 1;
			weight = u.weight;
			while( (j <= (items.size() -1)) && (weight + items.get(j).weight <= W)) {
				weight = weight + items.get(j).weight;
				bound = (float) (bound + items.get(j).profit);
				j++;
			}
		}
		k = j;
		if( k <= (items.size()-1)) {
			bound = (float) (bound + (W-weight) * (items.get(k).getRatio()));
		}
		return bound;
			
	}//end bound
	
	
}
