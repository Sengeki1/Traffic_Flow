import java.util.Random;
import java.util.concurrent.Semaphore;

public class TrafficFlowB extends Thread{
    private Semaphore carro;
    private boolean flag = false;
    public TrafficFlowB(Semaphore carro) {
        this.carro = carro;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            sleep(1000);
            while (!flag) {
                System.out.println("Carros esperando no cruzamento B");
                carro.acquire();
                System.out.println("Carros passando no cruzamento B");
                sleep(rand.nextInt(5000));
                carro.release();
                System.out.println("Carros esperando no cruzamento B");
                sleep(rand.nextInt(5000,8000));
            }
            System.out.println("Nenhum carro presente no cruzamento B");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void exit() {
        flag = true;
    }
}
