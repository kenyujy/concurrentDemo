package c1t7;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class T {

    volatile int count=0;  //每个程序有自己的缓冲区，会把自己的缓冲区的数据写到主内存中？
    synchronized void m(){  //synchronized 既保证可见性也保证原子性
        for (int i=0; i<10000; i++){
            count++;
        }
    }

    public static void main(String[] args){
        T t=new T();

        List<Thread> threads= new ArrayList<Thread>();

        for (int i=0; i<10; i++){
            threads.add(new Thread(()->t.m(),"Thread "+i));
        }

        threads.forEach((th) ->th.start() );
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
