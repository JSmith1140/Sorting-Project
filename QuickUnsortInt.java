import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class QuickUnsortInt {

    // Quick Sort Implementation
    // This method sorts the input array using the Quick Sort algorithm
    public static void quickSort(BigInteger[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Partitioning step of Quick Sort
    // This method selects a pivot element, partitions the array around the pivot,
    // and then returns the index of the pivot element after partitioning
    private static int partition(BigInteger[] arr, int low, int high) {
        BigInteger pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;

                BigInteger temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        BigInteger temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Function to generate unsorted sequences
    // This method generates an unsorted sequence of integers from 0 to n-1
    public static BigInteger[] generateUnsortedSequence(int n) {
        BigInteger[] arr = new BigInteger[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = BigInteger.valueOf(rand.nextInt()); // Generate unsorted sequence
        }
        return arr;
    }

    // Function to measure execution time
    // This method measures the execution time of the quickSort method on the input array
    public static double measureTime(BigInteger[] arr) {
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
            try (BufferedReader br = new BufferedReader(new FileReader("Numbers.txt"))) {
                String line = br.readLine();
                String[] numbers = line.trim().split("\\s+");
                BigInteger[] data = new BigInteger[numbers.length];
                int dataIndex = 0;
                for (String number : numbers) {
                    try {
                        data[dataIndex++] = new BigInteger(number);
                    } catch (NumberFormatException e) {
                        // Silently handle NumberFormatException without printing error messages
                    }
                }

                double totalTime = 0;
                double minTime = Double.MAX_VALUE;
                double maxTime = Double.MIN_VALUE;
                // Run the sorting algorithm multiple times to get average performance
                for (int run = 0; run < numRuns; run++) {
                    BigInteger[] unsortedData = generateUnsortedSequence(size);
                    double time = measureTime(unsortedData);
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

