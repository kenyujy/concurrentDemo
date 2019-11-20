package c1t4;

import java.util.concurrent.TimeUnit;

public class T {

    synchronized void m(){
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("m end");
    }

    public static void main (String[] args){
        TT tt =new TT();
        tt.m();
    }
}

class TT extends T {

    @Override
    synchronized void m(){
        System.out.println("child m start");
        super.m();
        System.out.println("child m end");
    }
}
