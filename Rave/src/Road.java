public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "ROAD " + length + " METERS";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " STARTED THE STAGE: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " ENDED THE STAGE: " + description);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
