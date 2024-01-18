package executerexample;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class WordLengthCallable implements Callable<Integer> {
    private String word;

    public WordLengthCallable(String word) {
        this.word = word;
    }

    @Override
    public Integer call() throws Exception {
        return word.length();
    }
}

class ExecuterSample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        Set<Future<Integer>> set = new HashSet<>();
        String[] words = {"Ali", "Taghi", "Naghi"};
        for (String word : words) {
            Callable<Integer> callable = new WordLengthCallable(word);
            Future<Integer> future = pool.submit(callable);
            System.out.println(Thread.currentThread().getId());
            set.add(future);
        }
        int sum = 0;
        for (Future<Integer> future : set)
            sum += future.get();
        System.out.println("The sum of lengths is " + sum);
    }
}
