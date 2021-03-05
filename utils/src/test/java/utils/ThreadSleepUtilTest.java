package utils;

import common.utils.ThreadSleepUtil;
import org.junit.Test;

public class ThreadSleepUtilTest {

    @Test
    public void test1() {
        for (int i = 20; i < 80; i++) {
            System.out.println("i = " + i);
            ThreadSleepUtil.sleep(500L);
        }
    }

    @Test
    public void sleepSort() {
        int[] nums = {6, 9, 4, 15, 1, 5, 2, 9, 9, 22, 42};

        Sleeper.idx = 0;
        Sleeper.output = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            new Sleeper(nums[i]).start();
        }

        ThreadSleepUtil.sleep(100);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Sleeper.output[i];
        }

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}

class Sleeper extends Thread{
    public static int[] output;
    public static int idx;

    private int sleep_time;

    public Sleeper(){
        this.sleep_time=0;
    }

    public Sleeper(int sleep_time){
        this.sleep_time=sleep_time;
    }

    @Override
    public void run(){
        ThreadSleepUtil.sleep(sleep_time);
        output[idx++]=this.sleep_time;
    }
}