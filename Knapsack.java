import java.util.*;

class Block {
    int wt, val;
    double ratio;

    public Block(int wt, int val) {
        this.wt = (wt == 0) ? 1 : wt;
        this.val = val;
        this.ratio = (double) val / this.wt;
    }
}

public class Knapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the knapsack capacity: ");
        int capacity = scanner.nextInt();

        Comparator<Block> maxHeapComparator = (a, b) -> Double.compare(b.ratio, a.ratio);
        PriorityQueue<Block> pq = new PriorityQueue<>(maxHeapComparator);

        for (int i = 0; i < 10; i++) {  // Using 10 items for clear output
            int randomWt = (int)(Math.random() * 50) + 1; // Weight between 1 and 50
            int randomVal = (int)(Math.random() * 100) + 1; // Value between 1 and 100
            pq.add(new Block(randomWt, randomVal));
        }

        int currentWeight = 0;
        double totalValue = 0.0;

        System.out.println("\nSelected items:");
        while (!pq.isEmpty() && currentWeight < capacity) {
            Block b = pq.poll();
            if (currentWeight + b.wt <= capacity) {
                currentWeight += b.wt;
                totalValue += b.val;
                System.out.println("Profit is: " + b.val + ", Weight is: " + b.wt + " Ratio = 1");
            } else {
                int remain = capacity - currentWeight;
                double fraction = (double) remain / b.wt;
                totalValue += b.val * fraction;
                System.out.println("Profit is: " + b.val + ", Weight is: " + b.wt + " Ratio = " + remain + "/" + b.wt);
                break;
            }
        }

        System.out.println("\nMaximum profit: " + totalValue);
        scanner.close();
    }
}
