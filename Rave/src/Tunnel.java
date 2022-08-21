import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private static final Semaphore halfOfCarsAmountSemaphore = new Semaphore(Main.CARS_COUNT/2);
    public Tunnel() {
        this.length = 80;
        this.description = "TUNNEL " + length + " METERS";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                halfOfCarsAmountSemaphore.acquire(1);
                System.out.println(c.getName() + " PREPARING FOR THE STAGE(WAITING...): " + description);
                System.out.println(c.getName() + " STARTED THE STAGE: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " ENDED THE STAGE: " + description);
                halfOfCarsAmountSemaphore.release(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

