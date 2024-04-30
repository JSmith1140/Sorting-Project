import java.io.*;
import java.util.Scanner;

class GroupProjectBubbleSortInt {
    // Integer Sort
    
    static void bubbleSort(int arr[], int n) {
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped by inner loop, then break
            if (!swapped)
                break;
        }
    }

    // Function to print
    static void printArray(int arr[], int size) {
        int i;
        for (i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        int numRuns = 6; // Number of runs for calculating average and max
        int[] arraySizes = {10, };
        
        for (int size : arraySizes) {
            double[] runTimes1 = new double[numRuns];
            double[] runTimes2 = new double[numRuns];

            for (int i = 0; i < numRuns; i++) {
                try {
                    Scanner scanner1 = new Scanner(new File("numbers.txt"));
                    int[] arr1 = new int[size];
                    int index1 = 0;
                    while (scanner1.hasNextInt() && index1 < arr1.length) {
                        arr1[index1++] = scanner1.nextInt();
                    }
                    scanner1.close();
                    int n1 = index1;

                    Scanner scanner2 = new Scanner(new File("numberssorted.txt"));
                    int[] arr2 = new int[size];
                    int index2 = 0;
                    while (scanner2.hasNextInt() && index2 < arr2.length) {
                        arr2[index2++] = scanner2.nextInt();
                    }
                    scanner2.close();
                    int n2 = index2;

                    long startTime1 = System.nanoTime();
                    bubbleSort(arr1, n1);
                    long endTime1 = System.nanoTime();
                    double duration1 = (endTime1 - startTime1) / 1_000_000.0;
                    runTimes1[i] = duration1;

                    long startTime2 = System.nanoTime();
                    bubbleSort(arr2, n2);
                    long endTime2 = System.nanoTime();
                    double duration2 = (endTime2 - startTime2) / 1_000_000.0;
                    runTimes2[i] = duration2;

                } catch (FileNotFoundException e) {
                    System.err.println("File not found: " + e.getMessage());
                }
            }

            // Calculate average, min, and max for file 1
            double sum1 = 0;
            double min1 = runTimes1[0];
            double max1 = runTimes1[0];
            for (double time : runTimes1) {
                sum1 += time;
                if (time < min1) {
                    min1 = time;
                }
                if (time > max1) {
                    max1 = time;
                }
            }
            double average1 = sum1 / numRuns;

            // Calculate average, min, and max for file 2
            double sum2 = 0;
            double min2 = runTimes2[0];
            double max2 = runTimes2[0];
            for (double time : runTimes2) {
                sum2 += time;
                if (time < min2) {
                    min2 = time;
                }
                if (time > max2) {
                    max2 = time;
                }
            }
            double average2 = sum2 / numRuns;

            // Print results
            System.out.println("Size: " + size);
            System.out.println("File 1:");
            System.out.println("   Average time: " + average1 + " ms");
            System.out.println("   Max run time: " + max1 + " ms");
            System.out.println("   Min run time: " + min1 + " ms");

            System.out.println("File 2:");
            System.out.println("   Average time: " + average2 + " ms");
            System.out.println("   Max run time: " + max2 + " ms");
            System.out.println("   Min run time: " + min2 + " ms");
            System.out.println();
        }
    }
}