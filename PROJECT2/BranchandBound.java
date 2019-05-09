package Project2;
import java.util.*;
import java.util.PriorityQueue;

/**
 * When each algorithm has found a solution, print out
 * which items are taken and which are not, what the maximum profit is, and how many
 * nodes were checked.
 */

public class BranchandBound {

	public static int nodesChecked = 0;
	public static double W;						// Max weight of bag
	public static double maxProfit = 0;			// Greatest profit
	public static double[] p;						// Profit array
	public static double[] w;						// Weight array
	
	public static void main(String[] args) {
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		PQ.add(new Node(50, 10));
		PQ.add(new Node(30, 5));
		PQ.add(new Node(40, 2));
		PQ.add(new Node(10, 5));
		
		p = new double[4];
		w = new double[4];
		
		int i = 0;
		while(!PQ.isEmpty()) {
			Node node1 = PQ.poll();
		
			p[i] = node1.getProfit();
			System.out.println("p " + (i+1) + " element " + p[i]);
			w[i] = node1.getWeight();
			System.out.println("w " + (i+1) + " element " + w[i]);
			
			System.out.println(node1.toString());
			System.out.println("--------------------------");
			
	
			i++;
		}
	
		System.out.println("P ARRAY CONTENTS: ");
		for(Double pArray: p) {
			System.out.print(pArray.toString() + " ");
		}
		System.out.println("\nW ARRAY CONTENTS: ");
		for(Double wArray: w) {
			System.out.print(wArray.toString() + " ");
		}
		
		knapsack3(0, p, w, 16 );
		System.out.println();
		System.out.println(maxProfit);
		
	}
	public static void knapsack3(int n, double[] p, double[] w, int W) {
		PriorityQueue<Node> PQ = new PriorityQueue<>(); //Declare and initialize PQ
		Node u = new Node(p[0],w[0]);
		Node v = new Node();
		
		v.level = 0;			//Initialize v to the root
		v.profit = 0;			//Initialize v to the root
		v.weight = 0;			//Initialize v to the root
		v.bound = bound(v);		//Compute v bound
		PQ.add(v);				//Insert root to PQ
		
		while(!PQ.isEmpty())
			PQ.remove(v);
		
		if(v.bound > maxProfit) {
			//Remove node fromt front priority and assign it to v, visit left child
			u.level = v.level + 1;
			u.weight = v.weight + w[u.level];
			u.profit = v.profit + p[u.level];
			u.bound = bound(u);
			
			if(u.weight <= W && u.profit > maxProfit)
				maxProfit = u.profit;
			// Insert u to PQ
			if(u.bound > maxProfit)
				PQ.add(u);
			u.weight = v.weight;
			u.profit = v.profit;
			u.bound = bound(u);

			if(u.bound > maxProfit)
				PQ.add(u);
		}
	}
	
	public static double bound(Node u) {
		int j;
		int k;
		double totweight;
		double result;
		
		if(u.weight >= W)
			return 0;
		else {
			result = (float) u.profit;
			j = u.level + 1;
			totweight = u.weight;
			while( j <= (w.length) && totweight + w[j] <= W) {
				totweight = totweight +w[j];
				result = result + p[j];
				j++;
			}
		}
		k = j;
		if( k <= (w.length))
			result = (result + (W- totweight) * p[k] / w[k]);
		
		return result;
			
	}//end bound
	
	
}
