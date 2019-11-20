package C4T1_ConcurrentMap;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class ConcurrentMap1_CountdownLatch {

    public static void main(String[] args) {

        //Map<String,String> map= new Hashtable<>();  //HashTable 是加锁的
        Map<String, String> map= new ConcurrentHashMap<>();
        //Map<String, String> map= new ConcurrentSkipListMap<>(); // ConcurrentSkipListMap同步并且排序

        Random r= new Random();
        Thread[] ths= new Thread[100];

        CountDownLatch latch= new CountDownLatch(ths.length);
        long start= System.currentTimeMillis();
        for(int i=0; i<ths.length; i++){
            ths[i]= new Thread(()->{
                for(int j=0; j<100000; j++) map.put("a"+r.nextInt(100000),"a"+r.nextInt(100000));
                latch.countDown();
            });
        }

        Arrays.asList(ths).forEach(th->th.start());

        try{
            latch.await();    //等待latch 释放
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

        long end= System.currentTimeMillis();
        System.out.println(end-start);
    }
}
