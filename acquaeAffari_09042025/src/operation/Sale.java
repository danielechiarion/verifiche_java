package operation;

import account.Costumer;
import biology.LivingBeing;
import list.IdCounter;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class used to sell animals and plants
 */
public class Sale implements IdCounter, Cloneable {
    /**
     * Maximum aggressive animals
     */
    private static final int maxAggressiveAnimals = 2;
    /**
     * Maximum non aggressive animals
     */
    private static final int maxNotAggressiveAnimals = 5;

    /* ID counter */
    private static int idCounter;

    /**
     * Number code of the sell
     */
    protected int sellNumber;
    /**
     * Customer that buys the stuff
     */
    protected Costumer costumer;
    /**
     * Date and time of the buying
     */
    protected LocalDateTime date;
    /**
     * Products bought
     */
    protected ArrayList<LivingBeing> products;
    /**
     * Aggressive animals counter
     */
    protected int aggressiveAnimalsCounter;
    /**
     * Not aggressive animals counter
     */
    protected int notAggressiveAnimalsCounter;

    /**
     * Method to generate a sell
     * @param costumer costumer for the selling
     */
    public Sale(Costumer costumer){
        this.costumer = costumer;
        this.notAggressiveAnimalsCounter = 0;
        this.aggressiveAnimalsCounter = 0;
        this.products = new ArrayList<>();
        this.date = LocalDateTime.now();
        this.sellNumber = IdCounter.newId(idCounter);
    }

    /**
     * Return the number of the sell
     * @return sell code
     */
    public int getSellNumber() {
        return sellNumber;
    }

    /**
     * Returns the costumer
     * @return clone of the costumer
     */
    public Costumer getCostumer() {
        return costumer.clone();
    }

    /**
     * Returns the date of the sale
     * @return date of the sale
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets a new costumer
     * @param costumer new costumer
     */
    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }
}
