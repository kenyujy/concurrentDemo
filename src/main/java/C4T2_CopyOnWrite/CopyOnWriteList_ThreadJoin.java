package C4T2_CopyOnWrite;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteList_ThreadJoin {
    public static void main(String[] args) {
        List<String> lists= new CopyOnWriteArrayList<>();
        //List<String> lists= new ArrayList<>();
        //List<String> lists= new Vector<>();

        Random r= new Random();
        Thread[] ths= new Thread[100];

        for(int i=0; i<ths.length; i++){
            Runnable task= new Runnable() {
                @Override
                public void run() {
                    for (int i=0; i<1000; i++) lists.add("a"+r.nextInt(10000));
                }
            };

            ths[i]= new Thread(task);
        }

        runAndComputeTime(ths);
        System.out.println(lists.size());
    }

    public static void runAndComputeTime(Thread[] ths){
        long s1= System.currentTimeMillis();
        Arrays.asList(ths).forEach(th->th.start());
        Arrays.asList(ths).forEach(th->{
            try{
                th.join();      // 等待所有线程结束
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s2=System.currentTimeMillis();
        System.out.println(s2-s1);
    }
}
