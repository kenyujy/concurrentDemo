/*
    模拟10个线程销售车票
    最终会导致超售
    比如5个人一起论锤子打桩，最后会因为不同步导致多打深
 */

package C3T2_DeadLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class TicketSeller1 {

    volatile static List<String> tickets= new ArrayList<String>();

    static {
        for (int i=1; i<=10000; i++){
            tickets.add("票编号: "+i);
        }
    }

    public synchronized void sellTicket(){
        System.out.println("销售了。。。"+tickets.remove(0));
    }

    public static void main(String[] args) {
        ReentrantLock lock1=new ReentrantLock(true);
        final Object lock = new Object();
        for (int i=0; i<10; i++) {
            new Thread(()->{
                while(tickets.size()>0){
                    if (tickets.size()<100) {  //当票数少于10张时开始同步卖票
                        lock1.lock();
                        System.out.println("销售了。。。" + tickets.remove(0));
                        System.out.println(Thread.currentThread().getName()+" 获得锁");
                        lock1.unlock();
                    } else{
                        System.out.println("销售了。。。" + tickets.remove(0)); //当票数还有很多时异步卖票
                        }
                    }
            }).start();
        }
    }
}
// 为什么会造成死锁