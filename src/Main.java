import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        Timer timer = new Timer();

        Semaphore carros = new Semaphore(1);
        System.out.println("***  Cruzamento  ***");

        TrafficFlowA TrafegoA = new TrafficFlowA(carros);
        TrafficFlowB TrafegoB = new TrafficFlowB(carros);
        TrafegoA.start();
        TrafegoB.start();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                TrafegoA.exit();
                TrafegoB.exit();
                timer.cancel();
            }
        };

        timer.schedule(task, 15000);

        try {
            TrafegoA.join();
            TrafegoB.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Nenhum carro esta presente nos cruzamentos");
    }
}