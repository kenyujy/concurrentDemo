package C4T3_ConcurrentLinkQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueue1 {
    public static void main(String[] args) {
        Queue<String> strs= new ConcurrentLinkedQueue<>();

        for( int i=0; i<10; i++){
            strs.offer("a"+i);
        }

        System.out.println(strs);
        System.out.println(strs.size());

        System.out.println(strs.poll());
        System.out.println(strs.size());

        System.out.println(strs.peek());
        System.out.println(strs.size());

        System.out.println(strs.poll());
        System.out.println(strs.poll());
        System.out.println(strs.poll());
        System.out.println(strs.poll());
        System.out.println(strs.poll());
        System.out.println(strs.poll());
        System.out.println(strs.poll());
        System.out.println(strs.poll());
        System.out.println(strs.poll());
        System.out.println(strs.poll());
        System.out.println(strs.poll());  // 非阻塞， 有可能拿到null
    }

}
