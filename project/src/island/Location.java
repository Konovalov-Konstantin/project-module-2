package island;

import java.util.ArrayList;
import java.util.List;

public class Location {
    public static final int MAXWIDTH = 50;
    public static final int MAXHEIGHT = 50;
    int x;
    int y;

    List<Object> objects = new ArrayList<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    List<Object> getObjects() {
        return objects;
    }

    public static Location[][] createLocations() {
        Location[][] location = new Location[MAXWIDTH][MAXHEIGHT];
        for (int i = 0; i < MAXWIDTH; i++) {
            for (int j = 0; j < MAXHEIGHT; j++) {
                location[i][j] = new Location(i, j);
            }
        }
        return location;
    }

    public synchronized boolean checkLocation(Animal animal) {
        try {
            return animal.location == this;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
