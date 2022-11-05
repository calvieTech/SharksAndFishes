package ocean_animals;

public abstract class NeptuneAnimals {
    int hunger;
    int locationX, locationY;
    boolean breeding;
    boolean existing;
    boolean eating;



    protected abstract Object breed();
    protected abstract void eat();
    protected abstract void die();

}
