package biology;

import acquarium.Environment;
import acquarium.EnvironmentPro;

/**
 * Class of a plant used in an acquarium
 */
public class Plant extends LivingBeing{
    /**
     * Depth where the plant should live.
     * It's expressed in cm
     */
    protected int depth;
    /**
     * Temperature that is correct
     * for the plant.
     * It's expressed in °C
     */
    protected int temperature;

    /**
     * Constructor of a plant
     * using the LivingBeing as a decorator
     * @param livingBeing living being part
     * @param depth ideal depth for the plant - cm
     * @param temperature ideal temperature for the plant - °C
     * @throws IllegalArgumentException if the arguments are not valid
     */
    public Plant(LivingBeing livingBeing, int depth, int temperature)throws IllegalArgumentException{
        super(livingBeing);
        this.setDepth(depth);
        this.setTemperature(temperature);
    }

    /**
     * Returns the ideal depth
     * for the plant
     * @return depth in cm
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Sets a new value of the ideal depth
     * @param depth new depth value
     * @throws IllegalArgumentException if the new value is not valid
     */
    public void setDepth(int depth) throws IllegalArgumentException{
        if(depth<0)
            throw new IllegalArgumentException("Depth must be positive");
        this.depth = depth;
    }

    /**
     * Returns the ideal temperature for the plant
     * @return temperature expressed in °C
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Sets a new value of ideal temperature
     * for the plant
     * @param temperature new temperature value
     * @throws IllegalArgumentException if the new temperature value is not valid
     */
    public void setTemperature(int temperature) throws IllegalArgumentException{
        if(temperature<-30 || temperature>40)
            throw new IllegalArgumentException("Temperature value outside the limits");
        this.temperature = temperature;
    }

    /**
     * Method that returns if a plant can live
     * in a certain environment
     * @param environment object that describes a certain environment
     * @return TRUE if the plant can live in the environment, FALSE if it can't
     */
    @Override
    public boolean isRightEnvironment(Environment environment){
        /* just deciding for what characteristics
        * an environment should be good for a plant */
        if((environment.getWaterType() == WaterType.salt && !this.saltWater()) || (environment.getWaterType() == WaterType.fresh && !this.freshWater()))
            return false;
        if(environment.getVolume()<Math.pow(this.depth, 3))
            return false;

        /* condition put to separate the controls
        * applied for a pro environment */
        if(!(environment instanceof EnvironmentPro))
            return true;

        EnvironmentPro environmentPro = (EnvironmentPro) environment;
        if(environmentPro.getPh()<3 || environmentPro.getPh()>11)
            return false;
        if(Math.abs(environmentPro.getTemperature()-this.temperature)>5)
            return false;
        if(this.depth>1000 && environmentPro.hasLights())
            return false;

        return true;
    }
}
