package inter_thread_communication.newdemo;

public class NewDemo {

static void main() {
        int sum =0;
        SumThread sumThread = new SumThread(sum);
        sumThread.start();
        System.out.println("Sum is main thread : "+sum);
    }

}
