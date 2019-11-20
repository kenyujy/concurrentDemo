package C4T6_DelayQueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueue1 {

    static BlockingQueue<Mytask> tasks= new DelayQueue<>();

    static Random r= new Random();

    static class Mytask implements Delayed{

        long runningTime;

        Mytask(long rt){
            this.runningTime=rt;
        }

        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS)<o.getDelay(TimeUnit.MILLISECONDS)){
                return -1;
            }else if(this.getDelay(TimeUnit.MILLISECONDS)>o.getDelay(TimeUnit.MILLISECONDS)){
                return 1;
            }
            else return 0;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }


    }
}
