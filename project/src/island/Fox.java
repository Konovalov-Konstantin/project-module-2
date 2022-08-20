package island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Fox extends PredatorAnimal implements Runnable {

    int mass = 8;
    static volatile int wasEat = 0;
    static volatile int wasEaten = 0;
    static volatile int wasReproduced = 0;
    static volatile int wasRelocated = 0;
    static volatile int wasDie = 0;

    static int wasEatSumm = 0;
    static int wasEatenSumm = 0;
    static int wasReproducedSumm = 0;
    static int wasRelocatedSumm = 0;
    static int wasDieSumm = 0;

    public Fox(String title, int movementSpeed, boolean sex, Location location) {
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
            List<Object> animalsInLocation = this.location.getObjects();
            if (animalsInLocation.size() > 1) {
                for (int i = 0; i < animalsInLocation.size() - 1; i++) {
                    Object o = animalsInLocation.get(i);
                    if (o instanceof HerbivorAnimal) {
                        synchronized (o) {
                            int possibility = switch (o.getClass().toString()) {
                                case "class Rabbit" -> 70;
                                case "class Mousse" -> 90;
                                case "class Duck" -> 60;
                                case "class Caterpillar" -> 40;
                                default -> 0;
                            };
                            if (ThreadLocalRandom.current().nextInt(100) * possibility > 150) {
                                this.fullness += ((Animal) o).getMass();
                                if (this.fullness > 100) {
                                    this.fullness = 100;
                                }
                                wasEat++;
                                ((HerbivorAnimal) o).incrementWasEaten();

                                ((Animal) o).dieOrBeEaten();
                            }
                        }
                    }
                }
            }
        } else if (this.fullness <= 0) {
            wasDie++;
        }

    }

    @Override
    public void run() {
        if (checkLocation() || this.isLive) {
            move();
            reproduce();
            eat();
        }
    }

    public synchronized void incrementWasEaten() {
        wasEaten++;
    }

    public static void getStatistic() {
        System.out.println("************************************************");
        System.out.println("Лисами было съедено " + wasEat + " животных");
        System.out.println("Было съедено " + wasEaten + " лис");
        System.out.println("Умерло  " + wasDie + " лис");
        System.out.println("Было рождено " + wasReproduced + " лис");
        System.out.println("Сменили локацию  " + wasRelocated + " лис");

        wasEatSumm += wasEat;
        wasEatenSumm += wasEaten;
        wasReproducedSumm += wasReproduced;
        wasRelocatedSumm += wasRelocated;
        wasDieSumm += wasDie;

        wasEat = 0;
        wasReproduced = 0;
        wasEaten = 0;
        wasDie = 0;
        wasRelocated = 0;
    }
}
