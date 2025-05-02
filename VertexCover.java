import java.util.*;

public class VertexCover {

    static class Graph {
        int V;
        List<List<Integer>> adjList;

        public Graph(int V) {
            this.V = V;
            adjList = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        public boolean isValidCover(Set<Integer> cover) {
            for (int u = 0; u < V; u++) {
                for (int v : adjList.get(u)) {
                    if (!cover.contains(u) && !cover.contains(v)) {
                        return false;
                    }
                }
            }
            return true;
        }

        public Set<Integer> findMinimumVertexCover() {
            Set<Integer> minCover = null;
            int minSize = Integer.MAX_VALUE;

            for (int i = 0; i < (1 << V); i++) {
                Set<Integer> cover = new HashSet<>();
                for (int j = 0; j < V; j++) {
                    if ((i & (1 << j)) != 0) {
                        cover.add(j);
                    }
                }

                if (isValidCover(cover) && cover.size() < minSize) {
                    minCover = cover;
                    minSize = cover.size();
                }
            }

            return minCover;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        Set<Integer> minCover = graph.findMinimumVertexCover();
        System.out.println("Minimum Vertex Cover: " + minCover);
    }
}