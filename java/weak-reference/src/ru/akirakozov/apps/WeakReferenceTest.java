package ru.akirakozov.apps;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {

    public static WeakReference<IntHolder> reference;

    public static class IntHolder {
        public final int value;

        public IntHolder(int value) {
            this.value = value;
        }
    }

    public static void createBooleanWeakReference() {
        IntHolder value = new IntHolder(5);
        reference = new WeakReference(value);
    }

    @Test
    public void checkLifeCycleOfWeakReference() throws InterruptedException {
        createBooleanWeakReference();
        Assert.assertEquals(5, reference.get().value);
        System.gc();
        Assert.assertNull(reference.get());
    }
}
