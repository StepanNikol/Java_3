import java.lang.management.ThreadInfo;
import java.sql.Struct;
import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private volatile boolean winner = true;
    public static final CountDownLatch carsToReady = new CountDownLatch(Main.CARS_COUNT);
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "PARTICIPANT #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " PREPARING");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " READY");
            carsToReady.countDown();
            if (carsToReady.getCount()==0){
                Main.showStartAnnouncement();
            }
            carsToReady.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if(Thread.activeCount()==Main.CARS_COUNT+2){
            System.out.println(this.getName()+" WIN!!!");
        }
        if(Thread.activeCount()==race.getAmountOfStages()){
            Main.showEndAnnouncement();
        }
    }
}

