package biology;

public enum WaterType {
    fresh("Acqua dolce"),
    salt("Acqua salata"),
    both("Acqua dolce e salata");

    public String nameValue;
    WaterType(String name){
        this.nameValue = name;
    }

    /**
     * Returns the corresponding values of
     * water types into an array of strings,
     * used for a menu
     * @return array of strings of water types
     */
    public static String[] WaterTypeToArrayString(){
        WaterType[] waterTypes = WaterType.values();
        String[] output = new String[waterTypes.length+1];

        output[0] = "TIPI DI ACQUA";
        for(int i=0;i<waterTypes.length;i++){
            output[i+1] = waterTypes[i].nameValue;
        }

        return output;
    }

    /**
     * Returns the WaterType value by the index given
     * @param index index of the element in WaterType array
     * @return correspoding WaterType value
     * @throws IndexOutOfBoundsException if the index is not valid
     */
    public static WaterType getWaterType(int index)throws IndexOutOfBoundsException{
        if(index<0 || index>=WaterType.values().length)
            throw new IndexOutOfBoundsException("WaterType index not valid");
        return WaterType.values()[index];
    }
}
