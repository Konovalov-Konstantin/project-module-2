package island;

import java.util.List;

public abstract class HerbivorAnimal extends Animal {

    static volatile int eatenHerbal;

    public synchronized void eat() {
        if (fullness < 90 && fullness > 0) {
            List<Object> herbalsInLocation = this.location.getObjects();
            if (herbalsInLocation.size() > 1) {
                for (int i = 0; i < herbalsInLocation.size() - 1; i++) {
                    Object o = herbalsInLocation.get(i);
                    if (o instanceof Herbal) {
                        if (this.getClass() == Caterpillar.class) {
                            Caterpillar.eatenHerbal++;
                        } else if (this.getClass() == Deer.class) {
                            Deer.eatenHerbal++;
                        } else if (this.getClass() == Duck.class) {
                            Duck.eatenHerbal++;
                        } else if (this.getClass() == Goat.class) {
                            Goat.eatenHerbal++;
                        } else if (this.getClass() == Horse.class) {
                            Horse.eatenHerbal++;
                        } else if (this.getClass() == Mousse.class) {
                            Mousse.eatenHerbal++;
                        } else if (this.getClass() == Rabbit.class) {
                            Rabbit.eatenHerbal++;
                        } else if (this.getClass() == Sheep.class) {
                            Sheep.eatenHerbal++;
                        } else if (this.getClass() == Pig.class) {
                            Pig.eatenHerbal++;
                        } else if (this.getClass() == Buffalo.class) {
                            Buffalo.eatenHerbal++;
                        }
                        this.fullness += ((Grass) o).mass;
                        if (this.fullness >= 100) {
                            this.fullness = 100;
                        }
                    }
                }
            }
        } else if (fullness < 0) {
            this.wasDie++;
        }
    }

    public boolean checkLocation() {
        boolean check = false;
        try {
            List<Object> objects = this.location.getObjects();
            for (Object o : objects) {
                if (o instanceof Herbal) {
                    check = true;
                    break;
                }
                return check;
            }
        } catch (Exception ignore) {
        }
        return check;
    }

    public synchronized void incrementWasEaten() {
        this.wasEaten++;
    }
}
