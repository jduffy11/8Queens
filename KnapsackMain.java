
public class KnapsackMain {
	
	static int maxVal(int x, int y) {
		return (x>y) ? x : y;
	}
	
	static int knapsack(int Weight, int wt[], int val[], int j) {
		if (j == 0 || Weight == 0) {
			return 0;
		}
		if (wt[j - 1] > Weight) {
			return knapsack(Weight, wt, val, j - 1);
		} else {
			return maxVal(val[j - 1] + knapsack(Weight - wt[j - 1], wt, val, j - 1), knapsack(Weight, wt, val, j - 1));
		}
	}
	

	public static void main(String[] args) {
		int val[] = new int[] {30, 50, 80};
		int wt[] = new int[] {2,5,10};
		int Weight = 20;
		int j = val.length;
		System.out.println(knapsack(Weight, wt, val, j));

	}

}
