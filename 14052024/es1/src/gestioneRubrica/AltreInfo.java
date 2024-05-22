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
        if(!this.nickname.isBlank() && !this.nickname.equals("/"))
            output+=String.format("nickname: %s\t", this.nickname);
        if(!this.secondoTel.isBlank() && !this.secondoTel.equals("/"))
            output+=String.format("secondo telefono: %s\t", this.secondoTel);
        if(!this.email.isBlank() && !this.email.equals("/"))
            output+=String.format("email: %s", this.email);

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
        if(!this.nickname.isBlank())
         object.put("nickname", this.nickname);
        if(!this.nickname.isBlank())
            object.put("secondoTel", this.secondoTel);
        if(!this.nickname.isBlank())
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
        /* dichiarazione variabili */
        String nickname="", secondoTel="", email="";
        int contaErrori=0;

        /* ricavo informazioni,
        * controllando che siano state inserite*/
        try{
            nickname = object.getString("nickname");
        }catch(Exception e){
            contaErrori++;
        }
        try {
            secondoTel = object.getString("secondoTel");
        }catch (Exception e){
            contaErrori++;
        }
        try{
            email = object.getString("email");
        }catch(Exception e){
            contaErrori++;
        }

        if(contaErrori<3)
            return new AltreInfo(nickname, secondoTel, email); //ritorno nuovo oggetto creato
        else
            return null;
    }
}
