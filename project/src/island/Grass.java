package island;

public class Grass extends Herbal implements Runnable {
    int mass = 1;
    Location location;
    public Grass(Location location) {
        this.location = location;
//        this.location.objects.add(this);
    }

    public void addGrassToLocation(Location location){
        location.objects.add(new Grass(location));
    }

    @Override
    public void run() {
        addGrassToLocation(location);
    }
}
