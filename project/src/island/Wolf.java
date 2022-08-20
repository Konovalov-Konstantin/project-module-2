package island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends PredatorAnimal implements Runnable {
    static volatile int wasEat = 0;
    static volatile int wasReproduced = 0;
    static volatile int wasRelocated = 0;
    static volatile int wasDie = 0;

    static int wasEatSumm = 0;
    static int wasReproducedSumm = 0;
    static int wasRelocatedSumm = 0;
    static int wasDieSumm = 0;

    public Wolf(String title, int movementSpeed, boolean sex, Location location) {
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
                        int possibility = switch (o.getClass().toString()) {
                            case "class Horse" -> 10;
                            case "class Deer" -> 15;
                            case "class Rabbit" -> 60;
                            case "class Goat" -> 60;
                            case "class Sheep" -> 70;
                            case "class Buffalo" -> 10;
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

    public static void getStatistic() {
        System.out.println("************************************************");
        System.out.println("Волками было съедено " + wasEat + " животных");
        System.out.println("Было рождено " + wasReproduced + " волчат");
        System.out.println("Умерло  " + wasDie + " волков");
        System.out.println("Сменили локацию  " + wasRelocated + " волков");

        wasEatSumm += wasEat;
        wasReproducedSumm += wasReproduced;
        wasRelocatedSumm += wasRelocated;
        wasDieSumm += wasDie;

        wasEat = 0;
        wasReproduced = 0;
        wasDie = 0;
        wasRelocated = 0;
    }
}
