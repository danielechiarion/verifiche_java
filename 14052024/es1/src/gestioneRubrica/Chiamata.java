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


        return chiamata; //ritorno l'oggetto
    }

    /* public Chiamata parseJSON(JSONObject object){

    } */
}
