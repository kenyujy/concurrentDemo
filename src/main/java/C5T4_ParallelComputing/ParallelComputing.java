package C5T4_ParallelComputing;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ParallelComputing {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start= System.currentTimeMillis();
        //List<Integer> results= getPrime(1,2000000);
        long end= System.currentTimeMillis();

        System.out.println(end-start);
        //System.out.println(results.size());
        System.out.println("第一种方法结束");  //第一种方法

        final int cpuCoreNum=8;

        ExecutorService service= Executors.newFixedThreadPool(cpuCoreNum);
        // 可以考虑开几个线程。每个从一个容器里面拿，算完再从里面拿
        //
        Task t1= new Task(1, 60000);
        Task t2= new Task(60001, 100000);
        Task t3= new Task(100001, 140000);
        Task t4= new Task(140001, 160000);
        Task t5= new Task(160001, 175000);
        Task t6= new Task(175001, 185000);
        Task t7= new Task(185001, 195000);
        Task t8= new Task(195001, 200000);

        start= System.currentTimeMillis();

        Future<List<Integer>> f1= service.submit(t1);
        Future<List<Integer>> f2= service.submit(t2);
        Future<List<Integer>> f3= service.submit(t3);
        Future<List<Integer>> f4= service.submit(t4);
        Future<List<Integer>> f5= service.submit(t5);
        Future<List<Integer>> f6= service.submit(t6);
        Future<List<Integer>> f7= service.submit(t7);
        Future<List<Integer>> f8= service.submit(t8);

        f1.get();
        f2.get();
        f3.get();
        f4.get();
        f5.get();
        f6.get();
        f7.get();
        f8.get();
        int size= f1.get().size()+f2.get().size()+f3.get().size()+f4.get().size()
                +f5.get().size()+f6.get().size()+f7.get().size()+f8.get().size();
        end=System.currentTimeMillis();
        System.out.println( end- start);
        System.out.println(size);

        service.shutdown();
        System.out.println("第二种方法结束");  //第二种方法

        Queue<Integer>  q= new ConcurrentLinkedQueue<Integer>();
        for (int i=1; i<=2000000; i++){
            q.add(i);
        }

        ExecutorService service2= Executors.newFixedThreadPool(8);
        Queue q2= new ConcurrentLinkedQueue();  // 多线程添加 比vector 要快

        CountDownLatch latch= new CountDownLatch(8);

        var ref = new Object() {
            boolean flag = true;
        };

        start= System.currentTimeMillis();  //计时开始

        for (int i=0; i<8; i++) {
            service2.execute(() -> {
                while (ref.flag) {
                    Integer k= q.poll();
                    if (k==null){
                        break;
                    }
                    if (isPrime(k)) {
                        q2.offer(k);
                    }
                }
                latch.countDown();  // 每个线程结束countdown 一次
            });
        }

        try{
            latch.await();    // -----等待latch 释放
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service2.shutdown();

        end= System.currentTimeMillis();  //计时结束
        System.out.println( end- start);
        System.out.println(q2.size());
        System.out.println("第3种方法结束");  //第3种方法

        List list3;

        List<Integer> list2= new ArrayList();
        for (int i=1; i<=2000000; i++){
            list2.add(i);
        }

        start= System.currentTimeMillis();  //计时开始
        list3= list2.parallelStream().filter(num->isPrime(num)).collect(Collectors.toList());

        end= System.currentTimeMillis();  //计时结束

        System.out.println( end- start);
        System.out.println(list3.size());
        System.out.println("第4种方法结束");  //第4种方法

    }

    static class Task implements Callable<List<Integer>>{
        int startPos, endPos;

        Task(int s, int e){
            this.startPos=s;
            this.endPos=e;
        }

        @Override
        public List<Integer> call(){
            List<Integer> list= getPrime(startPos, endPos);
            return list;
        }
    }

    static boolean isPrime(int num){
        for (int i=2; i<=num/2; i++){
            if (num%i==0){
                return false;
            }
        }

        return true;
    }

    static List<Integer> getPrime(int start, int end){
        List<Integer> results= new ArrayList<>();
        for(int i=start; i<=end; i++){
            if(isPrime(i)) results.add(i);
        }

        return results;
    }
}
