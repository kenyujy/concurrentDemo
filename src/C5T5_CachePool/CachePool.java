package C5T5_CachePool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CachePool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service= Executors.newCachedThreadPool();

        System.out.println(service);

        for(int i=0; i<2; i++){
            service.execute(()->{
                try{
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        TimeUnit.SECONDS.sleep(80);

        System.out.println(service);
    }
}
