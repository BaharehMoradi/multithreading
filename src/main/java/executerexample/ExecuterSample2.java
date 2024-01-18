package executerexample;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecuterSample2 {
    public static void main(String[] args) throws InterruptedException {

//        Executor e = Executors.newFixedThreadPool(2); //newFixedThreadPool
//        Executor e= Executors.newSingleThreadExecutor(); //newSingleThreadExecutor
        Executor e= Executors.newCachedThreadPool(); /* for last e.execute the last thread will be used
                                                        13:0
                                                        13:1
                                                        13:2
                                                        13:3 */
        Runnable runnable = new Runnable() {
            public void run() {
                for (int i = 0; i < 4; i++)
                    System.out.println(Thread.currentThread().getId() + ":" + i);
            }
        } ;
        for (int i = 0; i < 3; i++)
            e.execute(runnable);

        Thread.sleep(1000);
        e.execute(runnable);
    }
}