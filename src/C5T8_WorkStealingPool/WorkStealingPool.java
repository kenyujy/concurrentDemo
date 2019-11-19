package C5T8_WorkStealingPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkStealingPool {

    public static void main(String[] args) throws IOException {

        ExecutorService service= Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        // workstealingpool 产生的是daemon 后台线程，主线程不阻塞的话，看不到输出
        System.in.read();  //阻塞

        service.shutdown();
    }

    static class R implements Runnable{
        int time;

        R(int t){
            this.time=t;
        }

        @Override
        public void run(){
            try{
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time+" "+Thread.currentThread().getName());
        }
    }
}
