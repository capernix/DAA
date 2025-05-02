import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubsetSumFinder {

    public static boolean subsetSum(List<Integer> nums, int target, List<Integer> subset, int index) {
        if (target == 0) {
            System.out.print("Subset found: ");
            for (int num : subset) {
                System.out.print(num + " ");
            }
            System.out.println();
            return true;
        }

        if (index == nums.size() || target < 0) {
            return false;
        }

        int currentNum = nums.get(index);
        subset.add(currentNum);
        boolean include = subsetSum(nums, target - currentNum, subset, index + 1);

        subset.remove(subset.size() - 1);

        boolean exclude = subsetSum(nums, target, subset, index + 1);

        return include || exclude;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, target;

        System.out.print("Enter number of elements: ");
        n = scanner.nextInt();

        List<Integer> nums = new ArrayList<>();
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; ++i) {
            nums.add(scanner.nextInt());
        }

        System.out.print("Enter target sum: ");
        target = scanner.nextInt();

        List<Integer> subset = new ArrayList<>();

        if (!subsetSum(nums, target, subset, 0)) {
            System.out.println("No subset with the given sum found.");
        }

        scanner.close();
    }
}
