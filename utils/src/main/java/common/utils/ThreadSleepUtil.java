package common.utils;

public class ThreadSleepUtil {

    /**
     * 线程睡眠
     * @param millis 睡眠时间（单位：毫秒）
     */
    public static void sleep(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignore) {
        }
    }


    /**
     * 线程睡眠
     * @param millis 睡眠时间（单位：毫秒）
     */
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignore) {
        }
    }
}