package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

public class TestList {

    @Test
    public void testThreeAddThreeRemove(){
        BuggyAList<Integer> buggyAList = new BuggyAList<>();
        AListNoResizing<Integer> rightList = new AListNoResizing<>();
        buggyAList.addLast(1);
        buggyAList.addLast(2);
        buggyAList.addLast(3);
        rightList.addLast(1);
        rightList.addLast(2);
        rightList.addLast(3);
        Assert.assertEquals(buggyAList.getLast(), rightList.getLast());
        buggyAList.removeLast();
        rightList.removeLast();
        Assert.assertEquals(buggyAList.getLast(), rightList.getLast());
        buggyAList.removeLast();
        rightList.removeLast();
        Assert.assertEquals(buggyAList.getLast(), rightList.getLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                broken.addLast(randVal);
                L.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                Assert.assertEquals(broken.size(), size);
            } else if (operationNumber == 2) {
                if (L.size() == 0){
                    continue;
                }
                int last = L.getLast();
                Assert.assertEquals(last, (int)broken.getLast());
            } else if (operationNumber == 3) {
                if (L.size() == 0){
                    continue;
                }
                int lastValue = L.removeLast();
                Assert.assertEquals(lastValue, (int)broken.removeLast());
            }
        }
    }
}
