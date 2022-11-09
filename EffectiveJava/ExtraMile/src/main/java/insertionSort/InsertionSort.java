package insertionSort;

public class InsertionSort {

    public static void sort(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            int holder = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > holder) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = holder;
        }
    }
}
