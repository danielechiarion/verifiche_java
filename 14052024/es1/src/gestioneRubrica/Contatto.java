package gestioneRubrica;

import org.json.JSONObject;

/**
 * Classe per la gestione di un singolo contatto,
 * con metodi e attrituti
 */
public class Contatto {
    /* lista di attributi disponibili
    * per la classe contatto */
    protected String nome;
    protected String cognome;
    protected String telefono;
    protected tipoContratto tipo;
    protected AltreInfo altreInfo;
    protected boolean stato;

    /**
     * Metodo costruttore con aggiunta di informazioni
     * extra, se inserite dall'utente
     * @param nome
     * @param cognome
     * @param telefono
     * @param tipo
     * @param infoExtra
     */
    public Contatto(String nome, String cognome, String telefono, tipoContratto tipo, String infoExtra, boolean nascosto){
        this.nome=nome;
        this.cognome=cognome;
        this.telefono=telefono;
        this.tipo=tipo;
        if(!infoExtra.isBlank()) //prima controllo se la stringa contiene dei valori
        {
            String[] info = infoExtra.split(",");//le informazioni extra sono separate dalla virgola
            this.altreInfo=new AltreInfo(info[0], info[1], info[2]); //creo un nuovo oggetto con le informazioni extra
        }
        this.stato=nascosto;
    }

    /* utilizzo dei vari metodi get/set */
    /* metodi get/set attributi */
    public String getNome(){
        return this.nome;
    }
    public String getCognome(){
        return this.cognome;
    }
    public String getTelefono(){
        return this.telefono;
    }
    public tipoContratto getTipo(){
        return this.tipo;
    }
    public boolean getStato(){
        return this.stato;
    }

    public void setNome(String x){
        this.nome=x;
    }
    public void setCognome(String x){
        this.cognome=x;
    }
    public void setTelefono(String x){
        this.telefono=x;
    }
    public void setTipo(tipoContratto tipo){
        this.tipo=tipo;
    }
    public void setStato(){
        this.stato=!this.stato;
    }

    /**
     * Metodo che visualizza lo stato del contatto,
     * con tutte le informazioni necessarie per
     * identificarlo
     * @return formato stringa dei dati
     */
    public String visualizza(boolean siCategoria){
        /* prima assegno tutti i valori
        * essenziali alla creazione di un nuovo contatto */
        String output=String.format("Nome: %s\tCognome: %s\nTelefono: %s\tContratto: %s",
                this.nome, this.cognome, this.telefono, this.tipo.name());

        /* poi verifico se ci sono anche informazioni
        * ulteriori da aggiungere,
        * in tal caso le accodo alla stringa già creata */
        if(this.altreInfo!=null && !this.altreInfo.visualizza().isBlank())
            output+=String.format("\n%s", this.altreInfo.visualizza());

        if(siCategoria){
            if(this.stato)
                output+=String.format("\nNASCOSTO");
            else
                output+=String.format("\nVISIBILE");
        }

        return output; //ritorno risultato da stampare
    }

    /**
     * Metodo che converte un contatto in un JSON
     * Object
     * @return JSON Object creato
     */
    public JSONObject toJSON(){
        JSONObject object = new JSONObject(); //creazione oggetto

        /* inserimento attributi */
        object.put("nome", this.nome);
        object.put("cognome", this.cognome);
        object.put("telefono", this.telefono);
        object.put("tipo", this.tipo.name()); //salvo in stringa per evitare problemi
        if(this.altreInfo!=null)
            object.put("altreInfo", this.altreInfo.toJSON());
        object.put("stato", this.stato);

        return object; //ritorno oggetto creato
    }

    /**
     * Metodo per convertire un oggetto JSON
     * in un oggetto della classe Contatto
     * @param object JSON da convertire
     * @return Contatto convertito
     */
    public static Contatto parseJSON(JSONObject object){
        /* dichiarazione variabili
        * per informazioni aggiuntive */
        String nickname, secondoTel, email;

        /* recupero informazioni */
        String nome = object.getString("nome");
        String cognome = object.getString("cognome");
        String telefono = object.getString("telefono");
        tipoContratto tipo = tipoContratto.valueOf(object.getString("tipo"));
        AltreInfo altreInfo;
        try{
            altreInfo = AltreInfo.parseJSON(object.getJSONObject("altreInfo"));
            nickname= altreInfo.getNickname();
            email= altreInfo.getEmail();
            secondoTel= altreInfo.getSecondoTel();
        }catch(Exception e){
            email="/";
            secondoTel="/";
            nickname="/";
        }

        boolean nascosto = object.getBoolean("stato");

        return new Contatto(nome, cognome, telefono, tipo,
                nickname+","+secondoTel+","+email, nascosto); //creo nuovo oggetto
    }
}
