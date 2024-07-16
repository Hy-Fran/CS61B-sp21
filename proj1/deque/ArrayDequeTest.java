package deque;

import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
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

        ArrayDeque<String>  arrayDeque1 = new ArrayDeque<String>();
        ArrayDeque<Double>  arrayDeque2 = new ArrayDeque<Double>();
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
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, arrayDeque.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, arrayDeque.removeLast());

    }

    @Test
    public void iteratorTest(){
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        Iterator<Integer> iterator = list.iterator();
        int i = 1;
        while (iterator.hasNext()){
            int item = iterator.next();
            assertEquals(item, i);
            i++;
        }
    }

    @Test
    public void listEquals(){
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
    public void testResize(){
        ArrayDeque<Integer> list = new ArrayDeque<>(2);
        list.addLast(2);
        list.addLast(2);
        list.addLast(2);
        list.addLast(2);
        list.addLast(2);
        list.addLast(2);
    }
}
