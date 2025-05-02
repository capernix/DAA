import java.util.*;

public class MaxFlow {

    private static int minValue(int x, int y) {
        return Math.min(x, y);
    }

    private static boolean bfs(int[][] residualGraph, int start, int end, int[] prev, int totalVertices) {
        boolean[] visited = new boolean[totalVertices];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;
        prev[start] = -1;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next = 0; next < totalVertices; next++) {
                if (!visited[next] && residualGraph[current][next] > 0) {
                    queue.add(next);
                    prev[next] = current;
                    visited[next] = true;

                    if (next == end) return true;
                }
            }
        }
        return false;
    }

    public static int computeMaxFlow(int[][] capacityGraph, int source, int sink, int totalVertices) {
        int[][] residualGraph = new int[totalVertices][totalVertices];

        for (int i = 0; i < totalVertices; i++)
            residualGraph[i] = Arrays.copyOf(capacityGraph[i], totalVertices);

        int[] predecessor = new int[totalVertices];
        int maxFlow = 0;

        while (bfs(residualGraph, source, sink, predecessor, totalVertices)) {
            int flow = Integer.MAX_VALUE;

            for (int v = sink; v != source; v = predecessor[v]) {
                int u = predecessor[v];
                flow = minValue(flow, residualGraph[u][v]);
            }

            for (int v = sink; v != source; v = predecessor[v]) {
                int u = predecessor[v];
                residualGraph[u][v] -= flow;
                residualGraph[v][u] += flow;
            }

            maxFlow += flow;
        }

        return maxFlow;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int nodes = input.nextInt();

        System.out.print("Enter number of edges: ");
        int links = input.nextInt();

        int[][] network = new int[nodes][nodes];

        System.out.println("Enter edges (From To Capacity):");
        for (int i = 0; i < links; i++) {
            int from = input.nextInt();
            int to = input.nextInt();
            int cap = input.nextInt();
            network[from][to] += cap;
        }

        System.out.print("Enter source vertex: ");
        int src = input.nextInt();

        System.out.print("Enter sink vertex: ");
        int dest = input.nextInt();

        int result = computeMaxFlow(network, src, dest, nodes);
        System.out.println("\nThe maximum possible flow is: " + result);
    }
}