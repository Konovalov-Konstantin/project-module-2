package island;

import java.util.List;

public class Caterpillar extends HerbivorAnimal implements Runnable {
    double mass = 0.01;
    static volatile int wasEaten = 0;
    static volatile int eatenHerbal = 0;
    static volatile int wasReproduced = 0;

    static int wasEatenSumm = 0;
    static int eatenHerbalSumm = 0;
    static int wasReproducedSumm = 0;

    public synchronized double getCaterpillarMass() {
        return mass;
    }
     public Caterpillar(String title, int movementSpeed, boolean sex, Location location) {
        this.title = title;
        this.speed = movementSpeed;
        this.location = location;
        this.sex = sex;
        this.timeOfCreate = System.currentTimeMillis();
        this.location.objects.add(this);
    }

    @Override
    public synchronized void eat() {
        if (fullness < 90) {
            List<Object> herbalsInLocation = this.location.getObjects();
            if (herbalsInLocation.size() > 1) {
                for (int i = 0; i < herbalsInLocation.size()-1; i++) {
                    Object o = herbalsInLocation.get(i);
                    if (o instanceof Herbal) {
                        this.fullness += ((Grass) o).mass / 10;
                        eatenHerbal++;
                        if (this.fullness >= 100) {
                            this.fullness = 100;
                        }
                    }
                }
            }
        }
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
        System.out.println("Умерло или было съедено " + wasEaten + " гусениц");
        System.out.println("Гусеницы съели " + eatenHerbal + " кг растений");
        System.out.println("Было рождено " + wasReproduced + " гусениц");

        wasEatenSumm += wasEaten;
        eatenHerbalSumm += eatenHerbal;
        wasReproducedSumm += wasReproduced;

        wasEaten = 0;
        wasReproduced = 0;
        eatenHerbal = 0;
    }
}
