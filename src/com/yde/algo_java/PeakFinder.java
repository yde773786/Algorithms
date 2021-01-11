package com.yde.algo_java;
import java.util.Scanner;

/**
 * MIT 6.006 Introduction to Algorithms, Fall 2011;
 * <p>
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
            int[] ans_2 = updatedTwoDimPeakFinder(mat, 0, mat[0].length - 1);
            endTime = System.nanoTime();
            System.out.println("Peak Found:  (" + ans_2[0] + ", " + ans_2[1] + ")" + "\n" +
                    "Elapsed Time: " + (endTime - startTime));

        }
    }

    /**
     *Runs over the array until an element is found that
     * satisfies the conditions for peak.
     *
     * @param row Input one dimensional array
     * @return  Required peak
     */
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

    /**
     * Starts from midpoint of array. If an element on either
     * side of the middle element is greater than it, a peak
     * is guaranteed to be present on that half of the current
     * array. Otherwise, the middle itself is the peak. Continue
     * recursively with the target half.
     *
     * @param row Input one dimensional array
     * @param start Beginning point of current array
     * @param end End point of current array
     * @return  Required peak
     */
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

    /**
     * Runs over the 2d array until an element is found that
     * satisfies the conditions for peak.
     *
     * @param mat 2 dimensional array
     * @return Required peak
     */
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
                    && mat[temp_r][temp_c] > mat[c_r][c_c]
                    && (i == 0 || j == 0)) {// Removing this line makes a true peak finder,
                        return false;       // Checking top right, top left, bottom right and
                                            // bottom left as well.
                    }
                }
            }
        }

        return true;
    }

    /**
     * Finds the maximum element in the middle column of the current 2d array.
     * Checks if the elements adjacent (horizontally) to this maximum in the
     * middle column are greater than it. If one is found, it is guaranteed
     * that there would be a peak in that half. Continue recursively with the
     * target half.
     *
     * @param mat 2 dimensional array
     * @param start_col Beginning column of current 2 dimensional array
     * @param end_col End column of current 2 dimensional array
     * @return  Required peak
     */
    private static int[] updatedTwoDimPeakFinder(int[][] mat, int start_col, int end_col) {
        int mid_col = (end_col - start_col + 1) / 2;

        if(end_col - start_col > 1){
            int mid_max = columnMaximum(mat, mid_col);
            if(mat[mid_max][mid_col] < mat[mid_max][mid_col - 1])
                updatedTwoDimPeakFinder(mat, start_col, mid_col);
            else if(mat[mid_max][mid_col] < mat[mid_max][mid_col + 1])
                updatedTwoDimPeakFinder(mat, mid_col, end_col);
            else
                return new int[]{mid_max, mid_col};
        }
        int start_max = columnMaximum(mat, start_col);
        int end_max = columnMaximum(mat, end_col);

        return mat[start_max][start_col] >= mat[start_max][end_col]
        ? new int[]{start_max, start_col} : new int[]{end_max, end_col};
    }

    private static int columnMaximum(int[][] mat, int col){
        int max = 0;

        if(mat.length > 1) {
            for (int i = 1; i < mat.length; i++) {
                if (mat[i][col] > mat[max][col])
                    max = i;
            }
        }

        return max;
    }
}
