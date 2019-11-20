package C5T3_Future;

import java.util.concurrent.*;

public class FutureTask {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        java.util.concurrent.FutureTask<Integer> task= new java.util.concurrent.FutureTask<>(()->{  // Callable对象 封装成 FutureTask
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();

        System.out.println(task.get()); // 阻塞等结果

        ExecutorService service= Executors.newFixedThreadPool(5);
        Future<Integer> f= service.submit(()->{  //返回值放在Future
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });

        System.out.println(f.get());
        System.out.println(f.isDone());
        System.out.println(f.get());
        System.out.println(f.isDone());
        service.shutdown();
    }
}
