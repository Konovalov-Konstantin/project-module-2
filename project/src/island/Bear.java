package island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Bear extends PredatorAnimal implements Runnable {
    static volatile int wasEat = 0;
    static volatile int wasReproduced = 0;
    static volatile int wasRelocated = 0;
    static volatile int wasDie = 0;

    static int wasEatSumm = 0;
    static int wasReproducedSumm = 0;
    static int wasRelocatedSumm = 0;
    static int wasDieSumm = 0;

    public Bear(String title, int movementSpeed, boolean sex, Location location) {
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
                            case "class Horse" -> 40;
                            case "class Deer" -> 80;
                            case "class Rabbit" -> 80;
                            case "class Mousse" -> 90;
                            case "class Goat" -> 70;
                            case "class Sheep" -> 70;
                            case "class Duck" -> 10;
                            case "class Buffalo" -> 20;
                            default -> 0;
                        };
                        if (ThreadLocalRandom.current().nextInt(100) * possibility > 150 && this.fullness < 100) {
                            this.fullness += ((Animal) o).getMass();
                            wasEat++;
                            ((HerbivorAnimal) o).incrementWasEaten();
                            ((Animal) o).dieOrBeEaten();
                        }
                    } else if (o instanceof Anaconda) {
                        if (ThreadLocalRandom.current().nextInt(100) * 80 > 150 && this.fullness < 100) {
                            this.fullness += ((Anaconda) o).getMass();
                            wasEat++;
                            ((Anaconda) o).incrementWasEaten();
                            ((Anaconda) o).dieOrBeEaten();
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
            eat();
            reproduce();
        }
    }

    public static void getStatistic() {
        System.out.println("************************************************");
        System.out.println("Медведями было съедено " + wasEat + " животных");
        System.out.println("Было рождено " + wasReproduced + " медведей");
        System.out.println("Умерло  " + wasDie + " медведей");
        System.out.println("Сменили локацию  " + wasRelocated + " медведей");

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
