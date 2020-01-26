import java.util.*;

public class Lift implements Runnable {
    static ArrayList<Worker> lift;

    static Queue<Worker> first = new PriorityQueue<>((o1,o2) -> 0);
    static Queue<Worker> second = new PriorityQueue<>((o1,o2) -> 0);
    static Queue<Worker> third = new PriorityQueue<>((o1,o2) -> 0);
    static Queue<Worker> fourth = new PriorityQueue<>((o1,o2) -> 0);
    static Queue<Worker> fifth = new PriorityQueue<>((o1,o2) -> 0);

    static ArrayList<Worker> temp = new ArrayList<>();
    static int j;
    static boolean flag1 = false;
    static boolean flag2 = false;




    public Lift(ArrayList<Worker> elevator) {
        this.lift = elevator;
    }

    public void call(){
        synchronized (Worker.elevator) {
            for (int i = 1; i <= 5; i++) {
                System.out.println("\nElevator : Floor " + i + "");
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
            movedown(6);
        }
    }

    public double percentage(Worker e){
        float a = (float) e.weight / Worker.weight1;
        double b = Math.round(a*100.0)/100.0;
        return b;
    }

    public boolean checkup(int currentfloor) {
        synchronized (Worker.elevator) {
            if (currentfloor == 1) {
                if (first.size() > 0 || second.size() > 0 || third.size() > 0 || fourth.size() > 0 || fifth.size() > 0)
                    return true;
            } else if (currentfloor == 2) {
                if (second.size() > 0 || third.size() > 0 || fourth.size() > 0 || fifth.size() > 0)
                    return true;
            } else if (currentfloor == 3) {
                if (third.size() > 0 || fourth.size() > 0 || fifth.size() > 0)
                    return true;
            } else if (currentfloor == 4) {
                if (fourth.size() > 0 || fifth.size() > 0)
                    return true;
            } else if (currentfloor == 5) {
                if (fifth.size() > 0)
                    return true;
            }
            return false;
        }
    }

    public void moveup(int currentfloor){
        synchronized (Worker.elevator) {
            for (int i = currentfloor; i <= 5; i++) {
                System.out.println("\nElevator : Floor " + i + "");
                while (i==5 && fifth.size() > 0  && fifth.peek().weight+Worker.weight1<=700){
                    System.out.println("Person : "+fifth.peek().id+" entered the elevator.");
                    Worker.weight1+=fifth.peek().weight;
                    fifth.peek().status=true;
                    temp.add(fifth.poll());
                }
                while (i==4 && fourth.size() > 0 && fourth.peek().weight+Worker.weight1<=700){
                    System.out.println("Person : "+fourth.peek().id+" entered the elevator.");
                    Worker.weight1+=fourth.peek().weight;
                    fourth.peek().status=true;
                    temp.add(fourth.poll());
                }
                while (i==3 && third.size() > 0 && third.peek().weight+Worker.weight1<=700){
                    System.out.println("Person : "+third.peek().id+" entered the elevator.");
                    Worker.weight1+=third.peek().weight;
                    third.peek().status=true;
                    temp.add(third.poll());
                }
                while (i==2 && second.size() > 0 && second.peek().weight+Worker.weight1<=700){
                    System.out.println("Person : "+second.peek().id+" entered the elevator.");
                    Worker.weight1+=second.peek().weight;
                    second.peek().status=true;
                    temp.add(second.poll());
                }
                while (i==1 && first.size() > 0 && first.peek().weight+Worker.weight1<=700){
                    System.out.println("Person : "+first.peek().id+" entered the elevator.");
                    Worker.weight1+=first.peek().weight;
                    first.peek().status=true;
                    temp.add(first.poll());
                }

            }
            if (flag2==false){
                flag2=true;
                ElevatorAndPersonSummary(Driver.worker);
            }
            movedown(5);
        }
    }

    public void movedown(int currentfloor){
        synchronized (Worker.elevator) {

            for (int i = currentfloor-1; i >= 0; i--) {
                System.out.println("\nElevator : Floor " + i + "");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                while (i==5 && fifth.size() > 0  && fifth.peek().weight+Worker.weight1<=700){
                        System.out.println("Person : "+fifth.peek().id+" entered the elevator.");
                        Worker.weight1+=fifth.peek().weight;
                        fifth.peek().status=true;
                        temp.add(fifth.poll());
                }
                while (i==4 && fourth.size() > 0 && fourth.peek().weight+Worker.weight1<=700){
                        System.out.println("Person : "+fourth.peek().id+" entered the elevator.");
                        Worker.weight1+=fourth.peek().weight;
                        fourth.peek().status=true;
                        temp.add(fourth.poll());
                }
                while (i==3 && third.size() > 0 && third.peek().weight+Worker.weight1<=700){
                        System.out.println("Person : "+third.peek().id+" entered the elevator.");
                        Worker.weight1+=third.peek().weight;
                        third.peek().status=true;
                        temp.add(third.poll());
                }
                while (i==2 && second.size() > 0 && second.peek().weight+Worker.weight1<=700){
                        System.out.println("Person : "+second.peek().id+" entered the elevator.");
                        Worker.weight1+=second.peek().weight;
                        second.peek().status=true;
                        temp.add(second.poll());
                }
                while (i==1 && first.size() > 0 && first.peek().weight+Worker.weight1<=700){
                        System.out.println("Person : "+first.peek().id+" entered the elevator.");
                        Worker.weight1+=first.peek().weight;
                        first.peek().status=true;
                        temp.add(first.poll());
                }

                if (i==0){
                    for (int k=0;k<temp.size();k++){
                        System.out.println("Person : "+temp.get(0).id+" exited the elevator.");
                        Worker.weight1-=temp.get(0).weight;
                        temp.get(0).status=false;
                        temp.remove(0);
                        k-=1;
                    }
                }
            }
            if (flag1==false){
                flag1=true;
                ElevatorAndPersonSummary(Driver.worker);
            }
            if (Worker.count == Driver.n_people && (first.size() > 0 || second.size() > 0 || third.size() > 0 || fourth.size() > 0 || fifth.size() > 0)){
                call();
            }
        }
    }

    public void ElevatorAndPersonSummary(Worker[] p){
        synchronized (Worker.elevator) {
            List<Worker> l = Arrays.asList(p);
            l.stream().filter((q) -> q.id % 2 == 0).forEach((q) -> {
                System.out.println("--------------------------------");
                System.out.println("Person :" + q.id + "\n\tWeight :" + q.weight + "\n\tStatus :" + q.status);
                System.out.println("--------------------------------");
                if (q.status)
                    System.out.print("\t"+percentage(q));
            });
        }
    }

    public void run() {
        synchronized (Worker.elevator) {
            for (j = 1; j <= 5; j++) {
                System.out.println("\nElevator : Floor " + j +"");
                for (int i = 0; i < lift.size(); i++) {
                    if (lift.get(i).endfloor == j) {
                        System.out.println("Person : " + (lift.get(i).id) + " exited the elevator ");
                        lift.get(i).status=false;
                        Thread t1 = new Thread(new Requestback(lift.get(i),j));
                        t1.start();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Worker.weight1 -= lift.get(i).weight;
                        lift.remove(i);
                        i=i-1;
                    }
                }
                try {Thread.sleep(1000); } catch (InterruptedException e) {}
                if (lift.isEmpty()){
                    if (checkup(j)){
                        moveup(j);
                    }
                    else {
                        movedown(j);
                    }
                    break;
                }
            }

        }
    }
}