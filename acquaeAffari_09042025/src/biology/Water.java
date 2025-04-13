package biology;

/**
 * Interface that referers to the status of the water
 */
public interface Water {
    /**
     * Method that indicates if the animal can
     * live in a fresh water acquarium
     * @return TRUE if the animal/plant can live in fresh water
     */
    boolean freshWater();

    /**
     * Method that indicates if the animal can
     * live in a salt water acquarium
     * @return TRUE if the animal/plant can live in salt water
     */
    boolean saltWater();
}
