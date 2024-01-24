import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        Timer timer = new Timer();

        Semaphore carros = new Semaphore(1);
        System.out.println("***  Cruzamento  ***");

        Thread TrafegoA = new TrafficFlowA(carros);
        Thread TrafegoB = new TrafficFlowB(carros);
        TrafegoA.start();
        TrafegoB.start();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ((TrafficFlowA) TrafegoA).exit();
                ((TrafficFlowB) TrafegoB).exit();
                timer.cancel();
            }
        };

        timer.schedule(task, 15000);
    }
}