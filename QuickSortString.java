import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class QuickSortString {

    // Quick Sort Implementation
    // This method sorts the input array using the Quick Sort algorithm
    public static void quickSort(String[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Partitioning step of Quick Sort
    // This method selects a pivot element, partitions the array around the pivot,
    // and then returns the index of the pivot element after partitioning
    private static int partition(String[] arr, int low, int high) {
        String pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;

                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Function to generate sorted sequences
    // This method generates a sorted sequence of strings
    public static String[] generateSortedSequence(int n) {
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = String.valueOf(i); // Generate sorted sequence
        }
        return arr;
    }

    // Function to measure execution time
    // This method measures the execution time of the quickSort method on the input array
    public static double measureTime(String[] arr) {
        long startTime = System.nanoTime();
        quickSort(arr, 0, arr.length - 1);
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

