import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable {
    public int id;
    public int weight;
    static int weight1 = 0;
    public int startfloor;
    public int endfloor;
    static int count;
    public boolean status;

    static final ArrayList<Worker> elevator = new ArrayList<>();


    public Worker(int id,Object o) {
        this.id = id;
        this.status=false;

        A a = () -> {
            Random rand = new Random();
            int weight = rand.nextInt(150) + 101;
            return weight;
        };

        this.weight = a.random();
        this.startfloor = 0;

        B b = () -> {
            Random rand = new Random();
            int floor = rand.nextInt(5) + 1;
            return floor;
        };

        this.endfloor = b.runningB();
        Driver.o = o;
        System.out.println("Person : " + (this.id) + " wants to enter the elevator who weighs " + this.weight + " from floor " +
               startfloor+ " and wants to go to floor " + this.endfloor+"\n");
    }

    public static void lift() {
            ExecutorService executor = Executors.newFixedThreadPool(1);
                executor.execute(new Lift(elevator));
                executor.shutdown();
                while (!executor.isTerminated()) {
                    try {Thread.sleep(2000);} catch (InterruptedException e) {}
                }

    }


    public void run() {
        synchronized (Driver.o) {
                if (weight1 >= 700 || this.weight + weight1 >= 700) {
                        lift();
                }
            count++;
                    elevator.add(this);
                    weight1 += this.weight;
            System.out.println("Person : "+id+ " entered the elevator.");
            this.status=true;
                    if (count==Driver.n_people) lift();
        }
    }
}




