package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by smcgrath on 7/7/2017.
 */

class Processor implements Runnable{

    private int id;

    public Processor(int id){
        this.id = id;
    }

    public void run() {
        System.out.println("Starting: " + id);

        //this is a simulation of some work which could happen
        //we put the thread to sleep for 5 seconds
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println("Completed: " + id);
    }
}
public class AppPool {
    public static void main(String[] args) {
        //create a thread pool with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //we submit 5 new processes to the executor pool
        for(int i = 0; i<5; i++){
            //new Process with id
            executor.submit(new Processor(i));
        }

        //shutdown() will wait till all the tasks have been completed and then will shutdown
        executor.shutdown();

        System.out.println("All tasks submitted");

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed");
    }
}
