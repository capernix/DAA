import java.util.*;

public class SetCover {

    static class Subset {
        Set<Integer> elements;
        int cost;

        Subset(Set<Integer> elements, int cost) {
            this.elements = elements;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        Set<Integer> universe = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));

        List<Subset> subsets = new ArrayList<>();
        subsets.add(new Subset(new HashSet<>(Arrays.asList(4, 1, 3)), 5)); // S1
        subsets.add(new Subset(new HashSet<>(Arrays.asList(2, 5)), 10)); // S2
        subsets.add(new Subset(new HashSet<>(Arrays.asList(1, 4, 3, 2)), 3)); // S3

        List<List<Subset>> covers = findSetCovers(universe, subsets);

        System.out.println("The possible set covers are:");
        int minCost = Integer.MAX_VALUE;
        for (List<Subset> cover : covers) {
            int cost = 0;
            for (Subset subset : cover) {
                cost += subset.cost;
            }
            if (cost < minCost) {
                minCost = cost;
            }
            System.out.print("{");
            for (int i = 0; i < cover.size(); i++) {
                System.out.print("S" + (subsets.indexOf(cover.get(i)) + 1));
                if (i < cover.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("} cost " + cost);
        }

        System.out.println("Minimum cost of set cover is " + minCost);
    }

    private static List<List<Subset>> findSetCovers(Set<Integer> universe, List<Subset> subsets) {
        List<List<Subset>> result = new ArrayList<>();
        findSetCoversHelper(universe, subsets, new ArrayList<>(), result);
        return result;
    }

    private static void findSetCoversHelper(Set<Integer> universe, List<Subset> subsets, List<Subset> currentCover,
            List<List<Subset>> result) {
        Set<Integer> covered = new HashSet<>();
        for (Subset subset : currentCover) {
            covered.addAll(subset.elements);
        }
        if (covered.containsAll(universe)) {
            result.add(new ArrayList<>(currentCover));
            return;
        }

        for (Subset subset : subsets) {
            if (!currentCover.contains(subset)) {
                currentCover.add(subset);
                findSetCoversHelper(universe, subsets, currentCover, result);
                currentCover.remove(subset);
            }
        }
    }
}