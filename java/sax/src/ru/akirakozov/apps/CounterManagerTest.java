package ru.akirakozov.apps;

import org.junit.Assert;
import org.junit.Test;

public class CounterManagerTest {

    @Test
    public void calcCount() throws Exception {
        CounterManager manager = new CounterManager();
        Assert.assertEquals(2, manager.calcCount(getClass().getResource("test.xml").toString(), "apple"));
        Assert.assertEquals(1, manager.calcCount(getClass().getResource("test.xml").toString(), "banana"));
    }
}