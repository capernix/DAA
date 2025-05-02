import java.util.Scanner;

public class BipartiteMatcher {

    static int leftSetSize, rightSetSize;
    static int[][] adjMatrix = new int[100][100]; 
    static int[] pairRight; 
    static boolean[] seen;  

    static boolean findMatch(int leftNode) {
        for (int rightNode = 0; rightNode < rightSetSize; rightNode++) {
            if (adjMatrix[leftNode][rightNode] == 1 && !seen[rightNode]) {
                seen[rightNode] = true;

                if (pairRight[rightNode] == -1 || findMatch(pairRight[rightNode])) {
                    pairRight[rightNode] = leftNode;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices in Set A: ");
        leftSetSize = sc.nextInt();

        System.out.print("Enter number of vertices in Set B: ");
        rightSetSize = sc.nextInt();

        // Initialize adjacency matrix
        for (int i = 0; i < leftSetSize; i++) {
            for (int j = 0; j < rightSetSize; j++) {
                adjMatrix[i][j] = 0;
            }
        }

        pairRight = new int[100];
        for (int i = 0; i < rightSetSize; i++) {
            pairRight[i] = -1;
        }

        System.out.print("Enter number of edges between Set A and Set B: ");
        int edgeCount = sc.nextInt();

        System.out.println("Enter edges (a b):");
        for (int i = 0; i < edgeCount; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adjMatrix[a][b] = 1;
        }

        int totalMatches = 0;
        seen = new boolean[100];

        for (int a = 0; a < leftSetSize; a++) {
            for (int i = 0; i < rightSetSize; i++) {
                seen[i] = false;
            }

            if (findMatch(a)) {
                totalMatches++;
            }
        }

        System.out.println("\nMaximum number of matches: " + totalMatches);
        System.out.println("Matchings:");
        for (int b = 0; b < rightSetSize; b++) {
            if (pairRight[b] != -1) {
                System.out.println("A" + pairRight[b] + " - B" + b);
            }
        }
    }
}
