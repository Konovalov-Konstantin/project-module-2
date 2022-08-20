package island;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Anaconda extends PredatorAnimal implements Runnable {

    int mass = 15;
    static volatile int wasEat = 0;
    static volatile int wasReproduced = 0;
    static volatile int wasEaten = 0;
    static volatile int wasDie = 0;

    static int wasEatSumm = 0;
    static int wasReproducedSumm = 0;
    static int wasEatenSumm = 0;
    static int wasDieSumm = 0;

    public Anaconda(String title, int movementSpeed, boolean sex, Location location) {
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
                    if (o instanceof HerbivorAnimal || o instanceof Fox) {
                        int possibility = switch (o.getClass().toString()) {
                            case "class Fox" -> 15;
                            case "class Rabbit" -> 20;
                            case "class Mousse" -> 40;
                            case "class Duck" -> 10;
                            default -> 0;
                        };
                        if (ThreadLocalRandom.current().nextInt(100) * possibility > 150) {
                            this.fullness += ((Animal) o).getMass();
                            if (this.fullness > 100) {
                                this.fullness = 100;
                            }
                            wasEat++;
                            if (o instanceof HerbivorAnimal) {
                                ((HerbivorAnimal) o).incrementWasEaten();
                            } else {
                                Fox.wasDie++;
                            }
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
            this.fullness -= (System.currentTimeMillis() - this.timeOfCreate);
            reproduce();
            eat();
        }
    }

    public static void getStatistic() {
        System.out.println("************************************************");
        System.out.println("Удавами было съедено " + wasEat + " животных");
        System.out.println("Было рождено " + wasReproduced + " удавов");
        System.out.println("Умерло или было съедено " + wasEaten + " удавов");
        System.out.println("Умерло  " + wasDie + " удавов");

        wasEatSumm += wasEat;
        wasReproducedSumm += wasReproduced;
        wasEatenSumm += wasEaten;
        wasDieSumm += wasDie;

        wasEat = 0;
        wasReproduced = 0;
        wasDie = 0;
    }

    public synchronized void incrementWasEaten() {
        wasEaten++;
    }
}
