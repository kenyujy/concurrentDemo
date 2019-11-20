package C3T4;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class TicketSeller3 {

    volatile static List<String> tickets= new LinkedList<>();

    static {
        for (int i=1; i<=100; i++){
            tickets.add("票编号: "+i);
        }
    }

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        for (int i=0; i<10; i++) {
            new Thread(()->{
                while(true) {
                    synchronized (tickets) {
                        if (tickets.size()>0){
                            System.out.println("销售了。。。" + tickets.remove(0)); //当票数还有很多时异步卖票
                        }else {
                            break;
                        }
                    }
                }
            }).start();
        }

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("totalTime"+ totalTime);
    }
}
