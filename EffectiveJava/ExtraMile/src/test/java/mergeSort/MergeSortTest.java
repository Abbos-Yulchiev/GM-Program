package mergeSort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeSortTest {


    @Test
    public void testMergesort() {
        int[] array = {};
        MergeSort.mergesort(array);
        assertEquals(Arrays.toString(new int[]{}), Arrays.toString(array));
    }

    @Test
    public void testMergesort2() {
        int[] array = {6, 3, 1, 5, 2, 4, 7};
        MergeSort.mergesort(array);
        assertEquals(Arrays.toString(new int[]{1, 2, 3, 4, 5, 6, 7}), Arrays.toString(array));
    }
}

