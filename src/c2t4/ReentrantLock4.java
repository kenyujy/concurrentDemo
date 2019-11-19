package c2t4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock4 {


    public static void main(String[] args){
        Lock lock= new ReentrantLock(); //ReentrantLock 必须手动释放

        Thread t1= new Thread(()->{
            try{
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                System.out.println("Thread t1 interrupted");;
            }finally {
                lock.unlock();
            }
        });

        t1.start();

        Thread t2 = new Thread(()->{

            try{
                lock.lockInterruptibly();   //可以对线程interrupt()打断作出响应
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("Thread t2 interrupted");
            }
            try{
                lock.unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t2.start();

        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();  // 打断线程2的等待
    }
}
