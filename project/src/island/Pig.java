package island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Pig extends HerbivorAnimal implements Runnable {
    int mass = 400;
    static volatile int wasRelocated = 0;
    static volatile int wasEaten = 0;
    static volatile int eatenHerbal = 0;
    static volatile int wasReproduced = 0;

    static int wasRelocatedSumm = 0;
    static int wasEatenSumm = 0;
    static int eatenHerbalSumm = 0;
    static int wasReproducedSumm = 0;


    public Pig(String title, int movementSpeed, boolean sex, Location location) {
        this.title = title;
        this.speed = movementSpeed;
        this.location = location;
        this.sex = sex;
        this.timeOfCreate = System.currentTimeMillis();
        this.location.objects.add(this);
    }

    public synchronized void eat() {
        if (this.fullness < 90 && this.fullness > 0) {
            List<Object> objectsInLocation = this.location.getObjects();
            if (objectsInLocation.size() > 1) {

                for (int i = 0; i < objectsInLocation.size() - 1; i++) {
                    Object o = objectsInLocation.get(i);
                    if (o instanceof Herbal && this.fullness < 100) {
                        this.fullness += ((Grass) o).mass;
                        o = null;
                        eatenHerbal++;
                    }
                    else if (o instanceof Mousse && this.fullness < 100) {
                        System.out.println(((Animal) o).title);
                        if (ThreadLocalRandom.current().nextInt(100) * 50 > 150) {
                            this.fullness += ((Mousse) o).getMass();
                            wasEat++;
                            ((Mousse) o).incrementWasEaten();
                            ((Animal) o).dieOrBeEaten();
                        }
                    }
                 else if (o instanceof Caterpillar && this.fullness < 100) {
                    if (ThreadLocalRandom.current().nextInt(100) * 90 > 150) {
                        this.fullness += ((Caterpillar) o).getMass();
                        wasEat++;
                        ((Caterpillar) o).incrementWasEaten();
                        ((Animal) o).dieOrBeEaten();
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
        System.out.println("Умерло или было съедено " + wasEaten + " кабанов");
        System.out.println("Кабаны съели " + eatenHerbal + " кг растений");
        System.out.println("Было рождено " + wasReproduced + " кабанов");
        System.out.println("Сменили локацию  " + wasRelocated + " кабанов");

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
