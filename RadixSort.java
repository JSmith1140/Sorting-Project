import java.util.*;

public class RadixSort {
    // A utility function to get maximum value in arr[]
    static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    // A utility function to get maximum length in arr[]
    static int getMaxStringLength(String arr[], int n) {
        int mx = arr[0].length();
        for (int i = 1; i < n; i++)
            if (arr[i].length() > mx)
                mx = arr[i].length();
        return mx;
    }

    // A utility function to get maximum value in double arr[]
    static double getMax(double arr[], int n) {
        double mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current
        // digit
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp for strings
    static void countSortString(String arr[], int n, int exp) {
        String output[] = new String[n]; // output array
        int i;
        int count[] = new int[256]; // Considering ASCII characters
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[arr[i].length() < exp ? 0 : (arr[i].charAt(arr[i].length() - exp))]++;
        
        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 256; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[arr[i].length() < exp ? 0 : (arr[i].charAt(arr[i].length() - exp))] - 1] = arr[i];
            count[arr[i].length() < exp ? 0 : (arr[i].charAt(arr[i].length() - exp))]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current
        // digit
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    // The main function to that sorts arr[] of
    // size n using Radix Sort
    static void radixsort(int arr[], int n) {
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);

        // Do counting sort for every digit. Note that
        // instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    // The main function to that sorts arr[] of
    // size n using Radix Sort for strings
    static void radixsortString(String arr[], int n) {
        // Find the maximum length to know number of digits
        int m = getMaxStringLength(arr, n);

        // Do counting sort for every digit. Note that
        // instead of passing digit number, exp is passed.
        // exp is 1 where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSortString(arr, n, exp);
    }

    // The main function to that sorts arr[] of
    // size n using Radix Sort for doubles
    static void radixsort(double arr[], int n) {
        // Find the maximum number to know number of digits
        double m = getMax(arr, n);

        // Convert double array to integer array
        int[] intArr = new int[n];
        for (int i = 0; i < n; i++) {
            intArr[i] = (int) (arr[i] * 1000000); // Scale up by 1,000,000 for precision
        }

        // Do counting sort for every digit. Note that
        // instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(intArr, n, exp);

        // Convert back to double array
        for (int i = 0; i < n; i++) {
            arr[i] = intArr[i] / 1000000.0; // Scale down to original range
        }
    }

    // A utility function to print an array
    static void print(int arr[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }

    // A utility function to print an array of strings
    static void printString(String arr[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }

    public static void main(String[] args) {
        // Array sizes to test
        int[] arraySizes = { 10, 100, 1000, 10000, 50000, 100000, 500000 };

        // Number of repetitions for each array size
        int repetitions = 5;

        // Repeat sorting for each array size
        for (int sizeIndex = 0; sizeIndex < arraySizes.length; sizeIndex++) {
            int arrSize = arraySizes[sizeIndex];
            double[] timesInt = new double[repetitions];
            double[] timesString = new double[repetitions];
            double[] timesDouble = new double[repetitions];

            // Repeat sorting process to calculate average, min, and max times for integers
            for (int i = 0; i < repetitions; i++) {
                // Generate random array of integers
                int[] arrInt = new int[arrSize];
                Random rand = new Random();
                for (int j = 0; j < arrSize; j++) {
                    arrInt[j] = rand.nextInt(1000000); // Random numbers up to 1,000,000
                }

                // Start measuring time
                long startTimeInt = System.nanoTime();

                // Perform radix sort for integers
                radixsort(arrInt, arrSize);

                // Stop measuring time
                long endTimeInt = System.nanoTime();

                // Calculate time taken in milliseconds as a double
                double durationInt = (endTimeInt - startTimeInt) / 1_000_000.0; // convert nanoseconds to milliseconds
                timesInt[i] = durationInt;
            }

            // Repeat sorting process to calculate average, min, and max times for strings
            for (int i = 0; i < repetitions; i++) {
                // Generate random array of strings
                String[] arrString = new String[arrSize];
                Random rand = new Random();
                for (int j = 0; j < arrSize; j++) {
                    arrString[j] = generateRandomString(); // Generate random strings
                }

                // Start measuring time
                long startTimeString = System.nanoTime();

                // Perform radix sort for strings
                radixsortString(arrString, arrSize);

                // Stop measuring time
                long endTimeString = System.nanoTime();

                // Calculate time taken in milliseconds as a double
                double durationString = (endTimeString - startTimeString) / 1_000_000.0; // convert nanoseconds to milliseconds
                timesString[i] = durationString;
            }

            // Repeat sorting process to calculate average, min, and max times for doubles
            for (int i = 0; i < repetitions; i++) {
                // Generate random array of doubles
                double[] arrDouble = new double[arrSize];
                Random rand = new Random();
                for (int j = 0; j < arrSize; j++) {
                    arrDouble[j] = rand.nextDouble(); // Random doubles between 0.0 and 1.0
                }

                // Start measuring time
                long startTimeDouble = System.nanoTime();

                // Perform radix sort for doubles
                radixsort(arrDouble, arrSize);

                // Stop measuring time
                long endTimeDouble = System.nanoTime();

                // Calculate time taken in milliseconds as a double
                double durationDouble = (endTimeDouble - startTimeDouble) / 1_000_000.0; // convert nanoseconds to milliseconds
                timesDouble[i] = durationDouble;
            }

            // Calculate maximum, minimum, and average times for integers
            Arrays.sort(timesInt);
            double sumInt = 0;
            for (double time : timesInt) {
                sumInt += time;
            }
            double averageTimeInt = sumInt / repetitions;
            double minTimeInt = timesInt[0];
            double maxTimeInt = timesInt[repetitions - 1];

            // Calculate maximum, minimum, and average times for strings
            Arrays.sort(timesString);
            double sumString = 0;
            for (double time : timesString) {
                sumString += time;
            }
            double averageTimeString = sumString / repetitions;
            double minTimeString = timesString[0];
            double maxTimeString = timesString[repetitions - 1];

            // Calculate maximum, minimum, and average times for doubles
            Arrays.sort(timesDouble);
            double sumDouble = 0;
            for (double time : timesDouble) {
                sumDouble += time;
            }
            double averageTimeDouble = sumDouble / repetitions;
            double minTimeDouble = timesDouble[0];
            double maxTimeDouble = timesDouble[repetitions - 1];

            // Print statistics for integers
            System.out.printf("For array size %d (Integers):\n", arrSize);
            System.out.printf("Minimum time: %.6f milliseconds\n", minTimeInt);
            System.out.printf("Maximum time: %.6f milliseconds\n", maxTimeInt);
            System.out.printf("Average time: %.6f milliseconds\n\n", averageTimeInt);

            // Print statistics for strings
            System.out.printf("For array size %d (Strings):\n", arrSize);
            System.out.printf("Minimum time: %.6f milliseconds\n", minTimeString);
            System.out.printf("Maximum time: %.6f milliseconds\n", maxTimeString);
            System.out.printf("Average time: %.6f milliseconds\n\n", averageTimeString);


            // Print statistics for doubles
            System.out.printf("For array size %d (Doubles):\n", arrSize);
            System.out.printf("Minimum time: %.6f milliseconds\n", minTimeDouble);
            System.out.printf("Maximum time: %.6f milliseconds\n", maxTimeDouble);
            System.out.printf("Average time: %.6f milliseconds\n\n", averageTimeDouble);
        }
    }

    // Function to generate a random string of length 10
    static String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
