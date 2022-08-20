package island;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MyClass {
    static Location[][] location = Location.createLocations();

    public static void main(String[] args) throws InterruptedException {

        // пул потоков для создания растений
        ScheduledExecutorService executorServiceHerbal = Executors.newScheduledThreadPool(1);
        executorServiceHerbal.scheduleAtFixedRate(new Grass(SceneCreator.randomLocation),0,300,TimeUnit.MILLISECONDS);

        // пул потоков для создания животных
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 3; i++) {
            System.out.println();
            System.out.println("**********  Цикл № " + i + "**********");
            List<Object> objectsList = SceneCreator.createScene();

            for (Object o : objectsList) {
                executorService.execute((Runnable) o);
            }
            Thread.sleep(1000);

            Wolf.getStatistic();
            Deer.getStatistic();
            Horse.getStatistic();
            Anaconda.getStatistic();
            Rabbit.getStatistic();
            Goat.getStatistic();
            Sheep.getStatistic();
            Fox.getStatistic();
            Duck.getStatistic();
            Caterpillar.getStatistic();
            Mousse.getStatistic();
            Eagle.getStatistic();
            Pig.getStatistic();
            Bear.getStatistic();
            Buffalo.getStatistic();
        }
        executorServiceHerbal.shutdown();
        executorService.shutdown();
        Animal.printFinalStatistic();
    }
}
