package binarySearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinarySearchTest {

    @Test
    public void testIterativeBinarySearch() {

        //GIVEN

        //WHEN

        //THEN
        assertEquals(5, BinarySearch.iterativeBinarySearch(new int[]{1, 2, 3, 4, 5, 8}, 8));
        assertEquals(0, BinarySearch.iterativeBinarySearch(new int[]{1, 5, 6, 7, 8,}, 1));
        assertEquals(1, BinarySearch.iterativeBinarySearch(new int[]{0, 5, 6, 8, 15}, 5));
        assertEquals(-1, BinarySearch.iterativeBinarySearch(new int[]{}, 1));
    }

    @Test
    public void testRecursiveBinarySearch() {
        assertEquals(2, BinarySearch.recursiveBinarySearch(new int[]{1, 1, 1, 1}, 1));
        assertEquals(0, BinarySearch.recursiveBinarySearch(new int[]{1, 1, 2, 1}, 1));
        assertEquals(3, BinarySearch.recursiveBinarySearch(new int[]{1, 1, 0, 1}, 1));
        assertEquals(-1, BinarySearch.recursiveBinarySearch(new int[]{2, 2, 2, 2, 2, 2, 2, 2}, 1));
    }
}

