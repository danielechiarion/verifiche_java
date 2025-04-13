package biology;

import acquarium.Environment;

/**
 * Abstract class with the characteristics of a living being
 */
public abstract class LivingBeing implements Water, Cloneable {
    /* ATTRIBUTES */
    /**
     * Species of the living being
     */
    protected String species;
    /**
     * Type of water where the living being can live
     */
    protected WaterType waterType;
    /**
     * Price of the living being
     */
    protected double price;

    /**
     * Dimensions of the animal in cm
     */
    protected double dimensions;

    /* CONSTRUCTORS */
    /**
     * Constructor of a living being
     * @param species species of the living being
     * @param waterType the type of water where the living being can live
     * @param price price of the living being
     * @throws IllegalArgumentException if at least one of the attributes is not valid
     */
    public LivingBeing(String species, WaterType waterType, double price, double dimensions)throws IllegalArgumentException{
        this.species = species;
        this.waterType = waterType;
        this.setPrice(price);
    }

    /**
     * Other version of the constructor
     * used for pattern decorators
     * @param livingBeing object that need to be instantiated
     */
    public LivingBeing(LivingBeing livingBeing){
        this.species = livingBeing.species;
        this.waterType = livingBeing.waterType;
        this.price = livingBeing.price;
    }

    /* GETTERS AND SETTERS */
    /**
     * Returns the species
     * @return species of the living being
     */
    public String getSpecies() {
        return species;
    }

    /**
     * Sets a new value for the species
     * @param species new value of the species
     */
    public void setSpecies(String species) {
        this.species = species;
    }

    /**
     * Sets a new type of water
     * @param waterType new value of water type
     */
    public void setWaterType(WaterType waterType) {
        this.waterType = waterType;
    }

    /**
     * Gets the price of the living being
     * @return price of the living being
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets a new price for the living being
     * @param price new price
     * @throws IllegalArgumentException if the price is less than 0
     */
    public void setPrice(double price) throws IllegalArgumentException{
        if(price<0)
            throw new IllegalArgumentException("Price can't be less than 0");
        this.price = price;
    }

    /**
     * Returns the dimensions of the animal
     * @return dimensions of the animal
     */
    public double getDimensions() {
        return dimensions;
    }

    /**
     * Sets the dimensions of the animal
     * @param dimensions dimension of the animal
     */
    public void setDimensions(double dimensions) throws IllegalArgumentException{
        if(dimensions<=0)
            throw new IllegalArgumentException("Dimensions of a living being can't be <= 0 cm");
        this.dimensions = dimensions;
    }

    /* OTHER METHODS */
    /**
     * Returns if the living being can live in salt water
     * @return TRUE if the animal can live in salt water
     */
    @Override
    public boolean saltWater() {
        return this.waterType == WaterType.salt || this.waterType == WaterType.both;
    }

    /**
     * Returns if the living being can live in fresh water
     * @return TRUE if the animal can live in fresh water
     */
    @Override
    public boolean freshWater() {
        return this.waterType == WaterType.fresh || this.waterType == WaterType.both;
    }

    /**
     * Returns if two objects are equal by
     * the species they belong to
     * @param object object to be compared
     * @return TRUE if the two objects are equal
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof LivingBeing that)) return false;
        return this.species.equalsIgnoreCase(that.species);
    }

    /**
     * Return string format of the living being
     * @return string format of the attributes of the object
     */
    @Override
    public String toString(){
        return String.format("%s - %.1fcm\t%s\t%.2f", this.species, this.dimensions, this.waterType.nameValue, this.price);
    }

    /**
     * Method that returns if the living being
     * can live in a certain environment
     * @param environment object that describes a certain environment
     * @return TRUE if the animal can live in that environment
     */
    public abstract double isRightEnvironment(Environment environment);

    /**
     * Returns a new clone of the object
     * @return clone of this object
     */
    @Override
    public LivingBeing clone(){
        try{
            return (LivingBeing) super.clone();
        }catch (CloneNotSupportedException e){
            return null;
        }
    }
}
