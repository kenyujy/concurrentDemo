package c1t9;

import java.util.concurrent.TimeUnit;

public class T {

    int count=0;
    synchronized void m1(){
        try{
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        count++;

        try{
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2(){
        try{
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 只有需要的地方才加锁  synchronized (this){需要锁定的代码块}
        synchronized (this) {
            count++;
        }

        try{
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
