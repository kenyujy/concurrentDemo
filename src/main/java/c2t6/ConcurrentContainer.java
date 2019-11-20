package c2t6;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class ConcurrentContainer<T> {   //类声明的时候指定泛型
    final private LinkedList<T> list= new LinkedList<T>();

    final private int MAX=10; //最多10个元素
    private int count=0;

    public synchronized void put(T t){
        while (list.size()== MAX){
            try{
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.add(t);
        ++count;
        this.notifyAll();  //通知consumer
    }

    public synchronized T get(){
        T t = null;
        while(list.size()==0){
            try{
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t= list.removeFirst();
        count--;
        this.notifyAll();  //通知producer
        return t;
    }

    public synchronized int getCount(){
        return this.count;
    }

    public static void main(String[] args){
        ConcurrentContainer<String> c = new ConcurrentContainer<>();

        for(int i=0; i<10; i++){
            new Thread(()->{
                for(int j=0; j<25; j++) {     //生产者和消费者 不能完成所有循环的情况下会一直等待，程序不能结束
                    System.out.println(c.get());
                }
            },"c"+i).start();
        }
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=0; i<10; i++){
            new Thread(()->{
                for(int j=0; j<25; j++){
                    c.put(Thread.currentThread().getName()+" "+j);
                }
            },"p"+i).start();
        }
    }
}
