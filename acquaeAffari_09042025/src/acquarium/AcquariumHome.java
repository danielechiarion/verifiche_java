package acquarium;

import biology.Animal;
import biology.LivingBeing;
import list.Catalog;
import list.IdCounter;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class of a home acquarium
 */
public class AcquariumHome implements IdCounter, Cloneable, Catalog {
    /**
     * Environment of the acquarium
     */
    protected Environment environment;
    /**
     * List of animals and plants inside
     * the acquarium
     */
    protected ArrayList<LivingBeing> population;
    /**
     * Maximum number of animals
     * in the acquarium
     */
    protected int maxAnimals;
    /**
     * Maximum number of plants
     * in the acquarium
     */
    protected int maxPlants;
    /**
     * Counter of the animals
     */
    protected int animalCounter;
    /**
     * Counter of the plants
     */
    protected int plantCounter;

    /**
     * Constructor of a new acquarium
     * @param environment environment of the acquarium
     * @param maxAnimals maximum number of animals in the acquarium
     * @param maxPlants maximum number of plants in the acquarium
     * @throws IllegalArgumentException is the arguments of the method are not valid
     */
    public AcquariumHome(Environment environment, int maxAnimals, int maxPlants)throws IllegalArgumentException{
        this.environment = environment;
        this.setMaxAnimals(maxAnimals);
        this.setMaxPlants(maxPlants);
        this.animalCounter = 0;
        this.plantCounter = 0;
    }

    /**
     * Return a clone of the environment
     * @return clone of the environment
     */
    public Environment getEnvironment() {
        return environment.clone();
    }

    /**
     * Returns a clone of the population
     * @return clone of the list of living beings
     */
    public ArrayList<LivingBeing> getPopulation() {
        ArrayList<LivingBeing> population = new ArrayList<>();
        for(Object obj : this.getCatalog())
            population.add((LivingBeing) obj);

        return population;
    }

    /**
     * Return the maximum number of animals
     * @return max number of animals
     */
    public int getMaxAnimals() {
        return maxAnimals;
    }

    /**
     * Return the maximum number of plants
     * @return max number of plants
     */
    public int getMaxPlants() {
        return maxPlants;
    }

    /**
     * Returns a clone of the catalog
     * @return clone of the elements of the catalog
     */
    @Override
    public ArrayList<Object> getCatalog(){
        ArrayList<Object> clone = new ArrayList<>();
        for(LivingBeing currentLivingBeing : this.population)
            clone.add(currentLivingBeing.clone());
        return clone;
    }

    /**
     * Sets the maximum number of animals
     * @param maxAnimals new max animals value
     * @throws IllegalArgumentException if the new value is not valid
     */
    public void setMaxAnimals(int maxAnimals) throws IllegalArgumentException{
        if(maxAnimals<0)
            throw new IllegalArgumentException("Max animals value can't be less than 0");
        this.maxAnimals = maxAnimals;
    }

    /**
     * Sets the maximum number of plants
     * @param maxPlants new max plants value
     * @throws IllegalArgumentException if the new value is not valid
     */
    public void setMaxPlants(int maxPlants) throws IllegalArgumentException{
        if(maxPlants<0)
            throw new IllegalArgumentException("Max plants value can't be less than 0");
        this.maxPlants = maxPlants;
    }

    /**
     * Method to add a new animal in the list
     * @param obj object to add
     * @throws Exception if the object cannot be converted into a animal or if the animal already exists
     */
    @Override
    public void addElement(Object obj)throws Exception{
        if(!(obj instanceof Animal))
            throw new ClassCastException("Object cannot be converted into animal");

        Animal animal = (Animal) obj;
        if(this.animalList.contains(animal))
            throw new IllegalStateException("animal already exists");

        this.animalList.add(animal);
    }

    /**
     * Method to remove a animal from the
     * list
     * @param obj object to be removed
     * @throws Exception if the object can't be converted to a animal or if the animal is not found
     */
    @Override
    public void removeElement(Object obj)throws Exception {
        if(!(obj instanceof living being))
            throw new ClassCastException("Object cannot be converted into animal");

        Animal animal = (Animal) obj;
        if(!this.animalList.contains(animal))
            throw new NoSuchElementException("animal not found");

        this.animalList.remove(animal);
    }

    /**
     * Return the animal by the index given
     * @param index index of the object
     * @return clone of the animal
     * @throws IndexOutOfBoundsException if the index is out of the bounds of the list
     */
    @Override
    public Object getElement(int index)throws IndexOutOfBoundsException{
        if(index<0 || index>=animalList.size())
            throw new IndexOutOfBoundsException("Arraylist index out of bounds");

        return this.animalList.get(index).clone();
    }

    /**
     * Method that returns a clone of the animal
     * by the animal given.
     * It's usually used in researchs
     * @param element equal object
     * @return clone of the animal found
     * @throws Exception if the animal is not found or the element can't be converted into a animal
     */
    public Object getElement(Object element)throws Exception{
        if(!(element instanceof Animal))
            throw new ClassCastException("Object cannot be converted into animal");

        Animal animal = (Animal) element;
        if(!this.animalList.contains(animal))
            throw new NoSuchElementException("animal not found");

        return this.animalList.get(this.animalList.indexOf(animal));
    }
}
