package c1t5_Runnable;

import java.util.concurrent.TimeUnit;

public class Trunnable {

    int count=0;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName()+" start");

        while (true){
            count++;
            System.out.println(Thread.currentThread().getName()+" count "+count);
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(count==5){
                int i=1/0;
            }
        }
    }

    public static void main(String[] args){
        Trunnable t=new Trunnable();

        Runnable r= new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };

        new Thread(r,"t1").start();
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r,"t2").start();
    }
}
