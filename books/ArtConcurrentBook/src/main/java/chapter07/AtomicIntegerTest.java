package chapter07;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tengfei.fangtf
 * @version $Id: AtomicIntegerTest.java, v 0.1 2015-8-1 上午12:13:16 tengfei.fangtf Exp $
 */
public class AtomicIntegerTest {

    static AtomicInteger ai = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
    }

}
