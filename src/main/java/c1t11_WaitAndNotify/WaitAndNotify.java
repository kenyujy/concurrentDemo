package c1t11_WaitAndNotify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitAndNotify {

    volatile List list= new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return this.list.size();
    }

    public static void main(String[] args){
        WaitAndNotify container = new WaitAndNotify();

        final Object lock = new Object();
        new Thread(()->{
            synchronized (lock){   // 把一个对象加上锁
                System.out.println("t2 启动");
                if (container.size()!= 5){
                    try{
                        lock.wait();    //如果容器size!= 5， 调用锁的wait方法, 线程进入wait，释放锁, 其他线程可获得锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
                lock.notify();        //唤醒其他等待锁的线程
            }
        },"t2").start();

        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1 启动");
            synchronized (lock){          //把lock 加上锁
                for (int i=0; i<10; i++){
                    container.add(new Object());
                    System.out.println("add "+i);

                    if(container.size()==5){
                        try {
                            lock.wait();       //调用lock的 wait方法，释放锁，当前线程进入暂停状态
                            lock.notify();      //如果条件满足，调用lock的notify方法，notify并不会释放锁，而是唤醒其他等待锁的线程, notify不能指定线程
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try{
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t1").start();
    }
}
