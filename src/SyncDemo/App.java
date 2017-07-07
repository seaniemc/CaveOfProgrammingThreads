package SyncDemo;

import java.util.Scanner;

/**
 * Created by smcgrath on 7/7/2017.
 */
class Processor extends Thread{

    //the volatile key word is used to stop the Threads from
    //caching variables
    private volatile boolean running = true;

    public void run(){
        while(running){
            System.out.println("Hello");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutDown(){
        running = false;
    }
}
public class App {
    public static void main(String[] args) {
        Processor proc1 = new Processor();
        proc1.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        proc1.shutDown();
    }
}
