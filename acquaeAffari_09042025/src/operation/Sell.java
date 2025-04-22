package operation;

import account.Costumer;
import biology.LivingBeing;
import list.IdCounter;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sell implements IdCounter, Cloneable {
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
    public Sell(Costumer costumer){
        this.costumer = costumer;
        this.notAggressiveAnimalsCounter = 0;
        this.aggressiveAnimalsCounter = 0;
        this.products = new ArrayList<>();
        this.date = LocalDateTime.now();
        this.sellNumber = IdCounter.newId(idCounter);
    }


}
