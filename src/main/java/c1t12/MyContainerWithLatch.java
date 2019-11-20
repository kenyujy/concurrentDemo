package c1t12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyContainerWithLatch {

    volatile List list=new ArrayList();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return this.list.size();
    }

    public static void main(String[] args){
        MyContainerWithLatch c= new MyContainerWithLatch();

        CountDownLatch latch= new CountDownLatch(1);  //设置countdownlatch 初始值，当值为0时，countdownlatch释放

        new Thread(()->{
            System.out.println("t2 启动");
            try{
                if (c.size() !=5){
                    latch.await();    //await()方法锁定， 只有当latch为0时重新启动
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 结束");
        },"t2").start();

        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            System.out.println("t1 启动");
            for(int i=0; i<10; i++){
                c.add(new Object());
                System.out.println("add "+i);
                if(c.size()==5){
                    latch.countDown();  //调用一次countdown， 里面值就减少1
                }                       //latch 打开以后，这个线程可以继续运行，不需要等待

                try{
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t1 结束");
        },"t1").start();


    }
}
