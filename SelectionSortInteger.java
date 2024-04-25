
/*
 * Selection Sorting for Integers
 * Author: Jacob Smith
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SelectionSortInteger {
    static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min])
                    min = j;

            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    public static void main(String[] args) {
        int[] sizes = { 10, 100, 1000, 10000, 50000, 100000, 500000 };

        System.out.println("Sorted Selection Sort");
        for (int size : sizes) {
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            double total = 0;

            for (int i = 0; i < sizes.length; i++) {
                int[] arr = new int[size];
                try (BufferedReader br = new BufferedReader(new FileReader("Numbers.txt"))) {
                    String line;
                    int index = 0;
                    while ((line = br.readLine()) != null && index < size) {
                        if (!line.trim().isEmpty()) { // Check if the line is not empty
                            String[] numbers = line.split("\\s+");
                            for (String number : numbers) {
                                if (index < size && !number.trim().isEmpty()) {
                                    arr[index++] = Integer.parseInt(number.trim());
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Arrays.sort(arr); // sorts the Array

                long start = System.nanoTime();
                selectionSort(arr);
                long end = System.nanoTime();
                double duration = (end - start) / 100_000_000.0;
                total += duration;
                min = Math.min(min, duration);
                max = Math.max(max, duration);
            }
            double average = total / sizes.length;

            System.out.println("Size: " + size);
            System.out.printf("Min: %.5f ms%n", min);
            System.out.printf("Average: %.5f ms%n", average);
            System.out.printf("Max: %.5f ms%n", max);
            System.out.println();
        }

        System.out.println("UnSorted Selection Sort");
        for (int size : sizes) {
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            double total = 0;
            for (int i = 0; i < sizes.length; i++) {
                int[] arr = new int[size];
                try (BufferedReader br = new BufferedReader(new FileReader("Numbers.txt"))) {
                    String line;
                    int index = 0;
                    while ((line = br.readLine()) != null && index < size) {
                        if (!line.trim().isEmpty()) { // Check if the line is not empty
                            String[] numbers = line.split("\\s+");
                            for (String number : numbers) {
                                if (index < size && !number.trim().isEmpty()) { // Check if the number is not empty
                                    arr[index++] = Integer.parseInt(number.trim());
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                long start = System.nanoTime();
                selectionSort(arr);
                long end = System.nanoTime();
                double duration = (end - start) / 100_000_000.0;
                total += duration;
                min = Math.min(min, duration);
                max = Math.max(max, duration);
            }
            double average = total / sizes.length;

            System.out.println("Size: " + size);
            System.out.printf("Min: %.5f ms%n", min);
            System.out.printf("Average: %.5f ms%n", average);
            System.out.printf("Max: %.5f ms%n", max);
            System.out.println();
        }
    }
}
