package com.yde.algo_java;
import java.util.Arrays;
import java.util.Scanner;

public class Sort {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);


        System.out.println("Enter length of array to be sorted");
        int n = in.nextInt();
        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            System.out.println("Enter number at index " + i);
            arr[i] = in.nextInt();
        }

        System.out.println("Chose sorting technique:\n" +
                "1. Selection Sort\n" +
                "2. Bubble Sort\n" +
                "3. Insertion Sort\n" +
                "4. Insertion Binary Sort\n" +
                "5. Merge Sort\n" +
                "6. Quick Sort\n" +
                "7. Heap Sort");

        int choice = in.nextInt();
        switch(choice){
            case 1: selectionSort(arr);
                    break;
            case 2: bubbleSort(arr);
                    break;
            case 3: insertionSort(arr);
                break;
            case 4: insertionBinarySort(arr);
                break;
            case 5: mergeSort(arr, 0, arr.length - 1);
                break;
            case 6: quickSort(arr, 0, arr.length - 1);
                break;
            case 7: heapsort(arr, arr.length - 1);
                break;
            default:
                System.out.println("No choice");
        }

        System.out.println(Arrays.toString(arr));

    }

    private static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length; i++){
            int min = i;
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    private static void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length - 1 - i; j++){
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void insertionSort(int[] arr) {
        for(int i = 1; i < arr.length; i++){
            for(int j = i - 1; j >= 0; j--){
                if(arr[j + 1] < arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void insertionBinarySort(int[] arr) {
        for(int i = 1; i < arr.length; i++){
            int target_pos = binarySearchLocator(arr, i, 0, i - 1);
            int insert = arr[i];
            if (i - target_pos >= 0) System.arraycopy(arr, target_pos, arr, target_pos + 1,
                    i - target_pos);
            arr[target_pos] = insert;
        }
    }

    private static int binarySearchLocator(int[] arr, int index, int start, int end) {
        int mid = (start + end) / 2;
        if(end - start >= 2){
            if(arr[mid - 1] <= arr[index] && arr[mid + 1] <= arr[index])
                return binarySearchLocator(arr, index, mid + 1, end);
            else if(arr[mid - 1] >= arr[index] && arr[mid + 1] >= arr[index])
                return binarySearchLocator(arr, index, start, mid - 1);

            return arr[index] < arr[mid] ? mid : mid + 1;
        }
        else
            if(arr[index] <= arr[start])
                return start;
            else if(arr[index] >= arr[end])
                return end + 1;
            else
                return end;
    }

    private static void mergeSort(int[] arr, int start, int end) {
        if(start < end){
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int pointerA = start;
        int pointerB = mid + 1;
        int pointerTemp = 0;
        int[] tempArr = new int[end - start + 1];

        while(pointerA <= mid || pointerB <= end){
            if(pointerB > end){
                tempArr[pointerTemp] = arr[pointerA];
                pointerA++;
            }
            else if(pointerA > mid){
                tempArr[pointerTemp] = arr[pointerB];
                pointerB++;
            }
            else{
                if(arr[pointerA] < arr[pointerB]){
                    tempArr[pointerTemp] = arr[pointerA];
                    pointerA++;
                }
                else{
                    tempArr[pointerTemp] = arr[pointerB];
                    pointerB++;
                }
            }
            pointerTemp++;
        }

        if (end + 1 - start >= 0) System.arraycopy(tempArr, 0, arr, start, end + 1 - start);
    }

    private static void quickSort(int[] arr, int start, int end) {
        if(end > start){
            int pivot = arr[end];
            int lesserPointer = start;
            for(int i = start; i < end; i++){
                if(arr[i] < pivot){
                    int tmp = arr[lesserPointer];
                    arr[lesserPointer] = arr[i];
                    arr[i] = tmp;
                    lesserPointer++;
                }
            }
            int tmp = arr[lesserPointer];
            arr[lesserPointer] = arr[end];
            arr[end] = tmp;
            quickSort(arr, start, lesserPointer - 1);
            quickSort(arr, lesserPointer + 1, end);
        }
    }

    private static void heapsort(int[] arr, int workingLength){
        int[] sortedArr = new int[arr.length];
        int cnt = 0;
        while(workingLength > 1){
            buildMinHeap(arr, workingLength);
            sortedArr[cnt] = arr[0];
            cnt++;
            int temp = arr[0];
            arr[0] = arr[workingLength];
            arr[workingLength--] = temp;
        }
        int least = arr[0];
        int most = arr[1];
        if(least > most){
            least = arr[1];
            most = arr[0];
        }
        sortedArr[cnt++] = least;
        sortedArr[cnt] = most;
        System.arraycopy(sortedArr, 0, arr, 0, sortedArr.length);
    }

    private static void buildMinHeap(int[] arr, int workingLength){
        for(int i = (workingLength) / 2; i >= 0; i--){
            mapHeapify(arr, i, workingLength);
        }
    }

    private static void mapHeapify(int[] arr, int p, int workingLength) {
        if(p < (workingLength) / 2) {
            int rightNode;
            if (2 * p + 2 > workingLength) {
                rightNode = 2 * p + 1;
            } else
                rightNode = 2 * p + 2;

            int minIndex = arr[2 * p + 1] < arr[rightNode] ? 2 * p + 1 : rightNode;

            if (arr[p] > arr[minIndex]) {
                int tmp = arr[minIndex];
                arr[minIndex] = arr[p];
                arr[p] = tmp;
                mapHeapify(arr, minIndex, workingLength);
            }
        }
    }

}
