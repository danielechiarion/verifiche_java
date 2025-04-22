package account;

import list.IdCounter;

import java.util.ArrayList;
import java.util.Objects;

import operation.*;
import acquarium.AcquariumHome;

public class Costumer implements IdCounter, Cloneable {
    /* ID counter */
    private static int idCounter;
    /**
     * Name of the user
     */
    protected String name;
    /**
     * Surname of the user
     */
    protected String surname;
    /**
     * Email of the user
     */
    protected String email;
    /**
     * List of acquariums bought
     * from the user
     */
    protected ArrayList<AcquariumHome> acquariumList;
    /**
     * List of buying made from the user
     */
    protected ArrayList<Sale> buyingList;
    /**
     * ID of the customer
     */
    protected int id;

    /**
     * Constructor of the customer
     * @param name name of the customer
     * @param surname surname of the customer
     * @param email email of the customer
     */
    public Costumer(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.acquariumList = new ArrayList<>();
        this.buyingList = new ArrayList<>();
        this.id = IdCounter.newId(idCounter);
    }

    /**
     * Returns the name of the user
     * @return name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the surname of the user
     * @return surname of the user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the email of the user
     * @return email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns a clone of the list
     * of acquariums
     * @return clone of the acquarium list
     */
    public ArrayList<AcquariumHome> getAcquariumList() {
        ArrayList<AcquariumHome> clone = new ArrayList<>();
        for(AcquariumHome currentAcquarium : this.acquariumList)
            clone.add(currentAcquarium.clone());

        return clone;
    }

    /**
     * Returns a clone of the list
     * of buyings of the user
     * @return clone of the buying list
     */
    public ArrayList<Sale> getBuyingList() {
        ArrayList<Sale> clone = new ArrayList<>();
        for(Sale currentSale : this.buyingList)
            clone.add(currentSale.clone());

        return clone;
    }

    /**
     * Returns the ID of the user
     * @return user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets a new name
     * @param name new name of the costumer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets a new surname
     * @param surname new surname of the costumer
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Sets a new email
     * @param email new email of the costumer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Add a new acquarium to the
     * customer's collection
     * @param acquariumHome acquarium to add
     * @throws IllegalStateException if the acquarium already exists in the list
     */
    public void addAcquarium(AcquariumHome acquariumHome)throws IllegalStateException{
        if(this.acquariumList.contains(acquariumHome))
            throw new IllegalStateException("Acquarium already exists in the list");

        this.acquariumList.add(acquariumHome);
    }

    /**
     * Adds a new buying to the list of the costumer
     * @param sale sale made by the shop
     * @throws IllegalStateException if the buying already exists in the list
     */
    public void addBuying(Sale sale)throws IllegalStateException{
        if(this.buyingList.contains(sale))
            throw new IllegalStateException("Sell already exists in the list");

        sale.setCostumer(null);
        this.buyingList.add(sale);
    }

    /**
     * Equals method based on the ID
     * of the costumer
     * @param object object to be compared
     * @return TRUE if the objects are equal
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Costumer costumer)) return false;
        return this.id == costumer.id;
    }

    /**
     * Returns a clone of the costumer
     * @return clone of the costumer
     */
    @Override
    public Costumer clone(){
        try{
            return (Costumer) super.clone();
        }catch(CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * toString of the costumer
     * @return String format of the customer
     */
    public String toString(){
        return String.format("%s %s - %s\t%05d", this.name, this.surname, this.email, this.id);
    }
}
