package ocean_animals;

public class Shark extends NeptuneAnimals{
    protected int starveTime;

    public Shark() {
        this.hunger = 0;
        this.locationX = 0;
        this.locationY = 0;
        breeding = false;
        eating = false;
        existing = true;
    }

    public Shark(int x, int y) {
        this.hunger = 0;
        this.locationX = x;
        this.locationY = y;
        breeding = false;
        eating = false;
        existing = true;
    }

    public Shark(int x, int y, int hungerFactor) {
        this.hunger = hungerFactor;
        this.locationX = x;
        this.locationY = y;
        breeding = false;
        eating = false;
        existing = true;
    }

    public Shark(int x, int y, boolean hasEaten) {
        if(hasEaten) {
            this.hunger -= 60;
        }
        this.locationX = x;
        this.locationY = y;
        breeding = false;
        eating = false;
        existing = true;
    }



    public Object breed() {
        this.breeding = true;
        return new Shark();
    }

    public void eat() {
        this.hunger -= 25;
    }

    public void die() {
        this.breeding = false;
        this.eating = false;
        this.existing = false;
    }
}
