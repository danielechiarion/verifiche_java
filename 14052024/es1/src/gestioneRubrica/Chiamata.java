package gestioneRubrica;
import utility.dataOra;

import org.json.*;

/** Classe che gestisce una chiamata
 * e tutti gli attributi necessari **/
public class Chiamata {
    /* definizione attributi */
    protected Contatto contatto;
    protected dataOra dataOra;
    protected int durata;

    /* creazione metodi get/set */
    public Contatto getContatto(){
        return this.contatto;
    }
    public dataOra getDataOra(){
        return this.dataOra;
    }
    public int getDurata(){
        return this.durata;
    }

    /**
     * Definizione metodo costruttore
     * @param contatto a cui l'utente ha effettuato la telefonata
     * @param dataOra in cui è partita la telefonata
     * @param durata espressa in minuti
     */
    public Chiamata(Contatto contatto, dataOra dataOra, int durata){
        this.contatto=contatto;
        this.dataOra=dataOra;
        this.durata=durata;
    }

    /**
     * Visualizza in output in formato tabellare
     * la chiamata effettuata con tutti i dati disponibili
     * @return formato stringa della chiamata
     */
    public String visualizza(){
        return String.format("%s\t%s %s %s\t%d min", this.dataOra.visualizza(), contatto.getNome(), contatto.getCognome(), contatto.getTelefono(), this.durata);
    }

    /**
     * Metodo che trasforma la chiamata in
     * un oggetto JSON, pronto al salvataggio
     * @return oggetto JSON della chiamata
     */
    public JSONObject toJSON(){
        JSONObject chiamata = new JSONObject(); //creo un nuovo oggetto

        /* imposto i dati essenziali da salvare */
        chiamata.put("contatto", this.contatto.toJSON());
        chiamata.put("dataOra", this.dataOra.toJSON());
        chiamata.put("durata", this.durata);

        return chiamata; //ritorno l'oggetto
    }

    /**
     * Metodo che prende l'oggetto chiamata utilizzando
     * i dati principali per restituire una stringa formattata
     * da salvare in un file CSV
     * @return stringa formattata della chiamata
     */
    public String toCSV(){
        /* recupero data e ora separate */
        String[] infoData = this.dataOra.visualizza().split("-");

        /* restituzione formato stringa da inserire nel
        * file CSV */
        return String.format("%s,%s,%d,%s,%s,%s", infoData[0], infoData[1],
                this.durata, this.contatto.getTelefono(), this.contatto.getCognome(), this.contatto.getNome());
    }

    /**
     * Metodo che legge da un file JSON una chiamata e la converte
     * in oggetto della classe chiamata
     * @param object in JSON da convertire
     * @return oggetto della classe chiamata convertito
     */
    public static Chiamata parseJSON(JSONObject object){
        /* ricavo i dati */
        Contatto contatto = Contatto.parseJSON(object.getJSONObject("contatto"));
        dataOra dataOra = utility.dataOra.parseJSON(object.getJSONObject("dataOra"));
        int durata = object.getInt("durata");

        return new Chiamata(contatto, dataOra, durata); //ritorno il nuovo contatto creato
    }

    /**
     * Metodo che confronta due chiamate e stabilisce definitivamente
     * se la chiamata associata al metodo viene prima o dopo
     * rispetto a quella fornita da parametro.
     * In questo caso, non è ammesso indicare che le due chiamate sono uguali, perchè questo
     * metodo occorrerà per stabilire la cronologia delle chiamate
     * @param altraChiamata - chiamata da confrontare
     * @return <0 se la prima chiamata viene prima
     *          >0 se la seconda chiamata viene prima
     */
    public int compareTo(Chiamata altraChiamata){
        if(this.dataOra.compareTo(altraChiamata.dataOra)<0 ||
        this.dataOra.compareTo(altraChiamata.dataOra)==0 && this.durata<=altraChiamata.durata)
            return -1;
        else
            return 1;
    }
}
