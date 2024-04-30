import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class MergeSortInt {

    // Merge Sort Implementation
    public static void mergeSort(BigInteger[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    // Merge step of Merge Sort
    private static void merge(BigInteger[] arr, int low, int mid, int high) {
        int leftLength = mid - low + 1;
        int rightLength = high - mid;

        BigInteger[] leftArr = new BigInteger[leftLength];
        BigInteger[] rightArr = new BigInteger[rightLength];

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
    public static BigInteger[] generateSortedSequence(int n) {
        BigInteger[] arr = new BigInteger[n];
        for (int i = 0; i < n; i++) {
            arr[i] = BigInteger.valueOf(i); // Generate sorted sequence
        }
        return arr;
    }

    // Function to measure execution time
    public static double measureTime(BigInteger[] arr) {
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
                    BigInteger[] sortedData = generateSortedSequence(size);
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


