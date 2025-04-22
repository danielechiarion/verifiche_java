package list;

/**
 * Interface used to manage
 * the use of ID
 */
public interface IdCounter {
    /**
     * Method used to generate a new ID
     * from the last one used and updates the value
     * of the last ID. The class needs to have
     * an ID counter variable.
     * @param id last ID used
     * @return new ID
     */
    static int newId(int id){
        return id++;
    }
}
