package mergeSort;

public class MergeSort {

    public static void mergesort(int[] array) {
        mergesort(array, 0, array.length - 1);
    }

    private static void mergesort(int[] array, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergesort(array, start, mid);
            mergesort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }

    private static void merge(int[] array, int start, int mid, int end) {

        int len1 = mid - start + 1;
        int len2 = end - mid;
        int[] left = new int[len1];
        int[] right = new int[len2];

        for (int i = 0; i < len1; ++i)
            left[i] = array[start + i];
        for (int i = 0; i < len2; ++i)
            right[i] = array[mid + i + 1];

        int index1 = 0;
        int index2 = 0;
        int index = start;
        while (index1 < len1 && index2 < len2) {
            if (left[index1] <= right[index2]) {
                array[index++] = left[index1++];
            } else {
                array[index++] = right[index2++];
            }
        }
        while (index1 < len1) {
            array[index++] = left[index1++];
        }
        while (index2 < len2) {
            array[index++] = right[index2++];
        }
    }
}
