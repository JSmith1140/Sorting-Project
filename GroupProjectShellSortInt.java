import java.io.*;
import java.util.Scanner;

class GroupProjectShellSortInt {
    /* An utility function to print array of size n*/
    static void printArray(int arr[], int n) {
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    /* function to sort arr using shellSort */
    static void sort(int arr[], int n) {
        // Sort the array
        int gap, i, j, temp;
        for (gap = n / 2; gap > 0; gap /= 2) {
            for (i = gap; i < n; i += 1) {
                temp = arr[i];
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];
                arr[j] = temp;
            }
        }
    }

    // Driver method
    public static void main(String args[]) {
        int[] arraySizes = {10,}; // Different array sizes
        int numRuns = 5; // Number of runs for calculating average, max, and min

        for (int size : arraySizes) {
            double[] runTimes1 = new double[numRuns];
            double[] runTimes2 = new double[numRuns];

            for (int i = 0; i < numRuns; i++) {
                try {
                    // Open the first file
                    Scanner scanner1 = new Scanner(new File("numbers.txt"));
                    int[] arr1 = new int[size]; // Array size
                    int index1 = 0;
                    while (scanner1.hasNextInt() && index1 < arr1.length) {
                        int nextInt = scanner1.nextInt();
                        if (Math.random() < (double) size / (index1 + 1)) {
                            arr1[index1++] = nextInt;
                        }
                    }
                    scanner1.close();
                    int n1 = index1; // Actual number of elements read from the first file

                    // Open the second file
                    Scanner scanner2 = new Scanner(new File("numberssorted.txt"));
                    int[] arr2 = new int[size]; // Array size
                    int index2 = 0;
                    while (scanner2.hasNextInt() && index2 < arr2.length) {
                        int nextInt = scanner2.nextInt();
                        if (Math.random() < (double) size / (index2 + 1)) {
                            arr2[index2++] = nextInt;
                        }
                    }
                    scanner2.close();
                    int n2 = index2; // Actual number of elements read from the second file

                    // Sorting and calculating time for first array
                    long startTime1 = System.nanoTime();
                    sort(arr1, n1);
                    long endTime1 = System.nanoTime();
                    double duration1 = (endTime1 - startTime1) / 1_000_000.0; // converting nanoseconds to milliseconds
                    runTimes1[i] = duration1;

                    // Sorting and calculating time for second array
                    long startTime2 = System.nanoTime();
                    sort(arr2, n2);
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
