public class Requestback implements Runnable {

    int request;
    Worker e;

    public Requestback(Worker e,int i) {
        this.e=e;
        this.request = i;
    }

    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (request==1) Lift.first.add(e);
        if (request==2) Lift.second.add(e);
        if (request==3) Lift.third.add(e);
        if (request==4)Lift.fourth.add(e);
        if (request==5)Lift.fifth.add(e);
    }
}
