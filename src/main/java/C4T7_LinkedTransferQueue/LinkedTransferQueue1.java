package C4T7_LinkedTransferQueue;

import java.util.concurrent.LinkedTransferQueue;

public class LinkedTransferQueue1 {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs= new LinkedTransferQueue<>();

        new Thread(()->{
            try{
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa"); //如果找不到消费者就会阻塞

    }
}
