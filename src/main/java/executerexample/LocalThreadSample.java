package executerexample;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LocalThreadSample implements Runnable {
    ThreadLocal<Integer> tl = new ThreadLocal<>();

    public void run() {
        tl.set(tl.get() == null ? 1 : tl.get() + 1);
        long thrID = Thread.currentThread().getId();
        System.out.println(thrID + ":" + tl.get());
    }
}

class Main {
    public static void main(String[] args) {
//        Executor e = Executors.newSingleThreadExecutor();
                                                           /*
                                                        12:1
                                                        12:2
                                                        12:3
                                                        12:4
                                                        12:5
                                                          */
        Executor e = Executors.newFixedThreadPool(10);
                                                        /*
                                                        14:1
                                                        15:1
                                                        12:1
                                                        13:1
                                                        16:1
                                                        */
        LocalThreadSample task= new LocalThreadSample();
        for(int i= 0; i< 5; i++)
            e.execute(task);
    }
}