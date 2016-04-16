import org.junit.Assert;
import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.List;

/**
 * @author akirakozov
 */
public class MethodHandleTest {

    @Test
    public void callSizeOfArrayList() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle mt = lookup.findVirtual(List.class, "size", MethodType.methodType(int.class));
        int size = (int) mt.invokeExact(Arrays.asList(1, 2, 3));
        Assert.assertEquals(3, size);
    }
}
