package insertionSort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsertionSortTest {

    @Test
    public void testSort() {
        int[] array = {};
        InsertionSort.sort(array);
        assertEquals(Arrays.toString(new int[]{}), Arrays.toString(array));
    }

    @Test
    public void testSort2() {

        int[] array = {6, 3, 1, 5, 2, 4, 7};
        InsertionSort.sort(array);
        assertEquals(Arrays.toString(new int[]{1, 2, 3, 4, 5, 6, 7}), Arrays.toString(array));
    }
}

