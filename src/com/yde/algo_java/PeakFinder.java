
package com.yde.algo_java;
import java.util.Scanner;

/**
 * MIT 6.006 Introduction to Algorithms, Fall 2011;
 * Coverage of Peak-finder, and comparing algorithms by complexity.
 * (Lecture 1)
 */
public class PeakFinder {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("PEAK FINDER:\n" +
                "Press 1: 1 dimensional peak finder comparison of Θ(n) and Θ(log n)\n" +
                "Press 2: 2 dimensional peak finder comparison of Θ(nm) and Θ(n log m)");
        int choice = in.nextInt();

        if(choice == 1){
            System.out.println("Enter number of elements in row");
            int n = in.nextInt();

            int[] row = new int[n];
            for(int i = 0 ; i < n; i++){
                System.out.println("Enter element " + i);
                row[i] = in.nextInt();
            }

            long startTime = System.nanoTime();
            int ans_1 = simpleOneDimPeakFinder(row);
            long endTime = System.nanoTime();
            System.out.println("Peak Found: " + ans_1 + "\n" +
                    "Elapsed Time: " + (endTime - startTime));

            startTime = System.nanoTime();
            int ans_2 = updatedOneDimPeakFinder(row, 0, row.length - 1);
            endTime = System.nanoTime();
            System.out.println("Peak Found: " + ans_2 + "\n" +
                    "Elapsed Time: " + (endTime - startTime));
        }
        else if(choice == 2){
            System.out.println("Enter number of rows");
            int n = in.nextInt();
            System.out.println("Enter number of columns");
            int m = in.nextInt();

            int[][] mat = new int[n][m];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    System.out.println("Enter element (" + i + "," + j + ")");
                    mat[i][j] = in.nextInt();
                }
            }

            long startTime = System.currentTimeMillis();
            int[] ans_1 = simpleTwoDimPeakFinder(mat);
            long endTime = System.nanoTime();
            System.out.println("Peak Found:  (" + ans_1[0] + ", " + ans_1[1] + ")" + "\n" +
                    "Elapsed Time: " + (endTime - startTime));

            startTime = System.currentTimeMillis();
            int[] ans_2 = updatedTwoDimPeakFinder(mat);
            endTime = System.nanoTime();
            System.out.println("Peak Found:  (" + ans_2[0] + ", " + ans_2[1] + ")" + "\n" +
                    "Elapsed Time: " + (endTime - startTime));

        }
    }

    private static int simpleOneDimPeakFinder(int[] row) {
        if(row.length > 1){
            if(row[0] >= row[1])
                return 0;
            for (int i = 1; i < row.length - 1; i++) {
                if (row[i - 1] <= row[i] && row[i + 1] <= row[i])
                    return i;
            }
            return row.length - 1;
        }
        return 0;
    }

    private static int updatedOneDimPeakFinder(int[] row, int start, int end) {
        int mid = (end + start + 1) / 2;
        if(end - start > 1) {
            if(row[mid] < row[mid - 1])
                return updatedOneDimPeakFinder(row, start, mid - 1);
            else if(row[mid] < row[mid + 1])
                return updatedOneDimPeakFinder(row, mid + 1, end);
            else
                return mid;
        }
        return row[end] > row[start] ? end : start;
    }

    private static int[] simpleTwoDimPeakFinder(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(checkPeak(i, j, mat))
                    return new int[]{i, j};
            }
        }

        return new int[]{0, 0};
    }

    private static boolean checkPeak(int c_r, int c_c, int[][] mat) {
        for(int i = -1; i <= 1; i++){
            int temp_r = c_r + i;
            if(0 <= temp_r && temp_r <= mat.length - 1) {
                for (int j = -1; j <= 1; j++) {
                    int temp_c = c_c + j;
                    if(0 <= temp_c && temp_c <= mat[0].length - 1
                    && mat[temp_r][temp_c] > mat[c_r][c_c]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static int[] updatedTwoDimPeakFinder(int[][] mat) {
        return new int[]{0, 0};
    }
}
