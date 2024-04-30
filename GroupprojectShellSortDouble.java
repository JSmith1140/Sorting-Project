import java.util.Arrays;
import java.io.*;
import java.util.Scanner;

class GroupProjectShellSortDouble {
    /* An utility function to print array of size n*/
    static void printArray(double arr[], int n) {
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    /* function to sort arr using shellSort */
    static void sort(double arr[], int n) {
        // Start with a big gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < n; i += 1) {
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at
                // position i
                double temp = arr[i];

                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];

                // put temp (the original a[i]) in its correct
                // location
                arr[j] = temp;
            }
        }
    }

    // Driver method
    public static void main(String args[]) {
        int[] arraySizes = {10, }; // Different array sizes
        int numRuns = 5; // Number of runs for calculating average and max

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

            // Calculate and print average, max, and min for each file
            double sum1 = 0, sum2 = 0;
            double max1 = runTimes1[0], max2 = runTimes2[0];
            double min1 = runTimes1[0], min2 = runTimes2[0];

            for (double time : runTimes1) {
                sum1 += time;
                if (time > max1) max1 = time;
                if (time < min1) min1 = time;
            }

            for (double time : runTimes2) {
                sum2 += time;
                if (time > max2) max2 = time;
                if (time < min2) min2 = time;
            }

            double average1 = sum1 / numRuns;
            double average2 = sum2 / numRuns;

            System.out.println("Array size: " + size);
            System.out.println("Average time for file 1: " + average1 + " ms");
            System.out.println("Max run time for file 1: " + max1 + " ms");
            System.out.println("Min run time for file 1: " + min1 + " ms");
            System.out.println("Average time for file 2: " + average2 + " ms");
            System.out.println("Max run time for file 2: " + max2 + " ms");
            System.out.println("Min run time for file 2: " + min2 + " ms");
            System.out.println();
        }
    }
}
