package LockDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by smcgrath on 7/7/2017.
 * In this class we make use of synchronized locks to control the use of our threads
 * The class is made up of 2 Arrayalists lists List1 and List2, which are populated with random integer values
 *
 */
public class Worker {
    private Random rand = new Random();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<Integer>();
    private List<Integer> list2 = new ArrayList<Integer>();

    //this method is used to put the thread to sleep and add the random value to the list1.
    //we use the synchronized lock to control access to the code block which is contained with in it.
    //once a thread has taken control of the lock no other thread can access the code contained with in
    //the Lock.
    public void stageOne(){
        synchronized(lock1){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(rand.nextInt(100));
        }
    }
    //this method is used to put the thread to sleep and add the random value to the list1.
    //we use the synchronized lock to control access to the code block which is contained with in it.
    //once a thread has taken control of the lock no other thread can access the code contained with in
    //the Lock.
    public void stageTwo(){
        synchronized(lock2){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(rand.nextInt(100));
        }
    }

    public void process(){
        for (int i=0; i <1000; i++){
            stageOne();
            stageTwo();
        }
    }
    public void main(){
        System.out.println("Starting.....");

        long start = System.currentTimeMillis();
        
        Thread t1 = new Thread(() ->{
            process();
        });
        Thread t2 = new Thread(() -> {
            process();
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time take: " + (end - start));
        System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
    }
}
