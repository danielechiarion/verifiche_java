package gestioneRubrica;

/**
 * Classe che consente la gestione
 * di una rubrica e la possibilità di avere
 * account riservati
 */
public class Rubrica {
    /* definizione attributi */
    protected String password;
    protected Contatto[] contattiNormali;
    protected Contatto[] contattiNascosti;
    protected int maxLista;
    protected String[] registroNormale;
    protected String[] registroNascosto;

    /* definizione dei metodi get/set */

    /* per tutti gli attributi definiti come "nascosti"
    * è possibile accedere ai dati solo se un valore booleano,
    * dato dal controllo della password, è impostato a TRUE */
    public String getPassword(){ return this.password; }
    public int getMaxLista(){ return this.maxLista; }
    public Contatto[] getContattiNormali(){ return this.contattiNormali; }
    public Contatto[] getContattiNascosti(boolean value){
        /* qui occorre fare un controllo
        * in più in quanto si dà la lista completa di contatti
        * nascosti solo dopo autorizzazione */
        if(value)
            return this.contattiNascosti;
        return null;
    }

    public String[] getRegistroNormale(){
        return this.registroNormale;
    }
    public String[] getRegistroNascosto(boolean value){
        if(value)
            return this.registroNascosto;

        return null;
    }
    public Contatto getContattoNormale(int pos){
        return this.contattiNormali[pos];
    }
    public Contatto getContattoNascosto(int pos){
        return this.contattiNascosti[pos];
    }

    public void setPassword(String password){ this.password=password; }
    public void setMaxLista(int maxLista){ this.maxLista=maxLista; }

    
}
