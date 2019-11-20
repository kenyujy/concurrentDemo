package C5T6_SingleThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPool {

    public static void main(String[] args) {
        ExecutorService service= Executors.newSingleThreadExecutor();
        for(int i=0; i<5; i++){
            final int j=i;
            service.execute(()->{
                System.out.println(Thread.currentThread().getName());
            });
        }
    }
}
