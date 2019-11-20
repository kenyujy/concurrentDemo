package c2t1;

import java.util.concurrent.TimeUnit;

public class ReentrantLock1 {

    synchronized void m(){
        for(int i=0; i<10; i++){
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println(i);
        }
    }

    synchronized void m2(){
        System.out.println("m2....");
    }

    public static void main(String[] args){
        ReentrantLock1 r1= new ReentrantLock1();
        new Thread(()->r1.m()).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->r1.m2()).start();
    }
}
