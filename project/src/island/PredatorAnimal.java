package island;

import java.util.List;

public abstract class PredatorAnimal extends Animal {
    public boolean checkLocation() {
        boolean check = false;
        try {
            synchronized (this) {
                List<Object> objects = this.location.getObjects();
                for (Object o : objects) {
                    if (o instanceof HerbivorAnimal) {
                        check = true;
                    }
                    return check;
                }
            }
        } catch (Exception ignore) {
        }
        return check;
    }
}
