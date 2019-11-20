package c1t6_dcl;

import java.util.concurrent.TimeUnit;

public class T_volatile {

    volatile int running= 1000;  //缓存过期通知, 线程之间变量的可见性, volatile不能保证多个线程变量不一致的问题
    void m(){   //加了synchronized 之后其他线程读取不了 这个类
        System.out.println("m running");
        while (running>0){
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            running--;
            System.out.println("running= "+running);
        }
        System.out.println("m end!");
    }

    public static void main(String[] args){
        T_volatile t= new T_volatile();
        new Thread(()->t.m()).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running=1;  //越过线程，把条件 int 设置为1 终止线程, 加了volatile 其他线程可以知道更改
    }
}
