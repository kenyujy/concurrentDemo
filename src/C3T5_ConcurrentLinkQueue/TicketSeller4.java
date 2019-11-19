package C3T5_ConcurrentLinkQueue;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CountDownLatch;

public class TicketSeller4 {

    static Queue<String> tickets=new ConcurrentLinkedDeque<>();

    static Thread[] ths= new Thread[10];

    static CountDownLatch latch= new CountDownLatch(10);

    static {
        for (int i=1; i<=10000; i++){
            tickets.add("票编号 "+i);
        }

        for(int i=0; i<10; i++){
            Thread j=new Thread(()->{
                while(true){
                    String s= tickets.poll();
                    if(s==null) break;
                    else System.out.println("销售了"+s);
                }
                latch.countDown();  // 线程结束countdown 一次
            });
            ths[i]=j;
        }
    }

    public static void startTh(){
        for(int i=0; i<10; i++){
            ths[i].start();
        }
    }

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        startTh();

        try{
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } //只有countdown 结束才执行下一步
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("totalTime "+ totalTime);
    }
}
