package biology;

import list.Catalog;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class that manages the list
 * of plants available
 */
public class CatalogPlants implements Catalog{
    /**
     * List of plants
     */
    protected ArrayList<Plant> plantList;

    /**
     * Constructor of a new plants
     * catalog
     */
    public CatalogPlants(){
        this.plantList = new ArrayList<>();
    }

    /**
     * Method to add a new plant in the list
     * @param obj object to add
     * @throws Exception if the object cannot be converted into a plant or if the plant already exists
     */
    @Override
    public void addElement(Object obj)throws Exception{
        if(!(obj instanceof Plant))
            throw new ClassCastException("Object cannot be converted into Plant");

        Plant plant = (Plant) obj;
        if(this.plantList.contains(plant))
            throw new IllegalStateException("Plant already exists");

        this.plantList.add(plant);
    }

    /**
     * Method to remove a plant from the
     * list
     * @param obj object to be removed
     * @throws Exception if the object can't be converted to a plant or if the plant is not found
     */
    @Override
    public void removeElement(Object obj)throws Exception {
        if(!(obj instanceof Plant))
            throw new ClassCastException("Object cannot be converted into Plant");

        Plant plant = (Plant) obj;
        if(!this.plantList.contains(plant))
            throw new NoSuchElementException("Plant not found");

        this.plantList.remove(plant);
    }

    /**
     * Returns a clone of the original
     * catalog of plants
     * @return list of cloned plants
     */
    @Override
    public ArrayList<Object> getCatalog(){
        ArrayList<Object> clone = new ArrayList<>();
        for(Plant currentPlant : this.plantList)
            clone.add(currentPlant.clone());

        return clone;
    }

    /**
     * Return the plant by the index given
     * @param index index of the object
     * @return clone of the plant
     * @throws IndexOutOfBoundsException if the index is out of the bounds of the list
     */
    @Override
    public Object getElement(int index)throws IndexOutOfBoundsException{
        if(index<0 || index>=plantList.size())
            throw new IndexOutOfBoundsException("Arraylist index out of bounds");

        return this.plantList.get(index).clone();
    }

    /**
     * Method that returns a clone of the plant
     * by the plant given.
     * It's usually used in researchs
     * @param element equal object
     * @return clone of the plant found
     * @throws Exception if the plant is not found or the element can't be converted into a plant
     */
    public Object getElement(Object element)throws Exception{
        if(!(element instanceof Plant))
            throw new ClassCastException("Object cannot be converted into Plant");

        Plant plant = (Plant) element;
        if(!this.plantList.contains(plant))
            throw new NoSuchElementException("Plant not found");

        return this.plantList.get(this.plantList.indexOf(plant));
    }
}
