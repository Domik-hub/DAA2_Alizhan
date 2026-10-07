import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataStructuresTest {

    @Test
    void testDynamicArray() {
        DynamicArray da = new DynamicArray();
        da.add(10);
        da.add(20);
        da.add(30);

        assertEquals(10, da.get(0));
        assertEquals(20, da.get(1));
        assertEquals(30, da.get(2));

        da.add(1, 15);
        assertEquals(15, da.get(1));
        assertEquals(20, da.get(2));

        assertEquals(15, da.remove(1));
        assertEquals(20, da.get(1));

        assertTrue(da.contains(20));
        assertFalse(da.contains(99));

        assertThrows(IndexOutOfBoundsException.class, () -> da.get(10));
        assertThrows(IndexOutOfBoundsException.class, () -> da.remove(-1));
    }

    @Test
    void testMyLinkedList() {
        MyLinkedList list = new MyLinkedList();
        list.add(5);
        list.add(15);
        list.add(25);

        assertEquals(5, list.get(0));
        assertEquals(15, list.get(1));
        assertEquals(25, list.get(2));

        list.add(1, 10);
        assertEquals(10, list.get(1));

        assertEquals(10, list.remove(1));
        assertEquals(15, list.get(1));

        assertTrue(list.contains(25));
        assertFalse(list.contains(100));

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(10, 1));
    }

    @Test
    void testMinHeap() {
        MinHeap heap = new MinHeap(5);
        heap.insert(40);
        heap.insert(10);
        heap.insert(30);
        heap.insert(20);

        assertEquals(10, heap.peekMin());

        assertEquals(10, heap.extractMin());
        assertEquals(20, heap.extractMin());
        assertEquals(30, heap.extractMin());
        assertEquals(40, heap.extractMin());

        assertThrows(IllegalStateException.class, heap::extractMin);
        assertThrows(IllegalStateException.class, heap::peekMin);
    }

    @Test
    void testHeapPropertyAndSortedOutput() {
        MinHeap heap = new MinHeap(10);
        int[] values = {45, 12, 78, 3, 22, 19, 88, 1};
        for (int v : values) {
            heap.insert(v);
        }

        int prev = Integer.MIN_VALUE;
        while (heap.size() > 0) {
            int current = heap.extractMin();
            assertTrue(current >= prev);
            prev = current;
        }
    }
}