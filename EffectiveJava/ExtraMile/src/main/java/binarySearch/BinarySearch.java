package binarySearch;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class BinarySearch {

    // iterative searching
    public static int iterativeBinarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        int middle;
        while (left <= right) {
            middle = (left + right) / 2;
            if (array[middle] == target) return middle;
            else if (target < array[middle])
                right = middle - 1;
            else left = middle + 1;
        }
        return -1;
    }

    // recursive searching
    public static int recursiveBinarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length;
        return recursiveBinarySearch(array, target, left, right);
    }

    private static int recursiveBinarySearch(int[] array, int target, int left, int right) {

        int middle = (left + right) / 2;
        if (left > right)
            return -1;
        if (array[middle] == target)
            return middle;
        else if (array[middle] < target)
            return recursiveBinarySearch(array, target, middle + 1, right);
        else
            return recursiveBinarySearch(array, target, left, middle - 1);
    }
}
