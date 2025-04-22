package acquarium;

import biology.Animal;
import biology.LivingBeing;
import list.Catalog;
import list.IdCounter;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Class of a home acquarium
 */
public class AcquariumHome implements IdCounter, Cloneable, Catalog {
    /* ID counter */
    private static int idCounter;

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
     * ID of the acquarium
     */
    protected int id;

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
        this.id = IdCounter.newId(idCounter);
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
     * Return the ID of the acquarium
     * @return ID of the acquarium
     */
    public int getId() {
        return id;
    }

    /**
     * Method to add a new living being in the list
     * @param obj object to add
     * @throws Exception if the object cannot be converted into a living being or if it already exists
     */
    @Override
    public void addElement(Object obj)throws Exception{
        if(!(obj instanceof LivingBeing))
            throw new ClassCastException("Object cannot be converted into living being");

        LivingBeing livingBeing = (LivingBeing) obj;
        if(this.population.contains(livingBeing))
            throw new IllegalStateException("Living being already exists");

        this.population.add(livingBeing);
    }

    /**
     * Method to remove a living being from the
     * list
     * @param obj object to be removed
     * @throws Exception if the object can't be converted to a living being or if it is not found
     */
    @Override
    public void removeElement(Object obj)throws Exception {
        if(!(obj instanceof LivingBeing))
            throw new ClassCastException("Object cannot be converted into living being");

        LivingBeing livingBeing = (LivingBeing) obj;
        if(!this.population.contains(livingBeing))
            throw new NoSuchElementException("animal not found");

        this.population.remove(livingBeing);
    }

    /**
     * Return the living being by the index given
     * @param index index of the object
     * @return clone of the living being
     * @throws IndexOutOfBoundsException if the index is out of the bounds of the list
     */
    @Override
    public Object getElement(int index)throws IndexOutOfBoundsException{
        if(index<0 || index>=population.size())
            throw new IndexOutOfBoundsException("Arraylist index out of bounds");

        return this.population.get(index).clone();
    }

    /**
     * Method that returns a clone of the living being
     * by the animal given.
     * It's usually used in researchs
     * @param element equal object
     * @return clone of the animal found
     * @throws Exception if the living being is not found or the element can't be converted into it
     */
    @Override
    public Object getElement(Object element)throws Exception{
        if(!(element instanceof LivingBeing))
            throw new ClassCastException("Object cannot be converted into living being");

        LivingBeing livingBeing = (LivingBeing) element;
        if(!this.population.contains(livingBeing))
            throw new NoSuchElementException("animal not found");

        return this.population.get(this.population.indexOf(livingBeing));
    }

    /**
     * Equals method that compares
     * to AcquariumHome objects by the ID given
     * @param object object to be compared
     * @return TRUE if the objects are equal
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof AcquariumHome that)) return false;
        return this.id == that.id;
    }

    /**
     * toString of a Home Acquarium
     * @return string format with the data of the home acquarium
     */
    @Override
    public String toString(){
        return String.format("%s\n%d max animals\t%d max plants\t%d total population",
                this.environment.toString(), this.maxAnimals, this.maxPlants, this.population.size());
    }

    /**
     * Generate a clone of the acquarium
     * @return clone of the AcquariumHome object
     */
    @Override
    public AcquariumHome clone(){
        try{
            return (AcquariumHome) super.clone();
        }catch(CloneNotSupportedException e){
            return null;
        }
    }
}
