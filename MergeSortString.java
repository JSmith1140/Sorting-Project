import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MergeSortString {

    // Merge Sort Implementation
    public static void mergeSort(String[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    // Merge step of Merge Sort
    private static void merge(String[] arr, int low, int mid, int high) {
        int leftLength = mid - low + 1;
        int rightLength = high - mid;

        String[] leftArr = new String[leftLength];
        String[] rightArr = new String[rightLength];

        // Copy data to temporary arrays
        System.arraycopy(arr, low, leftArr, 0, leftLength);
        System.arraycopy(arr, mid + 1, rightArr, 0, rightLength);

        int i = 0, j = 0, k = low;

        // Merge the temporary arrays back into arr
        while (i < leftLength && j < rightLength) {
            if (leftArr[i].compareTo(rightArr[j]) <= 0) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArr[], if any
        while (i < leftLength) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArr[], if any
        while (j < rightLength) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    // Function to generate sorted sequences
    public static String[] generateSortedSequence(int n) {
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = String.valueOf(i); // Generate sorted sequence
        }
        return arr;
    }

    // Function to measure execution time
    public static double measureTime(String[] arr) {
        long startTime = System.nanoTime();
        mergeSort(arr, 0, arr.length - 1);
        long endTime = System.nanoTime();
        double elapsedTimeMilliseconds = (endTime - startTime) / 1_000_000.0; // Convert nanoseconds to milliseconds
        return elapsedTimeMilliseconds;
    }

    public static void main(String[] args) {
        int numRuns = 5;
        int[] sizes = {10, 100, 1000, 10000, 15000, 16000, 16250};

        // Loop through different input sizes
        for (int size : sizes) {
            System.out.println("Testing for size: " + size);
            try (BufferedReader br = new BufferedReader(new FileReader("Strings.txt"))) {
                String line = br.readLine();
                String[] data = line.trim().split("\\s+");

                double totalTime = 0;
                double minTime = Double.MAX_VALUE;
                double maxTime = Double.MIN_VALUE;
                // Run the sorting algorithm multiple times to get average performance
                for (int run = 0; run < numRuns; run++) {
                    String[] sortedData = generateSortedSequence(size);
                    double time = measureTime(sortedData);
                    totalTime += time;
                    minTime = Math.min(minTime, time);
                    maxTime = Math.max(maxTime, time);
                }
                double avgTime = totalTime / numRuns;
                // Print out the minimum, average, and maximum sorting times
                System.out.println("Minimum time: " + minTime + " milliseconds");
                System.out.println("Average time: " + avgTime + " milliseconds");
                System.out.println("Maximum time: " + maxTime + " milliseconds");
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

