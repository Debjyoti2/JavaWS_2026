package inter_thread_communication.newdemo;

public class SumThread extends  Thread{
    int total = 0;

    SumThread(int sum){
        total = sum ;
    }

    public void run() {
        for(int i=0;i<100;i++){
            total=total+i;
        }
        System.out.println("Sum is : "+total);
    }
}
