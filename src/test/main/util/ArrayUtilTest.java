package main.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jaric Liao on 2015/12/12.
 */
public class ArrayUtilTest {

    @Test
    public void testSelectTop() throws Exception {
        List<Integer> unsorted = new ArrayList<Integer>(Arrays.asList(5,6,2,1,4,3));
        List<Integer> result = ArrayUtil.selectTop(unsorted,0.5);
        Assert.assertEquals(Arrays.asList(0,1,4),result);
    }
}