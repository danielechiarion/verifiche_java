package gestioneRubrica;

import org.json.JSONObject;

/**
 * Classe che occupa dell'aggiunta di info aggiuntive
 * al contatto già esistente
 */
public class AltreInfo {
    /* definizione attributi */
    protected String nickname;
    protected String secondoTel;
    protected String email;

    /* creazione metodi get/set */
    public String getEmail(){ return this.email; }
    public String getSecondoTel(){ return this.secondoTel; }
    public String getNickname(){ return this.nickname; }

    /**
     * Metodo costruttore per le informazioni aggiuntive
     * @param nickname
     * @param secondoTel
     * @param email
     */
    public AltreInfo(String nickname, String secondoTel, String email){
        /* divido la stringa
        * in più informazioni da
        * assegnare ad ogni attributo */
        this.nickname=nickname;
        this.email=email;
        this.secondoTel=secondoTel;
    }

    /**
     * Metodo che visualizza le informazioni secondarie del contatto,
     * solo se sono state inserite
     * @return formato stringa
     */
    public String visualizza(){
        String output=""; //definisco la stringa

        /* controllo se i valori non sono
        * vuoti, in modo tale da poterli
        * stampare */
        if(!this.nickname.isBlank())
            output+=String.format("nickname: %s\t", this.nickname);
        if(!this.secondoTel.isBlank())
            output+=String.format("secondo telefono: %s\t", this.secondoTel);
        if(!this.email.isBlank())
            output+=String.format("email: %s", this.nickname);

        return output; //ritorno la stringa in output
    }

    /**
     * Metodo per convertire le informazioni secondarie in
     * un oggetto pronto al salvataggio in JSON
     * @return oggetto JSON
     */
    public JSONObject toJSON(){
        JSONObject object = new JSONObject(); //creazione nuovo oggetto

        /* inserimento attributi */
        object.put("nickname", this.nickname);
        object.put("secondoTel", this.secondoTel);
        object.put("email", this.email);

        return object; //ritorno l'oggetto con gli attributi inseriti
    }

    /**
     * Metodo per trasformare nuovamente un oggetto JSON
     * in un oggetto altreINFO
     * @param object - JSONObject da convertire
     * @return oggetto di AltreInfo
     */
    public static AltreInfo parseJSON(JSONObject object){
        /* ricavo informazioni */
        String nickname = object.getString("nickname");
        String secondoTel = object.getString("secondoTel");
        String email = object.getString("email");

        return new AltreInfo(nickname, secondoTel, email); //ritorno nuovo oggetto creato
    }
}
