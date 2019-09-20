package councurrent.cas;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：xxt
 * @date ：Created in 2019/8/27 17:14
 * @description：
 * @modified By：
 */

public class AtomicIntegerTest {

    @Test
    public void testAtomicInteger(){
        AtomicInteger atomicInteger=new AtomicInteger(5);
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.addAndGet(2));
        System.out.println(atomicInteger.getAndAdd(5));
    }
}
