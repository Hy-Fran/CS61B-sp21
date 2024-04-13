package game2048;

import org.junit.Test;
import org.junit.Assert;

public class TestMoveUpNeedSteps {

    static Side side = Side.NORTH;

    static Model model;

    @Test
    public void test(){
        int[][] rawVals = new int[][] {
                {2, 4, 2, 4},
                {0, 0, 2, 0},
                {0, 8, 2, 4},
                {4, 0, 2, 0},
        };

        model = new Model(rawVals, 0, 0, false);

        Assert.assertEquals(2,getStep(0,0));

        Assert.assertEquals(-1, getStep(0, 1));

        Assert.assertEquals(0, getStep(0, 3));


        Assert.assertEquals(1, getStep(1,1));

        Assert.assertEquals(2, getStep(3,1));

        Assert.assertEquals(1,getStep(2, 1));

        Assert.assertEquals(0,getStep(2, 3));
    }

    public int getStep(int col, int row){
        return model.tileMoveUpNeedSteps(side, col, row, model.tile(col, row));
    }

}
