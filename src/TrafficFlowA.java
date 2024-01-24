import java.util.Random;
import java.util.concurrent.Semaphore;

public class TrafficFlowA extends Thread{
    private Semaphore carro;
    private boolean flag = false;
    public TrafficFlowA(Semaphore carro) {
        this.carro = carro;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            while (!flag) {
                carro.acquire();
                System.out.println("Carros passando no cruzamento A");
                sleep(rand.nextInt(6000));
                carro.release();
                System.out.println("Carros esperando no cruzamento A");
                sleep(rand.nextInt(4000,7000));
            }
            System.out.println("Carros passando no cruzamento A");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void exit() {
        flag = true;
    }
}
