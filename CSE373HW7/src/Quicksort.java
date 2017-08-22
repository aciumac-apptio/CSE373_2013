// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

import java.util.Arrays;

public class Quicksort {
    // lengths of arrays to test (input size N)
    private static final int[] LENGTHS = {
            10, 20, 40, 80, 160, 320, 640, 1000, 2000, 4000, 8000, 16000, 32000, 64000,
            128000, 256000, 512000, 1000000, 2000000, 4000000, 8000000, 16000000
    };

    public static void main(String[] args) {
        for (int N : LENGTHS) {
             // String[] a = ArrayMethods.createAscendingStringArray(N);
             //String[] a = ArrayMethods.createDescendingStringArray(N);
             String[] a = ArrayMethods.createRandomStringArray(N);

            // perform a sort and time how long it takes
            long startTime1 = System.currentTimeMillis();
            quickSort(a);
            long endTime1 = System.currentTimeMillis();

            // make sure the array is sorted afterward
            if (!ArrayMethods.isSorted(a)) {
                throw new RuntimeException("not sorted afterward: " + Arrays.toString(a));
            }

            // print runtime stats
            System.out.printf("%10d elements  =>  %6d ms \n", N, endTime1 - startTime1);
        }
    }

    // Rearranges elements of the array into sorted order using quick sort algorithm,
    // by dividing the array into two partitions based on which elements are less than and greater than
    // the pivot
    public static void quickSort(String[] a) {
        quickSort(a, 0, a.length-1);
    }

    // Recursive helper for quickSort that sorts the aray from
    // [start...end] inclusive
    private static void quickSort(String[] a, int start, int end) {
        if (start < end) {
            if(end + 1 - start <= 100) {
                insertionSort(a, start, end);
            } else {
                // pick a "pivot" value (median of 3)
                String pivot = median(a, start, end);
                // partition: left: < pivot,  right: > pivot
                int pIndex = partition(a, start, end, pivot);
                // move pivot back to middle
                swap(a, end, pIndex);

                // sort the two partitions
                quickSort(a, start, pIndex - 1);
                quickSort(a, pIndex + 1, end);
            }
        }
    }

    // Rearranges the values in the array into two partitions where elements that are less than are put
    // to the left of the pivot value, greater than to the right
    // Returns the index at which the pivot value should be located
    private static int partition(String[] a, int start, int end, String pivot) {
        int i = start;
        int j = end - 1;
        while (i <= j) {
            while (i <= j && a[i].compareTo(pivot) < 0) {
                i++;
            }
            while (i <= j && a[j].compareTo(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                swap(a, i, j);
                i++;
                j--;
            }
        }
        return i;
    }

    // Swaps two values at given indices of the given array
    private static void swap(String[] a, int i, int j) {
        // Indices are not out of bounds
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Returns a median string between the left most, right most and center strings
    private static String median(String[] a, int start, int end) {
        int center = (start + end)/2;

        if (a[center].compareTo(a[start]) < 0) {
            swap(a,start,center );
        }

        if (a[end].compareTo(a[start]) < 0) {
            swap(a, start, end);
        }

        if (a[end].compareTo(a[center]) < 0) {
            swap(a,center, end );
        }
        // Rotate median to the end
        swap(a,center, end);
        return a[end];
    }

    // Arranges elements into sorted order using "insertion sort" algorithm
    // Builds a sorted array from the start by removing the value at a given index
    // and shifting values of the sorted array until the value can be inserted into
    // ordered place in the "sorted" section of the array
    private static void insertionSort(String[] a, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            String value = a[i];
            int j = i;
            while (j > start && a[j-1].compareTo(value) > 0 ) {
                // shift over
                a[j] = a[j-1];
                j--;
            }
            a[j] = value;
        }
    }

    /* // Partition for youtube algorithm
    private static int partition (String [] a, int start, int end) {
        // Find median of 3
        median(a, start, end);
        //swap(a, (start + end + )/2, end);

        String pivot = a[end];
        int pIndex = start;
        for (int i = start; i <= end-1; i++) {
            if (a[i].compareTo(pivot) <= 0) {
                swap(a, i, pIndex);
                pIndex++;
            }
        }

        swap(a, pIndex,end);
        return pIndex;
    }*/

/*    private static void quickSort(String[] a, int start, int end) {
        // Ok
        if (start < end) {
            if (end + 1 - start <= 100) {
                insertionSort(a, start, a.length - 1);
            } else {
                int pIndex = partition(a, start, end);
                quickSort(a, start, pIndex - 1);
                quickSort(a, pIndex+1, end);
            }
        }
    }*/
}