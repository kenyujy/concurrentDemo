package c1l2;
//多个线程共享读写资源的时候需要同步 synchronize
public class T {   //非synchronized 与synchronized 方法可以同时运行

    public synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+" m1 start...");
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+" m1 end...");
    }

    public void m2(){
        try{
            Thread.sleep(6000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+" m2...");
    }

    public static void main(String[] args){
        T t= new T();

        new Thread(()-> t.m1(), "t1").start();
        new Thread(()-> t.m2(), "t2").start();
    }
}
