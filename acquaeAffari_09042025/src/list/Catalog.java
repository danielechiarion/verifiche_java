package list;

import java.util.ArrayList;

/**
 * Interface that contains all the methods
 * for a catalog
 */
public interface Catalog{
    /**
     * Method that adds a new element to
     * the catalog
     * @param obj object to add
     * @throws Exception if the object is not of the specified class or it already exists
     */
    void addElement(Object obj) throws Exception;

    /**
     * Method that removes an element
     * from the catalog
     * @param obj object to be removed
     * @throws Exception if the element already exists
     */
    void removeElement(Object obj) throws Exception;

    /**
     * Returns the clone of the elements
     * of the catalog
     * @return clone of the list of objects
     */
    ArrayList<Object> getCatalog();

    /**
     * Gets the clone of the element by
     * the index given
     * @param index index of the object
     * @return clone of the object
     * @throws Exception if the object already exists
     */
    Object getElement(int index) throws Exception;

    /**
     * Gets a clone of the element
     * by the equal element given
     * @param obj equal object
     * @return clone of the object
     * @throws Exception if the object is not found
     */
    Object getElement(Object obj) throws Exception;
}
