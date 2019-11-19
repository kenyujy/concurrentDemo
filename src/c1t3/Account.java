/*
* 当一个线程进入一个对象的一个synchronized方法后，其它线程是否可进入此对象的其它方法?
* 其他方法是synchronized 则不能， 不是的就能！！
*/

package c1t3;

import java.util.concurrent.TimeUnit;

public class Account {   //对写操作加锁，对读操作没有加锁，造成 dirty read

    String name;
    double balance;

    public synchronized void set(String name, double balance){
        this.name= name;
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        this.balance= balance;
    }

    public double getBalance(String name){      //要想余额读取正确，读取也要加锁
        return this.balance;
    }  // get没有加上 synchronized

    public static void main(String[] args){
        Account a= new Account();

        new Thread(()-> a.set("ken",100.0)).start();  //线程执行过程中，后面的代码不会等待这个线程结束才执行

        try{
            TimeUnit.SECONDS.sleep(1);    //等一秒读取，这时候上面Thread 的set 方法还没执行完，这时读取账户余额为0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("ken"));

        try{
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("ken")); //等2秒读取，这时候上面Thread 的set 方法执行完，这时读取账户余额为100
    }
}
