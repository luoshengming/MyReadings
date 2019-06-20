package nia.chapter7;

import java.util.Collections;
import java.util.List;

/**
 * Listing 7.1 Executing tasks in an event loop
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class EventLoopExamples {
    /**
     * Listing 7.1 Executing tasks in an event loop
     */
    public static void executeTaskInEventLoop() {
        boolean terminated = true;
        //...
        while (!terminated) {
            //阻塞，直到有事件已经就绪可被运行
            List<Runnable> readyEvents = blockUntilEventsReady();
            //循环遍历，并处理所有的事件
            for (Runnable ev : readyEvents) {
                ev.run();
            }
        }
    }

    private static List<Runnable> blockUntilEventsReady() {
        return Collections.singletonList(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
