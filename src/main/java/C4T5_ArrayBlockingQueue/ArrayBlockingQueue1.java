package C4T5_ArrayBlockingQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueue1 {
    static BlockingQueue<String> strs= new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i<10; i++){
            strs.put("a"+i);
        }

        //strs.put("aaa");  // 如果不成功会一直等待
        //strs.add("aaa");  // 如果满了会报错
        strs.offer("aaa"); //会返回boolean 表示是否成功，不会一直等待
        //strs.offer("aaa", 1, TimeUnit.SECONDS);  //在指定时间内失败就不会等待

        System.out.println(strs);
    }
}
