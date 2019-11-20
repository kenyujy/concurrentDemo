/*
*  ThreadLocal
*  hibernate 的 session 存放于 ThreadLocal中， 避免 synchronized
*/
package c2t9;

import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {

    static ThreadLocal<Person> tl= new ThreadLocal<>(); // ThreadLocal 变量只对单个线程可见

    public static void main(String[] args){

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
            System.out.println(tl.get());  //get()方法返回ThreadLocal的对象
        }).start();
    }
}

class Person{
    String name;

    Person(){
        this.name="张三丰";
    }
}

