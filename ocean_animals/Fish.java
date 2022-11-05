package ocean_animals;

public class Fish extends NeptuneAnimals{

    public Fish() {
        this.hunger = 60;
        this.locationX = 0;
        this.locationY = 0;
        breeding = false;
        existing = true;
        eating = false;
    }

    public Fish(int x, int y) {
        this.hunger = 60;
        this.locationX = x;
        this.locationY = y;
        breeding = false;
        eating = false;
        existing = true;
    }

    public Fish breed() {
        this.breeding = true;
        return new Fish();
    }

    public void eat() {
        this.hunger -= 10;
    }

    public void die() {
        existing = false;
        breeding = false;
        eating = false;
    }

}
