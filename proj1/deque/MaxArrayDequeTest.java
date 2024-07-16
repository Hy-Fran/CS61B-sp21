package deque;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

public class MaxArrayDequeTest {

    @Test
    public void testMax() {
        Comparator<Integer> realValueComparator = (item1, item2) -> item1.compareTo(item2);

        MaxArrayDeque<Integer> maxArrayDeque = new MaxArrayDeque<>(realValueComparator);
        maxArrayDeque.addLast(1);
        maxArrayDeque.addLast(2);
        maxArrayDeque.addLast(-1);
        maxArrayDeque.addLast(-2);
        maxArrayDeque.addLast(4);
        maxArrayDeque.addLast(-5);
        Assert.assertEquals(4, maxArrayDeque.max().intValue());

        Comparator<Integer> absoluteValueComparator = (item1, item2) -> Integer.compare(Math.abs(item1), Math.abs(item2));

        Assert.assertEquals(-5, maxArrayDeque.max(absoluteValueComparator).intValue());
    }
}
