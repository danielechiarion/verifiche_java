package biology;

import list.Catalog;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Class that manages the list of elements available
 */
public class CatalogAnimals implements Catalog{
    /**
     * List of animals
     */
    protected ArrayList<Animal> animalList;

    /**
     * Constructor of a new
     * animals catalog
     */
    public CatalogAnimals(){
        this.animalList = new ArrayList<>();
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
        if(!(obj instanceof Animal))
            throw new ClassCastException("Object cannot be converted into animal");

        Animal animal = (Animal) obj;
        if(!this.animalList.contains(animal))
            throw new NoSuchElementException("animal not found");

        this.animalList.remove(animal);
    }

    /**
     * Returns a clone of the original
     * catalog of animals
     * @return list of cloned animals
     */
    @Override
    public ArrayList<Object> getCatalog(){
        ArrayList<Object> clone = new ArrayList<>();
        for(Animal currentanimal : this.animalList)
            clone.add(currentanimal.clone());

        return clone;
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
