
/*
 * Heap Sort for Integers
 * Author: Jacob Smith
 */
import java.util.Random;

public class HeapSortIntegers {
  public static void sort(int arr[]) {
    int N = arr.length;

    for (int i = N / 2 - 1; i >= 0; i--)
      findHeap(arr, N, i);

    for (int i = N - 1; i > 0; i--) {

      int temp = arr[0];
      arr[0] = arr[i];
      arr[i] = temp;

      findHeap(arr, i, 0);
    }
  }

  static void findHeap(int arr[], int N, int i) {
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;

    if (l < N && arr[l] > arr[largest])
      largest = l;

    if (r < N && arr[r] > arr[largest])
      largest = r;

    if (largest != i) {
      int swap = arr[i];
      arr[i] = arr[largest];
      arr[largest] = swap;

      findHeap(arr, N, largest);
    }
  }

  public static void main(String[] args) {
    int[] sizes = { 10, 100, 1000, 10000, 50000, 100000, 500000 };

    Random random = new Random();
    System.out.println("Sorted Heap Sort");
    for (int size : sizes) {
      double min = Double.MAX_VALUE;
      double max = Double.MIN_VALUE;
      double total = 0;

      for (int i = 0; i < sizes.length; i++) {
        int[] arr = new int[size];
        for (int j = 0; j < size; j++) {
          arr[j] = random.nextInt();
        }

        long start = System.nanoTime();
        sort(arr);
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

    System.out.println("UnSorted Heap Sort");
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
        sort(sizes);
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
