package deque;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.*;


/**
 * Performs some basic linked list tests.
 */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        ArrayDeque<String> arrayDeque = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", arrayDeque.isEmpty());
        arrayDeque.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, arrayDeque.size());
        assertFalse("lld1 should now contain 1 item", arrayDeque.isEmpty());

        arrayDeque.addLast("middle");
        assertEquals(2, arrayDeque.size());

        arrayDeque.addLast("back");
        assertEquals(3, arrayDeque.size());

        System.out.println("Printing out deque: ");
        arrayDeque.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", arrayDeque.isEmpty());

        arrayDeque.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", arrayDeque.isEmpty());

        arrayDeque.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", arrayDeque.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(3);

        arrayDeque.removeLast();
        arrayDeque.removeFirst();
        arrayDeque.removeLast();
        arrayDeque.removeFirst();

        int size = arrayDeque.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String> arrayDeque1 = new ArrayDeque<String>();
        ArrayDeque<Double> arrayDeque2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> arrayDeque3 = new ArrayDeque<Boolean>();

        arrayDeque1.addFirst("string");
        arrayDeque2.addFirst(3.14159);
        arrayDeque3.addFirst(true);

        String s = arrayDeque1.removeFirst();
        double d = arrayDeque2.removeFirst();
        boolean b = arrayDeque3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertNull("Should return null when removeFirst is called on an empty Deque,", arrayDeque.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", arrayDeque.removeLast());

    }

    @Test
    public void iteratorTest() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        Iterator<Integer> iter = list.iterator();
        int iterationRecord = 0;
        int expectedValue = 1;
        while (iter.hasNext()) {
            iterationRecord++;
            int item = iter.next();
            assertEquals(expectedValue, item);
            expectedValue++;
        }
        //迭代次数
        assertEquals(5, iterationRecord);

        ArrayDeque<Integer> list2 = new ArrayDeque<>();
        assertFalse(list2.iterator().hasNext());
        list2.addFirst(1);
        assertTrue(list2.iterator().hasNext());
    }

    @Test
    public void getTest(){
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addFirst(0);
        list.removeLast();
        list.addLast(4);
        list.removeFirst();
        Assert.assertEquals(1, list.get(0).intValue());
        Assert.assertEquals(2, list.get(1).intValue());
        Assert.assertEquals(3, list.get(2).intValue());
        Assert.assertEquals(4, list.get(3).intValue());
        ArrayDeque<Integer> list2 = new ArrayDeque<>();
        list2.addLast(0);
        Assert.assertEquals(0, list2.removeFirst().intValue());
        list2.addLast(2);
        Assert.assertEquals(2, list2.removeLast().intValue());
        list2.addLast(4);
        list2.addLast(5);
        list2.removeLast();
        Assert.assertEquals(4, list2.get(0).intValue());
    }

    @Test
    public void listEquals() {
        ArrayDeque<Integer> list1 = new ArrayDeque<>();
        list1.addLast(1);
        list1.addLast(2);
        list1.addLast(3);
        list1.addLast(4);
        ArrayDeque<Integer> equalList1 = new ArrayDeque<>();
        equalList1.addLast(2);
        equalList1.addLast(1);
        equalList1.addLast(4);
        equalList1.addLast(3);
        ArrayDeque<Integer> notEqualList1 = new ArrayDeque<>();
        notEqualList1.addLast(1);
        notEqualList1.addLast(2);
        notEqualList1.addLast(3);
        assertEquals(list1, equalList1);
        assertNotEquals(list1, notEqualList1);
        notEqualList1.addLast(5);
        notEqualList1.addLast(6);
        assertNotEquals(list1, notEqualList1);
    }

    @Test
    public void randomOperation() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 1145; i++) {
            int operation = new Random().nextInt(4);
            int item = new Random().nextInt(Integer.MAX_VALUE);
            switch (operation) {
                case 0:
                    deque.addFirst(item);
                    break;
                case 1:
                    deque.addLast(item);
                    break;
                case 2:
                    deque.removeFirst();
                    break;
                case 3:
                    deque.removeLast();
                    break;
            }
        }
    }

    @Test
    public void equalTest() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(1);
        list.removeFirst();
        list.addFirst(1);
        list.addLast(2);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        ArrayDeque<Integer> list2 = new ArrayDeque<>();
        list2.addLast(1);
        list2.removeFirst();
        list2.addLast(1);
        list2.addLast(2);
        list2.addLast(3);
        list2.addLast(4);
        list2.removeFirst();
        list2.addFirst(1);
        list2.addLast(5);
        list2.addLast(1);

        assertEquals(list, list2);
    }

    @Test
    public void testResize() {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addFirst(4);
        list.addFirst(5);
        list.addFirst(6);
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addLast(7);
        list.addLast(8);
        list.addFirst(0);
    }
}
