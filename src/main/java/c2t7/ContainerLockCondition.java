package c2t7;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ContainerLockCondition<T> {

    final private LinkedList<T> list = new LinkedList<T>();

    final private int MAX = 10; //最多10个元素
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();


    public void put(T t) {
        try {
            lock.lock();
            while (list.size() == MAX) {
                producer.await(); // 生产者进入等待
            }
            list.add(t);
            ++count;
            consumer.signalAll();    // 唤醒所有消费者
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public T get(){
        T t=null;
        try{
            lock.lock();
            while( list.size()==0){
                consumer.await();    // 消费者进入等待
            }
            t= list.removeFirst();
            count--;
            producer.signalAll();   // 唤醒所有生产者
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        return t;
    }

    public static void main(String[] args){
        ContainerLockCondition<String> c= new ContainerLockCondition();

        for(int i=0; i<10; i++){
            new Thread(()->{
                for(int j=0; j<25; j++) {
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
