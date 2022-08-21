public class Main {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("IMPORTANT ANNOUNCEMENT!!! >>> PREPARATION!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
    }
    public static void showStartAnnouncement() {
        System.out.println("IMPORTANT ANNOUNCEMENT!!! >>> RACE HAS STARTED!!!");
    }
    public static void showEndAnnouncement() {
        System.out.println("IMPORTANT ANNOUNCEMENT!!! >>> RACE HAS ENDED!!!");
    }
}



