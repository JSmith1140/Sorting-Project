import java.io.*;
import java.util.Scanner;

class GroupProjectBubbleSortDouble {
    // Double Sort
    static void bubbleSort(double arr[], int n) {
        int i, j;
        double temp;
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

            // If no two elements were
            // swapped by inner loop, then break
            if (!swapped)
                break;
        }
    }

    // Function to print
    static void printArray(double arr[], int size) {
        for (int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver program
    public static void main(String args[]) {
        int[] arraySizes = {10, };
        int numRuns = 5; // Number of runs for calculating average, max, and min

        for (int size : arraySizes) {
            double[] runTimes1 = new double[numRuns];
            double[] runTimes2 = new double[numRuns];

            for (int i = 0; i < numRuns; i++) {
                try {
                    // Open the first file
                    Scanner scanner1 = new Scanner(new File("doubles.txt"));
                    double[] arr1 = new double[size]; // Array size
                    int index1 = 0;
                    while (scanner1.hasNextDouble() && index1 < arr1.length) {
                        double nextDouble = scanner1.nextDouble();
                        if (Math.random() < (double) size / (index1 + 1)) {
                            arr1[index1++] = nextDouble;
                        }
                    }
                    scanner1.close();
                    int n1 = index1; // Actual number of elements read from the first file

                    // Open the second file
                    Scanner scanner2 = new Scanner(new File("doublessorted.txt"));
                    double[] arr2 = new double[size]; // Array size
                    int index2 = 0;
                    while (scanner2.hasNextDouble() && index2 < arr2.length) {
                        double nextDouble = scanner2.nextDouble();
                        if (Math.random() < (double) size / (index2 + 1)) {
                            arr2[index2++] = nextDouble;
                        }
                    }
                    scanner2.close();
                    int n2 = index2; // Actual number of elements read from the second file

                    // Sorting and calculating time for first array
                    long startTime1 = System.nanoTime();
                    bubbleSort(arr1, n1);
                    long endTime1 = System.nanoTime();
                    double duration1 = (endTime1 - startTime1) / 1_000_000.0; // converting nanoseconds to milliseconds
                    runTimes1[i] = duration1;

                    // Sorting and calculating time for second array
                    long startTime2 = System.nanoTime();
                    bubbleSort(arr2, n2);
                    long endTime2 = System.nanoTime();
                    double duration2 = (endTime2 - startTime2) / 1_000_000.0; // converting nanoseconds to milliseconds
                    runTimes2[i] = duration2;

                } catch (FileNotFoundException e) {
                    System.err.println("File not found: " + e.getMessage());
                }
            }

            // Calculate and print average, max, and min for file 1
            double sum1 = 0;
            for (double time : runTimes1) {
                sum1 += time;
            }
            double average1 = sum1 / numRuns;

            double max1 = runTimes1[0];
            double min1 = runTimes1[0];
            for (double time : runTimes1) {
                if (time > max1) {
                    max1 = time;
                }
                if (time < min1) {
                    min1 = time;
                }
            }
            System.out.println("Array size: " + size);
            System.out.println("Average time for file 1: " + average1 + " ms");
            System.out.println("Max run time for file 1: " + max1 + " ms");
            System.out.println("Min run time for file 1: " + min1 + " ms");

            // Calculate and print average, max, and min for file 2
            double sum2 = 0;
            for (double time : runTimes2) {
                sum2 += time;
            }
            double average2 = sum2 / numRuns;

            double max2 = runTimes2[0];
            double min2 = runTimes2[0];
            for (double time : runTimes2) {
                if (time > max2) {
                    max2 = time;
                }
                if (time < min2) {
                    min2 = time;
                }
            }
            System.out.println("Average time for file 2: " + average2 + " ms");
            System.out.println("Max run time for file 2: " + max2 + " ms");
            System.out.println("Min run time for file 2: " + min2 + " ms");
            System.out.println();
        }
    }
}
