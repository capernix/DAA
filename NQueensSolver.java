import java.util.Scanner;
import java.util.Arrays;

public class NQueensSolver {

    public static void printBoard(char[][] board, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(new String(board[i]));
        }
        System.out.println();
    }

    public static boolean isSafe(int row, int col, char[][] board, int n) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    public static void solve(int row, char[][] board, int n) {
        if (row == n) {
            printBoard(board, n);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col, board, n)) {
                board[row][col] = 'Q';
                solve(row + 1, board, n);
                board[row][col] = '.';
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;

        System.out.print("Enter the value of N (chessboard size): ");
        n = scanner.nextInt();

        if (n <= 0) {
             System.out.println("N must be a positive integer.");
             scanner.close();
             return;
        }

        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        solve(0, board, n);

        scanner.close();
    }
}
