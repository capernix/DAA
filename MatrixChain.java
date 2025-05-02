import java.util.Scanner;

public class MatrixChain
{
    static char name;
    static void matrixChain(int[] p, int n){

        int[][] score = new int[n][n];
        int[][] dp = new int[n][n];

        for(int i = 1; i < n; i++){
            score[i][i] = 0;
        }

        for(int l = 2; l < n; l++){
            for(int i = 1; i < n - l + 1; i++){
                int j = i + l - 1;
                score[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++){
                    int q = score[i][k] + score[k + 1][j] + p[i - 1] * p[j] * p[k];

                    if( q < score[i][j]){
                        score[i][j] = q;
                        dp[i][j] = k;
                    }
                }
            }
        }
        System.out.println("\nThe Optimal Cost of the method is : " + score[1][n - 1]);

        //Print of the matrix chain table:
        System.out.println("\nMatrix Chain Score : ");

        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                System.out.print(score[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nK Table : ");
        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }

        name = 'A';
        System.out.print("Optimal Parenthesis is : ");
        printParenthesis(1, n - 1, n, dp);
    }

    static void printParenthesis(int i, int j, int n, int[][] dp){

        if(i == j){
            System.out.print(name++);
            return;
        }
        System.out.print("(");

        printParenthesis(i, dp[i][j], n, dp);
        printParenthesis(dp[i][j] + 1, j, n, dp);
        System.out.print(")");

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("No. of matrices : ");
        int matrix = sc.nextInt();
        int[] matrices = new int[matrix + 1];

        System.out.println("Enter the dimensions : ");
        for(int i = 0; i <= matrix; i++){
            matrices[i] = sc.nextInt();
        }
        matrixChain(matrices, matrix + 1);

        System.out.println();
        sc.close();
    }
}