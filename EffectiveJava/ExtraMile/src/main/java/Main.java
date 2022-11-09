import binarySearch.BinarySearch;
import insertionSort.InsertionSort;
import mergeSort.MergeSort;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        org.openjdk.jmh.Main.main(args);

        int[] nums = {1, 2, 3, 4, 6, 7, 8, 34, 46, 65, 66, 78, 87, 88, 990, 8765};
        int target = 34;
        System.out.println(BinarySearch.recursiveBinarySearch(nums, target));
        System.out.println(BinarySearch.iterativeBinarySearch(nums, target));

        int[] array = {3, 5, 21, 4, 6, 23, 5, 6, 3, 1, 7, 64, 34, 65, 76, 3, 1};
        MergeSort.mergesort(array);
        System.out.println(Arrays.toString(array));
        InsertionSort.sort(array);
        System.out.println(Arrays.toString(array));
    }
}
