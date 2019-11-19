package c1t10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyContainer {
    volatile List list=new ArrayList();  //要加volatile 保证线程间数据的可见性

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }

    public static void main(String[] args){
        MyContainer c =new MyContainer();

        new Thread(()->{
            for (int i=0; i<10; i++){
                c.add(new Object());
                System.out.println("add "+i);

                try{
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            while(true){
                /*try{                                        //加了sleep 以后线程间可见了，CPU 有空去读取公共内存部分，不会只限于程序本身私有的内存
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                if (c.size()==5){
                    break;
                }
            }
            System.out.println("t2 end");
        },"t2").start();
    }
}
