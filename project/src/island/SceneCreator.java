package island;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SceneCreator {
    static Location randomLocation = MyClass.location[new Random().nextInt(0, Location.MAXWIDTH)][new Random().nextInt(0, Location.MAXHEIGHT)];

    public static List<Object> createScene() {
        List<Object> objectsList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Anaconda anaconda = new Anaconda("Anakonda" + i, 1, new Random().nextBoolean(), randomLocation);
            Wolf wolf = new Wolf("Wolf" + i, new Random().nextInt(2, 5), new Random().nextBoolean(), randomLocation);
            Fox fox = new Fox("Fox" + i, new Random().nextInt(4, 5), new Random().nextBoolean(), randomLocation);
            Eagle eagle = new Eagle("Eagle" + i, new Random().nextInt(7, 8), new Random().nextBoolean(), randomLocation);
            Bear bear = new Bear("Bear" + i, new Random().nextInt(4, 5), new Random().nextBoolean(), randomLocation);

            Caterpillar caterpillar = new Caterpillar("Caterpillar" + i, 1, new Random().nextBoolean(), randomLocation);
            Deer deer = new Deer("Deer" + i, new Random().nextInt(1, 3), new Random().nextBoolean(), randomLocation);
            Duck duck = new Duck("Duck" + i, new Random().nextInt(1, 3), new Random().nextBoolean(), randomLocation);
            Goat goat = new Goat("Goat" + i, new Random().nextInt(1, 2), new Random().nextBoolean(), randomLocation);
            Horse horse = new Horse("Horse" + i, new Random().nextInt(4, 7), new Random().nextBoolean(), randomLocation);
            Mousse mousse = new Mousse("Mouse" + i, new Random().nextInt(1, 2), new Random().nextBoolean(), randomLocation);
            Rabbit rabbit = new Rabbit("Rabbit" + i, new Random().nextInt(2, 3), new Random().nextBoolean(), randomLocation);
            Sheep sheep = new Sheep("Sheep" + i, new Random().nextInt(1, 3), new Random().nextBoolean(), randomLocation);
            Pig pig = new Pig("Pig" + i, new Random().nextInt(3, 4), new Random().nextBoolean(), randomLocation);
            Buffalo buffalo = new Buffalo("Buffalo" + i, new Random().nextInt(3, 4), new Random().nextBoolean(), randomLocation);

            objectsList.add(wolf);
            objectsList.add(deer);
            objectsList.add(horse);
            objectsList.add(anaconda);
            objectsList.add(rabbit);
            objectsList.add(goat);
            objectsList.add(sheep);
            objectsList.add(fox);
            objectsList.add(duck);
            objectsList.add(caterpillar);
            objectsList.add(mousse);
            objectsList.add(eagle);
            objectsList.add(pig);
            objectsList.add(bear);
            objectsList.add(buffalo);
        }
        return objectsList;
    }
}
