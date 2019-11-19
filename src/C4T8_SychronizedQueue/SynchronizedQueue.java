package C4T8_SychronizedQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronizedQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs= new SynchronousQueue<>();

        new Thread(()->{
            try{
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa"); //如果找不到消费者就会阻塞
        System.out.println(strs.size());
    }
}


// 一般在 while(true){} 里面运行线程
