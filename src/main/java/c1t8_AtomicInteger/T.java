package c1t8_AtomicInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T {

    AtomicInteger count= new AtomicInteger(0);

    void m(){
        count.incrementAndGet();  //作用同synchronized，但是用了系统底层实现，效率高得多
    }

    public static void main(String[] args){
        T t=new T();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i=0; i<10; i++){
            threads.add(new Thread(()-> t.m(),"Thread "+i));  //生成10个线程
        }

        threads.forEach((th)->th.start());
        threads.forEach((th)->{
            try{
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}
