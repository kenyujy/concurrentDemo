package c1t6_dcl;

public class DoubleCheckLock {

    private static DoubleCheckLock instance= null;

    private DoubleCheckLock(){
        System.out.println(Thread.currentThread().getName()+"构造方法");
    }

    public static DoubleCheckLock getInstance(){
        if (null== instance){
            synchronized (DoubleCheckLock.class) {  //加锁
                if (null==instance)                 //双端检查
                instance = new DoubleCheckLock();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        /*
        System.out.println(DoubleCheckLock.getInstance()==DoubleCheckLock.getInstance());
        System.out.println(DoubleCheckLock.getInstance()==DoubleCheckLock.getInstance());
        System.out.println(DoubleCheckLock.getInstance()==DoubleCheckLock.getInstance());
        System.out.println(DoubleCheckLock.getInstance()==DoubleCheckLock.getInstance());
         */

        for(int i=0; i<10; i++){
            new Thread(()->{
                System.out.println(DoubleCheckLock.getInstance()==DoubleCheckLock.getInstance());
            }).start();
        }
    }
}
