package island;

public class Horse extends HerbivorAnimal implements Runnable {
    int mass = 400;
    static volatile int wasEaten = 0;
    static volatile int wasRelocated = 0;
    static volatile int wasReproduced = 0;
    static volatile int eatenHerbal = 0;

    static int wasRelocatedSumm = 0;
    static int wasEatenSumm = 0;
    static int eatenHerbalSumm = 0;
    static int wasReproducedSumm = 0;
    public Horse(String title, int movementSpeed, boolean sex, Location location) {
        this.title = title;
        this.speed = movementSpeed;
        this.location = location;
        this.sex = sex;
        this.timeOfCreate = System.currentTimeMillis();
        this.location.objects.add(this);
    }

    @Override
    public void run() {
        if (checkLocation() && this.isLive) {
            move();
            eat();
            reproduce();
        }
    }


    public static void getStatistic() {
        System.out.println("************************************************");
        System.out.println("Умерло или было съедено " + wasEaten + " лошадей");
        System.out.println("Лошади съели " + eatenHerbal + " кг растений");
        System.out.println("Было рождено " + wasReproduced + " лошадей");
        System.out.println("Сменили локацию  " + wasRelocated + " лошадей");

        wasRelocatedSumm += wasRelocated;
        wasEatenSumm += wasEaten;
        eatenHerbalSumm += eatenHerbal;
        wasReproducedSumm += wasReproduced;

        wasEaten = 0;
        wasReproduced = 0;
        eatenHerbal = 0;
        wasRelocated = 0;
    }
}
