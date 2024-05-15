package gestioneRubrica;

/**
 * tipi di contratti disponibili per un contatto.
 * Se ne può selezionare solo 1
 */
enum tipoContratto{abitazione, cellulare, aziendale, undefined};

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
    protected String[] altreInfo;

    /**
     * Metodo costruttore con aggiunta di informazioni
     * extra, se inserite dall'utente
     * @param nome
     * @param cognome
     * @param telefono
     * @param tipo
     * @param infoExtra
     */
    public Contatto(String nome, String cognome, String telefono, tipoContratto tipo, String infoExtra){
        this.nome=nome;
        this.cognome=cognome;
        this.telefono=telefono;
        this.tipo=tipo;
        if(!infoExtra.isBlank()) //prima controllo se la stringa contiene dei valori
            this.altreInfo=infoExtra.split(","); //le informazioni extra sono separate dalla virgola
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

    public String visualizza(){
        return String.format("Nome: %s\tCognome: %s\nTelefono: %s\tContratto: %s",
                this.nome, this.cognome, this.telefono, this.tipo.name());
    }
}
