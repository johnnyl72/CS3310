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
	public static double maxprofit;

	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		
		items = new ArrayList<Node>(5);	
		W = 16;
		items.add(new Node(0, 0));
		items.add(new Node(40, 2));
		items.add(new Node(30, 5));
		items.add(new Node(50, 10));
		items.add(new Node(10, 5));
	
		//DISPLAY ARRAYLIST (Sort by ratio in nonincreasing order)	
		System.out.println("-----------------Current Items-----------------");
		for(Node item: items) {
			System.out.println(item.toString());
		}
	
		knapsack3(0, items.get(0).getProfit(), items.get(0).getWeight());

	}// end main
	public static void knapsack3(int n, double p, double w) {

		PriorityQueue<Node> PQ = new PriorityQueue<Node>(); //Initialize
		PQ.clear();

		Node u = new Node();
		Node v = new Node();
		
		v.setLevel(0);		//Initialize v to the root
		v.setProfit(0.0);			//Initialize v to the root
		v.setWeight(0.0);			//Initialize v to the root
		maxprofit = 0.0;
		v.setBound(bound(v));		//Compute v bound
		
		System.out.println("bound: " + v.getBound());
		System.out.println("profit: " + v.getProfit());
		System.out.println("weight: " + v.getWeight());
		System.out.println("--");
		PQ.add(v);				//Insert root to PQ	
		while(!PQ.isEmpty()) { 
			PQ.remove();	//deque
			
			if(v.getBound() > maxprofit) {
				//Remove node from front priority and assign it to v, visit left child
				u.setLevel(v.getLevel() + 1);
				u.setProfit(v.getProfit() + items.get(u.getLevel()).getProfit());
				u.setWeight(v.getWeight() + items.get(u.getLevel()).getWeight());
				u.setBound(bound(u));
				System.out.println("bound: " + u.getBound());
				System.out.println("profit: " + u.getProfit());
				System.out.println("weight: " + u.getWeight());
				System.out.println("--");
				if(u.getWeight() <= W && u.getProfit() > maxprofit) {
					maxprofit = u.getProfit();	//40
				}
				// Insert u(1,1) to PQ
				if(u.getBound() > maxprofit) {
					PQ.add(u);
				}
				
				//Visit unvisited right child
				u.setLevel(v.getLevel()+1);;
				u.setWeight(v.getWeight());
				u.setProfit(v.getProfit());
				u.setBound(bound(u));
				System.out.println("bound: " + u.getBound());
				System.out.println("profit: " + u.getProfit());
				System.out.println("weight: " + u.getWeight());
				System.out.println("--");
				//Is bound promising?
				if(u.getBound() > maxprofit) {
					PQ.add(u);
					System.out.println(PQ.toString() + " |");
					
				}
				
			}
		}
			// ONLY DOES FIRST LEVEL

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
