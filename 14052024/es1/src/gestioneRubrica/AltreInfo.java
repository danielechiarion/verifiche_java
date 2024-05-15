package gestioneRubrica;

/**
 * Classe che occupa dell'aggiunta di info aggiuntive
 * al contatto già esistente
 */
public class AltreInfo {
    /* definizione attributi */
    protected String nickname;
    protected String secondoTel;
    protected String email;

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
}
