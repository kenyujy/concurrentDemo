package ParallelStreamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParallelStreamAPI {

    public static void main(String[] args) {
        List<Integer> nums= new ArrayList<>();
        Random r= new Random();

        for(int i=1; i<=100000; i++) nums.add(i);

        long start= System.currentTimeMillis();
        long count= nums.stream().filter(v->isPrime(v)).count();   //stream nums.stream().forEach(v->isPrime(v));
        long end= System.currentTimeMillis();

        System.out.println(count);
        System.out.println(end-start);

        start= System.currentTimeMillis();
        long count2= nums.stream().parallel().filter(num->isPrime(num)).count(); //parallel stream
        end= System.currentTimeMillis();

        System.out.println(count2);
        System.out.println(end-start);
    }

    static boolean isPrime(int num){
        for (int i=2; i<=num/2; i++){
            if(num%i==0) return false;
        }
        return true;
    }

    static void countPrime(int num,int count){
        if (isPrime(num))
        count++;
    }
}
