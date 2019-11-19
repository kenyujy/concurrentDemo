package C3T3;

/*
    模拟10个线程销售车票
    最终会导致超售
    比如5个人一起论锤子打桩，最后会因为不同步导致多打深
    Vector是同步的， 问题在于判断和执行之间不同步，有可能被打断
 */
import java.util.Vector;
import java.util.concurrent.TimeUnit;


public class TicketSeller2 {

    volatile static Vector<String> tickets= new Vector<>(); // Vector 容器的方法是同步的

    static {
        for (int i=1; i<=10000; i++){
            tickets.add("票编号: "+i);
        }
    }

    public static void main(String[] args) {

        for (int i=0; i<10; i++) {
            new Thread(()->{
                while(tickets.size()>0){
                    // 判断和执行不同步。还是会出问题
                    System.out.println("销售了。。。" + tickets.remove(0)); //当票数还有很多时异步卖票
                }
            }).start();
        }
    }
}