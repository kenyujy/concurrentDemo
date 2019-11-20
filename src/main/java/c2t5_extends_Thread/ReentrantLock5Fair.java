package c2t5_extends_Thread;

/*
 *  可重入锁，外层同步代码获得锁后， 进入另一个内层同步方法，自动获取同一个锁
 */
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock5Fair extends Thread {

    private static ReentrantLock lock= new ReentrantLock(true); //参数为true代表这是一个公平锁

    public void run(){
        for(int i=0; i<100; i++){
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+" 获得锁");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args){
        ReentrantLock5Fair r5= new ReentrantLock5Fair();
        Thread t1= new Thread(r5);
        Thread t2= new Thread(r5);
        t1.start();
        t2.start();
    }
}
