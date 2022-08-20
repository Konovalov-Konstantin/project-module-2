package island;

import java.util.List;
import java.util.Random;

public abstract class Animal {
    String title;
    int speed;
    int mass;
    long timeOfCreate;
    boolean sex;
    static int wasEat;
    int wasDie;
    int wasEaten;
    public volatile boolean isLive = true;


    public boolean getSex() {
        return sex;
    }

    int fullness = 80; // сытость
    Location location;

    public Location getLocation() {
        return location;
    }

    public int getMass() {
        return this.mass;
    }

    public abstract void eat();

    public synchronized void dieOrBeEaten() {
        try {
            this.location.objects.remove(this);
            this.isLive = false;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public synchronized void move() {
        if (this.fullness > 0) {
            this.fullness -= this.speed * 30;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.speed * (System.currentTimeMillis() - this.timeOfCreate) > 600) {
                Location loc = this.location;
                if (loc.x < Location.MAXWIDTH && loc.y < Location.MAXHEIGHT) {
                    loc.x = loc.x + 1;
                    loc.y = loc.y + 1;
                } else {
                    loc.x = loc.x - 1;
                    loc.y = loc.y - 1;
                }
                if (this.getClass() == Wolf.class) {
                    Wolf.wasRelocated++;
                } else if (this.getClass() == Deer.class) {
                    Deer.wasRelocated++;
                } else if (this.getClass() == Horse.class) {
                    Horse.wasRelocated++;
                } else if (this.getClass() == Rabbit.class) {
                    Rabbit.wasRelocated++;
                } else if (this.getClass() == Goat.class) {
                    Goat.wasRelocated++;
                } else if (this.getClass() == Sheep.class) {
                    Sheep.wasRelocated++;
                } else if (this.getClass() == Fox.class) {
                    Fox.wasRelocated++;
                } else if (this.getClass() == Duck.class) {
                    Duck.wasRelocated++;
                } else if (this.getClass() == Mousse.class) {
                    Mousse.wasRelocated++;
                } else if (this.getClass() == Eagle.class) {
                    Eagle.wasRelocated++;
                } else if (this.getClass() == Pig.class) {
                    Pig.wasRelocated++;
                } else if (this.getClass() == Bear.class) {
                    Bear.wasRelocated++;
                } else if (this.getClass() == Buffalo.class) {
                    Buffalo.wasRelocated++;
                }
                this.timeOfCreate = System.currentTimeMillis();
            }
        } else {
            this.dieOrBeEaten();
        }
    }

    public synchronized Animal reproduce() {
        List<Object> animalsList = this.location.getObjects();
        Animal result = null;
        try {
            if (animalsList.size() > 1 && (new Random().nextInt(0, 10) > 8)) {
                for (Object o : animalsList) {
                    if (o instanceof Wolf && ((Wolf) o).getSex()) {
                        result = new Wolf("new Wolf", 0, new Random().nextBoolean(), this.getLocation());
                        Wolf.wasReproduced++;
                    } else if (o instanceof Deer && ((Deer) o).getSex()) {
                        result = new Deer("new Deer", 0, new Random().nextBoolean(), this.getLocation());
                        Deer.wasReproduced++;
                    } else if (o instanceof Horse && ((Horse) o).getSex()) {
                        result = new Horse("new Horse", 0, new Random().nextBoolean(), this.getLocation());
                        Horse.wasReproduced++;
                    } else if (o instanceof Anaconda && ((Anaconda) o).getSex()) {
                        result = new Anaconda("new Anaconda", 0, new Random().nextBoolean(), this.getLocation());
                        Anaconda.wasReproduced++;
                    } else if (o instanceof Rabbit && ((Rabbit) o).getSex()) {
                        result = new Rabbit("new Rabbit", 0, new Random().nextBoolean(), this.getLocation());
                        Rabbit.wasReproduced++;
                    } else if (o instanceof Goat && ((Goat) o).getSex()) {
                        result = new Goat("new Goat", 0, new Random().nextBoolean(), this.getLocation());
                        Goat.wasReproduced++;
                    } else if (o instanceof Sheep && ((Sheep) o).getSex()) {
                        result = new Sheep("new Sheep", 0, new Random().nextBoolean(), this.getLocation());
                        Sheep.wasReproduced++;
                    } else if (o instanceof Fox && ((Fox) o).getSex()) {
                        result = new Fox("new Fox", 0, new Random().nextBoolean(), this.getLocation());
                        Fox.wasReproduced++;
                    } else if (o instanceof Duck && ((Duck) o).getSex()) {
                        result = new Duck("new Duck", 0, new Random().nextBoolean(), this.getLocation());
                        Duck.wasReproduced++;
                    } else if (o instanceof Caterpillar && ((Caterpillar) o).getSex()) {
                        result = new Caterpillar("new Caterpillar", 0, new Random().nextBoolean(), this.getLocation());
                        Caterpillar.wasReproduced++;
                    } else if (o instanceof Mousse && ((Mousse) o).getSex()) {
                        result = new Mousse("new Mousse", 0, new Random().nextBoolean(), this.getLocation());
                        Mousse.wasReproduced++;
                    } else if (o instanceof Eagle && ((Eagle) o).getSex()) {
                        result = new Eagle("new Eagle", 0, new Random().nextBoolean(), this.getLocation());
                        Eagle.wasReproduced++;
                    } else if (o instanceof Pig && ((Pig) o).getSex()) {
                        result = new Pig("new Pig", 0, new Random().nextBoolean(), this.getLocation());
                        Pig.wasReproduced++;
                    } else if (o instanceof Bear && ((Bear) o).getSex()) {
                        result = new Bear("new Bear", 0, new Random().nextBoolean(), this.getLocation());
                        Bear.wasReproduced++;
                    } else if (o instanceof Buffalo && ((Buffalo) o).getSex()) {
                        result = new Buffalo("new Buffalo", 0, new Random().nextBoolean(), this.getLocation());
                        Buffalo.wasReproduced++;
                    }
                }
            }
        } catch (Exception ignore) {  // здесь ConcurrentModificationException
        }
        return result;
    }

    public static void printFinalStatistic() {
        System.out.println("=================== ИТОГИ =============================");
        System.out.println("Всего было рождено " + (
                Wolf.wasReproducedSumm +
                        Deer.wasReproducedSumm +
                        Horse.wasReproducedSumm +
                        Anaconda.wasReproducedSumm +
                        Rabbit.wasReproducedSumm +
                        Goat.wasReproducedSumm +
                        Sheep.wasReproducedSumm +
                        Fox.wasReproducedSumm +
                        Duck.wasReproducedSumm +
                        Caterpillar.wasReproducedSumm +
                        Mousse.wasReproducedSumm +
                        Eagle.wasReproducedSumm +
                        Pig.wasReproducedSumm +
                        Bear.wasReproducedSumm +
                        Buffalo.wasReproducedSumm) + " животных");

        System.out.println("Всего умерло " + (
                Wolf.wasDieSumm +
                        Anaconda.wasDieSumm +
                        Fox.wasDieSumm +
                        Eagle.wasDieSumm +
                        Bear.wasDieSumm) + " животных");


        System.out.println("Всего было съедено хищниками " + (
                Wolf.wasEatSumm +
                        Anaconda.wasEatSumm +
                        Fox.wasEatSumm +
                        Eagle.wasEatSumm +
                        Bear.wasEatSumm) + " животных");


        System.out.println("Всего сменили локацию " + (
                Wolf.wasRelocatedSumm +
                        Deer.wasRelocatedSumm +
                        Horse.wasRelocatedSumm +
                        Rabbit.wasRelocatedSumm +
                        Goat.wasRelocatedSumm +
                        Sheep.wasRelocatedSumm +
                        Fox.wasRelocatedSumm +
                        Duck.wasRelocatedSumm +
                        Mousse.wasRelocatedSumm +
                        Eagle.wasRelocatedSumm +
                        Pig.wasRelocatedSumm +
                        Bear.wasRelocatedSumm +
                        Buffalo.wasRelocatedSumm) + " животных");


        System.out.println("Всего было съедено " + (
                Deer.eatenHerbalSumm +
                        Horse.eatenHerbalSumm +
                        Rabbit.eatenHerbalSumm +
                        Goat.eatenHerbalSumm +
                        Sheep.eatenHerbalSumm +
                        Duck.eatenHerbalSumm +
                        Caterpillar.eatenHerbalSumm +
                        Mousse.eatenHerbalSumm +
                        Pig.eatenHerbalSumm +
                        Buffalo.eatenHerbalSumm) + " КГ растений");
    }
}


