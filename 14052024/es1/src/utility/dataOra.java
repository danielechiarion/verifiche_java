package utility;

import org.json.JSONObject;

import static utility.tools.*;

/**
 * Classe per la gestione di data e ora,
 * con metodi e controlli
 */
public class dataOra {
    /* definizione attributi */
    protected int anno;
    protected int mese;
    protected int giorno;
    protected int ora;
    protected int min;

    /* creazione metodi get/set */
    public int getAnno(){ return this.anno; }
    public int getMese(){ return this.mese; }
    public int getGiorno(){ return this.giorno; }
    public int getOra(){ return this.ora; }
    public int getMin(){ return this.min; }

    /**
     * Metodo costruttore della classe data-ora
     * @param anno
     * @param mese
     * @param giorno
     * @param ora
     * @param min
     */
    public dataOra(int anno, int mese, int giorno, int ora, int min){
        this.anno=anno;
        this.mese=mese;
        this.giorno=giorno;
        this.ora=ora;
        this.min=min;
    }

    /**
     * Metodo per controllare se l'anno inserito è bisestile
     * @return TRUE se l'anno è bisestile,
     *          FALSE se non lo è
     */
    public boolean isBisestile()
    {
        /* dichiarazione variabili */
        boolean result;

        /* per essere booleano il numero deve essere divisibile per 4 E,
         * se multiplo di 100, deve essere divisibile anche per 400 */
        if((this.anno % 4 == 0 && this.anno % 100 != 0) || this.anno % 400 == 0)
            result=true;
        else
            result=false;

        return result; //ritorno risultato
    }

    /**
     * Metodo che verifica se la data è corretta
     * @return TRUE se la data è corretta, false se
     * non lo è
     */
    public boolean isDataCorretta(){
        int giorniMese; //dichiarazione variabile

        /* controllo quale numero massimo di giorni ha il mese inserito */
        switch(this.mese)
        {
            case 1,3,5,7,8,10,12:
                giorniMese=31;
                break;
            case 4,6,9,11:
                giorniMese=30;
                break;
            case 2:
                if(anno==0 || !this.isBisestile())
                    giorniMese=28;
                else
                    giorniMese=29;
                break;
            default:
                giorniMese=-1;
        }

        /* controllo di possibili
        * messaggi di errore */
        if(this.giorno<=0 || this.giorno>giorniMese)
            erroriDataOra(2);
        if(this.mese<=0 || this.mese>12)
            erroriDataOra(1);

        ClrScr(); //pulizia schermo

        /* controllo che la data sia corretta,
        * in modo da restituire i vari valori */
        if(this.giorno<=giorniMese && giorniMese>0 && this.mese>0 && this.mese<13)
            return true;
        else
            return false;
    }

    /**
     * Metodo che serve a visualizzare la correttezza dell'ora
     * confrontando minuti e secondi e restituendo
     * possibili messaggi di errore
     * @return TRUE se l'ora è corretta, FALSE se non lo è
     */
    public boolean isOraCorretta(){
        /* visualizzo i possibili messaggi di errore */
        if(this.ora<0 || this.ora>24)
            erroriDataOra(3);
        if(this.min<0 || this.min>59)
            erroriDataOra(4);
        ClrScr();

        /* controllo l'ora e restituisco un valore booleano */
        if(this.ora>=0 && this.ora<=24 && this.min>=0 && this.min<60)
            return true;
        else
            return false;
    }

    /**
     * Metodo che consente di visualizzare in output
     * data e ora
     * @return formato stringa di data e ora
     */
    public String visualizza(){
        /* dichiarazione variabili */
        String giorno;
        String mese;
        String ora;
        String min;

        /* anzitutto eseguo dei controlli
        * su giorno, mese, data e ora, in modo tale da inserire uno 0 all'inizio
        * quando questi sono minori di 10 */
        if(this.giorno<10)
            giorno="0"+Integer.toString(this.giorno);
        else
            giorno=Integer.toString(this.giorno);
        if(this.mese<10)
            mese="0"+Integer.toString(this.mese);
        else
            mese=Integer.toString(this.mese);
        if(this.ora<10)
            ora="0"+Integer.toString(this.ora);
        else
            ora=Integer.toString(this.ora);
        if(this.min<10)
            min="0"+Integer.toString(this.min);
        else
            min=Integer.toString(this.min);

        /* una volta effettuati i controlli,
        * ritorno il formato stringa di ciò che
        * verrà visualizzato a schermo */
        return String.format("%d/%s/%s - %s:%s", this.anno, mese, giorno, ora, min);
    }

    /**
     * Metodo che consente di comparare due oggetti
     * di tipo dataOra tra di loro
     * @param dataOra2 - data da confrontare
     * @return 0 - le date inserite sono uguali
     *          >0 - la prima data è maggiore
     *          <0  - la prima data è minore
     */
    public int compareTo(dataOra dataOra2) {
        // Confronta gli anni
        if (this.anno != dataOra2.anno) {
            return Integer.compare(this.anno, dataOra2.anno);
        }
        // Confronta i mesi
        if (this.mese != dataOra2.mese) {
            return Integer.compare(this.mese, dataOra2.mese);
        }
        // Confronta i giorni
        if (this.giorno != dataOra2.giorno) {
            return Integer.compare(this.giorno, dataOra2.giorno);
        }
        // Confronta le ore
        if (this.ora != dataOra2.ora) {
            return Integer.compare(this.ora, dataOra2.ora);
        }
        // Confronta i minuti
        return Integer.compare(this.min, dataOra2.min);
    }
    /**
     * Metodo che stampa i output tutti i possibili
     * errori su data e ora, in base al numero inserito
     * @param num - indica quale messaggio di errore occorre visualizzare
     */
    private static void erroriDataOra(int num){
        /* differenzio i vari output
        con lo switch case */
        switch (num){
            case 1:
                System.out.println("Mese inserito non valido");
                break;
            case 2:
                System.out.println("Giorno inserito non valido");
                break;
            case 3:
                System.out.println("Ora inserita non valida");
                break;
            case 4:
                System.out.println("Minuto inserito non valido");
                break;
            default:
                System.out.println();
        }
        Wait(3); //attesa
    }

    /**
     * Metodo per trasformare una data e un'ora in un
     * JSON Object
     * @return nuovo oggetto JSON
     */
    public JSONObject toJSON(){
        JSONObject object = new JSONObject(); //creazione nuovo oggetto

        /* inserimento attributi */
        object.put("anno", this.anno);
        object.put("mese", this.mese);
        object.put("giorno", this.giorno);
        object.put("ora", this.ora);
        object.put("min", this.min);

        return object; //ritorno nuovo oggetto creato
    }

    /**
     * Metodo per convertire un JSONObject
     * in un oggetto per indicare data e ora
     * @param object - JSONObject da convertire
     * @return oggetto dataOra
     */
    public static dataOra parseJSON(JSONObject object){
        /* recupero informazioni */
        int anno = object.getInt("anno");
        int mese = object.getInt("mese");
        int giorno = object.getInt("giorno");
        int ora = object.getInt("ora");
        int min = object.getInt("min");

        return new dataOra(anno, mese, giorno, ora, min); //ritorno il nuovo oggettp creatp
    }
}
