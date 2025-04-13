package acquarium;

import biology.WaterType;

public class EnvironmentPro extends Environment {
    /* CONSTANTS */
    /**
     * Minimum temperature value
     */
    private final int MINTEMPERATURE = -10;
    /**
     * Maximum temperature value
     */
    private final int MAXTEMPERATURE = 40;

    /* ATTRIBUTES */
    /**
     * PH of the water
     */
    protected double ph;
    /**
     * Temperature of the water expressed in °C
     */
    protected double temperature;
    /**
     * Boolean that indicates if the environments has lights on
     */
    protected boolean lights;

    /* CONSTRUCTOR */
    public EnvironmentPro(int volume, WaterType waterType, double ph, double temperature, boolean lights)throws IllegalArgumentException{
        super(volume, waterType);

        this.setPh(ph);
        this.setTemperature(temperature);
        this.lights = lights;
    }

    /* GETTERS AND SETTERS */
    /**
     * Returns the PH value of the water
     * @return PH value
     */
    public double getPh() {
        return ph;
    }

    /**
     * Sets a new  PH value
     * @param ph new ph value
     * @throws IllegalArgumentException if the PH value is not valid
     */
    public void setPh(double ph) throws IllegalArgumentException{
        if(ph<0 || ph>14)
            throw new IllegalArgumentException("PH value must be between 0 and 14");
        this.ph = ph;
    }

    /**
     * Returns the temperature value
     * @return temperature value in °C
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * Sets a new temperature value
     * @param temperature new temperature value in °C
     * @throws IllegalArgumentException if the value is not between the MINTEMPERATURE and MAXTEMPERATURE decided
     */
    public void setTemperature(double temperature) throws IllegalArgumentException{
        if(temperature<MINTEMPERATURE || temperature>MAXTEMPERATURE)
            throw new IllegalArgumentException("Temperature value can't be less than "+MINTEMPERATURE+"°C or more than "+MAXTEMPERATURE+"°C");
        this.temperature = temperature;
    }

    /**
     * Returns if the environment has lights
     * @return TRUE if the environment has lights
     */
    public boolean hasLights() {
        return lights;
    }

    /**
     * Sets the lights status
     * @param lights new lights value
     */
    public void setLights(boolean lights) {
        this.lights = lights;
    }

    /* OTHER METHODS */
    /**
     * Returns the string format of the pro environment
     * @return string format of the object
     */
    @Override
    public String toString(){
        return String.format("%s\tPH: %.1f - Temperatura: %.1f°C\tStatus luci: %b",
                super.toString(), this.ph, this.temperature, this.lights);
    }
}
