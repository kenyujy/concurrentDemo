package c2t8;

import java.util.concurrent.TimeUnit;

public class ThreadLocal1 {

    volatile static Person p= new Person();

    public static void main(String[] args){

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name="张三";
        }).start();
    }
}

class Person{
    String name;

    Person(){
        this.name="张三丰";
    }
}
