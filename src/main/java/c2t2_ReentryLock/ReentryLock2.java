package c2t2_ReentryLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentryLock2 {

    Lock lock= new ReentrantLock(); //ReentrantLock 必须手动释放

    void m1(){
        try{
            lock.lock();   //synchronized(this)
            for (int i=0; i<10; i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lock.unlock();  //手动释放锁
        }
    }

    void m2(){
        lock.lock();   //锁定同一把锁
        System.out.println("m2 ....");
        lock.unlock(); //手动释放锁
    }

    public static void main(String[] args){
        ReentryLock2 r2 = new ReentryLock2();

        new Thread(()->r2.m1()).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->r2.m2()).start();
    }
}
