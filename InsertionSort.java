import java.util.*;

class InsertionSort {
    // Function to perform insertion sort for integers
    static void insertionSortInt(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // Function to perform insertion sort for strings
    static void insertionSortString(String arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            String key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // Function to perform insertion sort for doubles
    static void insertionSortDouble(double arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            double key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // A utility function to print an array of integers
    static void printIntArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // A utility function to print an array of strings
    static void printStringArray(String arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // A utility function to print an array of doubles
    static void printDoubleArray(double arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String args[]) {
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

                // Perform insertion sort for integers
                insertionSortInt(arrInt);

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
                    arrString[j] = generateRandomString(); // Generating random strings
                }

                // Start measuring time
                long startTimeString = System.nanoTime();

                // Perform insertion sort for strings
                insertionSortString(arrString);

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
                    arrDouble[j] = rand.nextDouble() * 1000000; // Random doubles up to 1,000,000
                }

                // Start measuring time
                long startTimeDouble = System.nanoTime();

                // Perform insertion sort for doubles
                insertionSortDouble(arrDouble);

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
            System.out.println("For array size " + arrSize + " (Integers):");
            System.out.println("Minimum time: " + minTimeInt + " milliseconds");
            System.out.println("Maximum time: " + maxTimeInt + " milliseconds");
            System.out.println("Average time: " + averageTimeInt + " milliseconds\n");

            // Print statistics for strings
            System.out.println("For array size " + arrSize + " (Strings):");
            System.out.println("Minimum time: " + minTimeString + " milliseconds");
            System.out.println("Maximum time: " + maxTimeString + " milliseconds");
            System.out.println("Average time: " + averageTimeString + " milliseconds\n");

            // Print statistics for doubles
            System.out.println("For array size " + arrSize + " (Doubles):");
            System.out.println("Minimum time: " + minTimeDouble + " milliseconds");
            System.out.println("Maximum time: " + maxTimeDouble + " milliseconds");
            System.out.println("Average time: " + averageTimeDouble + " milliseconds\n");
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
