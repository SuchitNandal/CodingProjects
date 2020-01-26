import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver{
    static public int n_people;
    public int limit;
    public static Worker[] worker = new Worker[10];
    static Object o = new Object();


    Driver(){
        this.n_people = 10;
        this.limit=700;
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Number of floors : 5");

        final Runnable runnable = ()-> {
            new Driver();
            ExecutorService executor = Executors.newFixedThreadPool(n_people);

            for (int i = 1; i <= n_people; i++) {
                Worker w;
                w = new Worker(i,o);
                worker[i-1] = w;
                executor.execute(w);

            }

            executor.shutdown();
            while ( !executor.isTerminated() ) {
                try {
                    Thread.sleep(100);
                }
                catch ( Exception e ) { }
            }
        };

        Thread Elevator = new Thread(runnable);
        Elevator.start();
        Elevator.join();
        System.out.println("\nAll people have exited.");

    }
}