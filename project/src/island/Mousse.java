package island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Mousse extends HerbivorAnimal implements Runnable {
    double mass = 0.05;
    static volatile int wasRelocated = 0;
    static volatile int wasEaten = 0;
    static volatile int eatenHerbal = 0;
    static volatile int wasReproduced = 0;

    static int wasRelocatedSumm = 0;
    static int wasEatenSumm = 0;
    static int eatenHerbalSumm = 0;
    static int wasReproducedSumm = 0;


    public Mousse(String title, int movementSpeed, boolean sex, Location location) {
        this.title = title;
        this.speed = movementSpeed;
        this.location = location;
        this.sex = sex;
        this.timeOfCreate = System.currentTimeMillis();
        this.location.objects.add(this);
    }

    @Override
    public synchronized void eat() {
        if (this.fullness < 90 && this.fullness > 0) {
            List<Object> objectsInLocation = this.location.getObjects();
            if (objectsInLocation.size() > 1) {
                for (int i = 0; i < objectsInLocation.size() - 1; i++) {
                    Object o = objectsInLocation.get(i);
                    if (o instanceof Caterpillar) {
                        if (ThreadLocalRandom.current().nextInt(100) * 90 > 150) {
                            this.fullness += ((Caterpillar) o).getCaterpillarMass();
                            if (this.fullness > 100) {
                                this.fullness = 100;
                            }
                            wasEat++;
                            ((HerbivorAnimal) o).incrementWasEaten();

                            ((Animal) o).dieOrBeEaten();
                        }
                    } else if (o instanceof Herbal) {
                        eatenHerbal++;
                        this.fullness += ((Grass) o).mass;
                        if (this.fullness >= 100) {
                            this.fullness = 100;
                        }
                    }
                }
            } else if (this.fullness <= 0) {
                this.dieOrBeEaten();
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
        System.out.println("Умерло или было съедено " + wasEaten + " мышей");
        System.out.println("Мыши съели " + eatenHerbal + " кг растений");
        System.out.println("Было рождено " + wasReproduced + " мышей");
        System.out.println("Сменили локацию  " + wasRelocated + " мышей");

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
