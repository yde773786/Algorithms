package com.yde.algo;
import java.util.Scanner;

public class Lecture_1 {
    public static void main() {
        Scanner in = new Scanner(System.in);
        System.out.println("PEAK FINDER:\n" +
                "Press 1: 1 dimensional peak finder comparison of Θ(n) and Θ(log n)\n" +
                "Press 2: 2 dimensional peak finder comparison of Θ(nm) and Θ(n log m)");
        int choice = in.nextInt();

        System.out.println("Enter number of elements in row");
        int n = in.nextInt();

        if(choice == 1){
            int[] row = new int[n];
            for(int i = 0 ; i < n; i++){
                System.out.println("Enter element " + i);
                row[i] = in.nextInt();
            }

            long startTime = System.currentTimeMillis();
            int ans_1 = simpleOneDimPeakFinder(row);
            long endTime = System.currentTimeMillis();
            System.out.println("Peak Found: " + ans_1 + "\n" +
                    "Elapsed Time: " + (endTime - startTime));

            startTime = System.currentTimeMillis();
            int ans_2 = updatedOneDimPeakFinder(row);
            endTime = System.currentTimeMillis();
            System.out.println("Peak Found: " + ans_2 + "\n" +
                    "Elapsed Time: " + (endTime - startTime));
        }
        else if(choice == 2){
            System.out.println("Enter number of elements in column");
            int m = in.nextInt();
            int[][] mat = new int[n][m];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    System.out.println("Enter element (" + i + "," + j + ")");
                    mat[i][j] = in.nextInt();
                }
            }

            long startTime = System.currentTimeMillis();
            int ans_1 = simpleTwoDimPeakFinder(mat);
            long endTime = System.currentTimeMillis();
            System.out.println("Peak Found: " + ans_1 + "\n" +
                    "Elapsed Time: " + (endTime - startTime));

            startTime = System.currentTimeMillis();
            int ans_2 = updatedTwoDimPeakFinder(mat);
            endTime = System.currentTimeMillis();
            System.out.println("Peak Found: " + ans_2 + "\n" +
                    "Elapsed Time: " + (endTime - startTime));
        }
    }

    private static int simpleOneDimPeakFinder(int[] row) {
        return 0;
    }

    private static int updatedOneDimPeakFinder(int[] row) {
        return 0;
    }

    private static int simpleTwoDimPeakFinder(int[][] mat) {
        return 0;
    }

    private static int updatedTwoDimPeakFinder(int[][] mat) {
        return 0;
    }
}
