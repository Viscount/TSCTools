package main.entity;

import main.util.JsonUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Jaric Liao on 2015/12/12.
 */
public class VectorTest {

    Vector v1,v2;

    @Before
    public void setUp() throws Exception{
        Map<String,Long> wordCount = new HashMap<String,Long>();
        wordCount.put("haha",2L);
        wordCount.put("go",1L);
        v1 = new Vector(wordCount);
        wordCount = new HashMap<String,Long>();
        wordCount.put("haha",2L);
        wordCount.put("nice",1L);
        v2 = new Vector(wordCount);
    }

    @Test
    public void testSimularity() throws Exception {
        double sim = Vector.simularity(v1,v2);
        Assert.assertEquals(4.0/6.0,sim,0.001);
    }

    @Test
    public void testMerge() throws Exception {
        Vector v = Vector.merge(v1,v2);
        Map<String,Long> wordCount = new HashMap<String,Long>();
        wordCount.put("haha",4L);
        wordCount.put("go",1L);
        wordCount.put("nice",1L);
        Vector standard = new Vector(wordCount);
        Assert.assertEquals(JsonUtil.toJson(standard), JsonUtil.toJson(v));
    }
}