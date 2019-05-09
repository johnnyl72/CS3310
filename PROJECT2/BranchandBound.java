package Project2;
import java.util.*;

/**
 * When each algorithm has found a solution, print out
 * which items are taken and which are not, what the maximum profit is, and how many
 * nodes were checked.
 */

public class BranchandBound {

	public static double W;								// Max weight of bag
	public static ArrayList<Node> items;
	public static double maxprofit;
	public static int nodesChecked;
	
	
	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		System.out.println("How many items to take: ");
		int totalTake = kb.nextInt();
		items = new ArrayList<Node>(totalTake);	
		System.out.println("What is the max weight of the bag: ");
		W = kb.nextInt();
		
		for(int i = 1; i <= totalTake; i++) {
			System.out.println("What is the profit of item " + (i));
			int profit = kb.nextInt();
			System.out.println("What is the weight of item " + (i));
			int weight = kb.nextInt();
			items.add(new Node(profit, weight));
		}
		
		Collections.sort(items);
		items.add(0, new Node(0,0));
		//DISPLAY ARRAYLIST (Sort by ratio in nonincreasing order)	
		System.out.println("-----------------Current Items-----------------");
		for(Node item: items) {
			System.out.println(item.toString());
			
		}
		
		//Initialize and solve
		knapsack3(0, items.get(0).getProfit(), items.get(0).getWeight());

		System.out.println("RESULTS");
		System.out.println("The max profit is " + maxprofit);
		System.out.println("Total nodes checked is " + nodesChecked) ;
	}// end main
	
	
	public static void knapsack3(int n, double p, double w) {
		nodesChecked = -1;
		PriorityQueue<Node> PQ = new PriorityQueue<Node>(); //Initialize
		
		Node u = new Node();
		Node v = new Node();
		
		v.setLevel(0);				//Initialize v to the root
		v.setProfit(0.0);			//Initialize v to the root
		v.setWeight(0.0);			//Initialize v to the root
		maxprofit = 0.0;
		v.setBound(bound(v));		//Compute v bound
		
		System.out.println("V bound: " + v.getBound());
		System.out.println("V profit: " + v.getProfit());
		System.out.println("V weight: " + v.getWeight());
		System.out.println("--");
		PQ.add(v);				//Insert root to PQ	
		while(!PQ.isEmpty()) {
			v = PQ.poll();	//deque
			System.out.println("Current PQ: " + PQ.toString());
		
			if(v.getBound() > maxprofit) {
				//Remove node from front priority and assign it to v, visit left child
				u = new Node();
				u.setLevel(v.getLevel() + 1);
				u.setProfit(v.getProfit() + items.get(u.getLevel()).getProfit());
				u.setWeight(v.getWeight() + items.get(u.getLevel()).getWeight());
				u.setBound(bound(u));
				nodesChecked++;
				System.out.println("U bound: " + u.getBound());
				System.out.println("U profit: " + u.getProfit());
				System.out.println("U weight: " + u.getWeight());
				System.out.println("--");
				if(u.getWeight() <= W && u.getProfit() > maxprofit) {
					maxprofit = u.getProfit();	
				}
				// Insert u(1,1) to PQ
				if(u.getBound() > maxprofit) {
					PQ.add(u);
				}
				
				//Visit unvisited right child
				u = new Node();
				u.setLevel(v.getLevel()+1);;
				u.setWeight(v.getWeight());
				u.setProfit(v.getProfit());
				u.setBound(bound(u));
				nodesChecked++;
				System.out.println("U bound: " + u.getBound());
				System.out.println("U profit: " + u.getProfit());
				System.out.println("U weight: " + u.getWeight());
				System.out.println("--");
				//Is bound promising?
				if(u.getBound() > maxprofit) {
					PQ.add(u);
					
				}
				
			}
		}

	}
	
	public static float bound(Node u) {
		int j;
		int k;
		double weight;
		float bound;
		if(u.getWeight() >= W)
			return 0;
		else {
			bound = (float) u.getProfit();
			j = u.getLevel() + 1;
			weight = u.getWeight();
			while( (j < (items.size())) && (weight + items.get(j).getWeight() <= W)) {
				weight = weight + items.get(j).getWeight();
				bound = (float) (bound + items.get(j).getProfit());
				j++;
			}
		}
		k = j;
		if( k < (items.size())) {
			bound = (float) (bound + (W-weight) * (items.get(k).getRatio()));
		}
		return bound;
			
	}//end bound
	
	
}
