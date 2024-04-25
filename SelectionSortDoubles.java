
/*
 * Selection Sorting for Doubles
 * Author: Jacob Smith
 */
import java.util.Random;

public class SelectionSortDoubles {
    static void selectionSort(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min])
                    min = j;

            double temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    public static void main(String[] args) {
        int[] sizes = { 10, 100, 1000, 10000, 50000, 100000, 500000 };

        Random random = new Random();

        System.out.println("Sorted Selection Sort");
        for (int size : sizes) {
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            double total = 0;

            for (int i = 0; i < sizes.length; i++) {
                double[] arr = new double[size];
                for (int j = 0; j < size; j++) {
                    arr[j] = random.nextDouble();
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

        System.out.println("UnSorted Selection Sort");
        for (int size : sizes) {
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            double total = 0;

            for (int i = 0; i < sizes.length; i++) {
                double[] arr = new double[size];
                for (int j = 0; j < size; j++) {
                    arr[j] = random.nextDouble();
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
