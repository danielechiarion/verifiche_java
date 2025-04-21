package biology;

import acquarium.Environment;
import acquarium.EnvironmentPro;

import java.util.EventListener;

public class Animal extends LivingBeing{
    /**
     * If it's TRUE indicates that this animal
     * is aggressive
     */
    protected boolean aggressive;
    /**
     * If it's TRUE means that this animal
     * can live with others in the acquarium
     */
    protected boolean cohabitant;

    /**
     * Constructor of an animal using
     * the LivingBeing as a decorator
     * @param livingBeing object with living being characteristics
     * @param aggressive boolean that indicates if the animal is aggressive
     * @param cohabitant boolean that indicates if the animal can live with other species in the acquarium
     */
    public Animal(LivingBeing livingBeing, boolean aggressive, boolean cohabitant){
        super(livingBeing);
        this.aggressive = aggressive;
        this.cohabitant = cohabitant;
    }

    /**
     * Returns if the animal is aggressive
     * @return TRUE if the animal is aggressive
     */
    public boolean isAggressive() {
        return aggressive;
    }

    /**
     * Returns if the animal can live
     * with other species in the acquarium
     * @return TRUE if the animal can live with other species
     */
    public boolean isCohabitant() {
        return cohabitant;
    }

    /**
     * Method that indicates if the environment is
     * good for the animal
     * @param environment object that describes a certain environment
     * @return TRUE if the characteristics of the enviroment match the characteristics of the animal
     */
    @Override
    public boolean isRightEnvironment(Environment environment) {
        if(environment.getVolume() > 1000 && !this.cohabitant)
            return false;

        /* just to separate the controls
        * for a pro environment */
        if(!(environment instanceof EnvironmentPro))
            return true;

        EnvironmentPro environmentPro = (EnvironmentPro) environment;
        if(environmentPro.getPh()<3 || environmentPro.getPh()>11)
            return false;
        if(this.isAggressive() && !environmentPro.hasLights())
            return false;

        return true;
    }
}
