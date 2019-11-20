package c2t3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock3 {

    Lock lock= new ReentrantLock(); //ReentrantLock 必须手动释放

    void m1(){
        try{
            lock.lock();   //synchronized(this)
            for (int i=0; i<3; i++){
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
        boolean locked=false;
        try{
            locked=lock.tryLock(5, TimeUnit.SECONDS); //尝试获得锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2...."+locked);  //实际上不管有没有获得锁都会执行
        if(locked){lock.unlock();}
    }

    public static void main(String[] args){
        ReentrantLock3 r3 = new ReentrantLock3();

        new Thread(()->r3.m1()).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->r3.m2()).start();
    }
}

