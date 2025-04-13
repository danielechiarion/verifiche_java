package acquarium;

import biology.WaterType;

/**
 * Class that contains the characteristics
 * of an environment
 */
public class Environment implements Cloneable{
    /* CONSTANTS */
    /**
     * Minimum volume an environment can have
     * expressed in litres
     */
    private static final int MINVOLUME = 20;

    /* ATTRIBUTES */
    /**
     * Volume of the environment
     * express in litres
     */
    protected int volume;
    /**
     * Type of water of the environment
     */
    protected WaterType waterType;

    /* CONSTRUCTORS */
    public Environment(int volume, WaterType waterType)throws IllegalArgumentException{
        this.waterType = waterType;
        this.setVolume(volume);
    }

    /* GETTERS AND SETTERS */
    /**
     * Returns the volume
     * @return volume in litres
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Returns the type of water
     * @return type of water
     */
    public WaterType getWaterType() {
        return waterType;
    }

    /**
     * Sets the new volume of the environment
     * @param volume new volume
     * @throws IllegalArgumentException if the volume is not valid
     */
    public void setVolume(int volume) throws IllegalArgumentException{
        if(volume<MINVOLUME)
            throw new IllegalArgumentException("Volume value can't be less than "+MINVOLUME);
        this.volume = volume;
    }

    /**
     * Sets the water type
     * @param waterType new value of water type
     */
    public void setWaterType(WaterType waterType) {
        this.waterType = waterType;
    }

    /* OTHER METHODS */
    /**
     * Returns a clone of the environment
     * @return clone of this object
     */
    @Override
    public Environment clone(){
        try{
            return (Environment) super.clone();
        }catch(CloneNotSupportedException e){
            return null;
        }
    }

    /**
     * Returns the string format of the environment
     * @return string format of the object
     */
    @Override
    public String toString(){
        return String.format("ACQUARIO: %dL - %s", this.volume, this.waterType.nameValue);
    }
}
