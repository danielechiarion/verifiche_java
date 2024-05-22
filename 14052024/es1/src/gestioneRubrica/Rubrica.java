package gestioneRubrica;

import org.json.JSONArray;
import org.json.JSONObject;

import static utility.array.*;

/**
 * Classe che consente la gestione
 * di una rubrica e la possibilità di avere
 * account riservati
 */
public class Rubrica {
    /* definizione attributi */
    protected String password;
    protected Contatto[] contatti;
    protected int maxLista;
    protected Chiamata[] registroNormale;
    protected Chiamata[] registroNascosto;
    protected int contattiNormali;
    protected int contattiNascosti;

    /* definizione dei metodi get/set */

    /* per tutti gli attributi definiti come "nascosti"
    * è possibile accedere ai dati solo se un valore booleano,
    * dato dal controllo della password, è impostato a TRUE */
    public String getPassword(){ return this.password; }
    public int getMaxLista(){ return this.maxLista; }
    public Contatto[] getContatti(){ return this.contatti; }
    /* metodo per restituire un contatto
    all'interno di un array */
    public Contatto getContatto(int pos){
        return this.contatti[pos];
    }
    public Chiamata[] getRegistroNormale(){
        return this.registroNormale;
    }
    public Chiamata[] getRegistroNascosto(boolean value){
        if(value)
            return this.registroNascosto;
        return null;
    }
    public int getContattiNormali(){ return this.contattiNormali; }
    public int getContattiNascosti(){ return this.contattiNascosti; }

    public void setPassword(String password){ this.password=password; }
    public void setMaxLista(int maxLista){ this.maxLista=maxLista; }

    /**
     * Metodo costruttore della classe
     * rubrica
     * @param password - password per accedere ai dati nascosti
     * @param nMaxLista - numero massimo di chiamate da visualizzare nei registri di chiamate
     */
    public Rubrica(String password, int nMaxLista){
        this.password=password; //imposto la password
        this.maxLista=nMaxLista; //imposto gli attributi
        this.registroNascosto=new Chiamata[nMaxLista]; //imposto la lunghezza del registro normale
        this.registroNormale=new Chiamata[nMaxLista]; //imposto la lunghezza del registro nascosto
        /* setto a zero contatti normali e nascosti */
        this.contattiNormali=0;
        this.contattiNascosti=0;
    }

    /**
     * Metodo per l'inserimento di un contatto
     * all'interno di uno dei due vettori dedicati
     * @param contatto - elemento da aggiungere in coda
     */
    public void inserimento(Contatto contatto){
        this.contatti=aggiungiPosArray(this.contatti); //aggiungo una posizione all'array dei contatti
        this.contatti[this.contatti.length-1]=contatto; //salvo l'array nella posizione

        /* decido quale numero incrementare,
        * se quello dei contatti normali o quello dei contatti nascosti */
        if(contatto.getStato())
            this.contattiNascosti++;
        else
            this.contattiNormali++;
    }

    /**
     * Metodo che rimuove un contatto in uno dei due vettori,
     * data la posizione
     * @param pos - posizione del contatto da eliminare
     */
    public void eliminazione(int pos){
        this.contatti = rimuoviPosArray(this.contatti, pos); //invoco il metodo per la rimozione della posizione nell'array
    }

    /**
     * Metodo che ordina tutti i contatti normali
     * e quelli nascosti utilizzando il bubble sort
     */
    public void ordinaContatti(){
        /* prima di tutto controllo se l'array ha una dimensione
        * uguale a 1 o se è vuoto, in modo tale da escludere questo
        * caso dall'ordinamento */
        if(this.contatti==null || this.contatti.length<2)
            return;

        /* dichiarazione variabili
         * utili all'ordinamento */
        boolean scambio; //indica se è avvenuto almeno uno scambio all'interno di un ciclo
        int passaggi=0; //indica quanti passaggi ha compiuto il bubble sort
        Contatto temp; //variabile temporanea per lo switch

        do {
            scambio=false; //reinizializzo ogni volta la variabile
            for(int i=0;i<this.contatti.length-passaggi-1;i++){
                /* se il contatto ha un cognome che viene dopo nell'ordine alfabetico,
                 * (o il nome, in caso dello stesso cognome) ... */
                if(this.contatti[i].getCognome().compareToIgnoreCase(this.contatti[i+1].getCognome())>0 ||
                        this.contatti[i].getCognome().equalsIgnoreCase(this.contatti[i+1].getCognome()) &&
                                this.contatti[i].getNome().compareToIgnoreCase(this.contatti[i+1].getNome())>0){
                    /* ... si effettua lo scambio tra i contatti */
                    temp=this.contatti[i];
                    this.contatti[i]=this.contatti[i+1];
                    this.contatti[i+1]=temp;

                    scambio=true; //si aggiorna la variabile
                }
            }
            passaggi++; //si incrementa il numero di passaggi fatti
        }while(scambio); //continua se è stata fatta almeno un'operazione, altrimenti vuol dire che è già tutto ordinato
    }

    /**
     * Metodo che permette la ricerca di un
     * contatto all'interno anche di tutte e due gli array,
     * in base ai valori forniti da parametri.
     * Questo metodo, a differenza degli altri metodi di ricerca, può ritornare anche più posizioni,
     * in modo da avere risultati simili.
     * @param nome
     * @param cognome
     * @param telefono
     * @param siNascosti è TRUE se bisogna cercare anche nei contatti nascosti
     *                     FALSE se non e possibile accedere a tali contatti
     * @return array con le posizioni trovate
     */
    public int[] ricercainElencoContatti(String nome, String cognome, String telefono, boolean siNascosti){
        /* dichiarazione variabili */
        int uguali=0;
        String param1="", param2; //stringhe per il confronto tra due parametri

        if(!nome.isBlank())
            param1+=nome;
        if(!cognome.isBlank())
            param1+=cognome;
        if(!telefono.isBlank())
            param1+=telefono;

        if(this.contatti==null)
            return new int[]{-1};

        /* scorro tutto l'array in cerca di corrispondenze */
        for(int i=0;i<this.contatti.length;i++){
            param2="";//reinizializzo ogni volta la stringa
            /* pongo delle condizioni per comporre la seconda stringa, basandomi su
             * quali campi sono rimasti vuoti */
            if(!nome.isBlank())
                param2+=this.contatti[i].getNome();
            if(!cognome.isBlank())
                param2+=this.contatti[i].getCognome();
            if(!telefono.isBlank())
                param2+=this.contatti[i].getTelefono();

            /* confronto le due stringhe e vedo se ci sono uguaglianze,
            * controllando se posso accedere ad eventuali dati nascosti*/
            if(param1.equalsIgnoreCase(param2) && !this.contatti[i].getStato() || this.contatti[i].getStato() && siNascosti)
                uguali++;
        }

        /* controllo se ho trovato dei risultati
         * e, in caso affermativo, dichiaro un vettore */
        if(uguali==0)
            return new int[]{-1};

        int[] vetPos = new int[uguali];
        int indexVetPos=0;

        /* scorro nuovamente il valore per cercare
        le posizioni degli oggetti uguali */
        for(int i=0;i<this.contatti.length;i++){
            param2="";//reinizializzo ogni volta la stringa
            /* pongo delle condizioni per comporre la seconda stringa, basandomi su
             * quali campi sono rimasti vuoti */
            if(!nome.isBlank())
                param2+=this.contatti[i].getNome();
            if(!cognome.isBlank())
                param2+=this.contatti[i].getCognome();
            if(!telefono.isBlank())
                param2+=this.contatti[i].getTelefono();

            /* confronto le due stringhe e vedo se ci sono uguaglianze */
            if(param1.equalsIgnoreCase(param2) && !this.contatti[i].getStato() || this.contatti[i].getStato() && siNascosti)
                vetPos[indexVetPos++]=i;
        }

        return vetPos; //ritorno l'array di posizioni
    }

    /**
     * Metodo che consente la visualizzazione in output dei
     * contatti all'interno di una rubrica
     * @param siNascosti - indica se è possibile o meno stampare i contatti nascosti
     */
    public void visualizzaContatti(boolean siNascosti){
        /* creo un array per ogni tipo di contatti (normali e nascosti),
        * dichiarando anche i relativi indici */
        Contatto[] elencoNormale = new Contatto[this.contattiNormali];
        Contatto[] elencoNascosti=new Contatto[this.contattiNascosti];
        int indexNormali=0, indexNascosti=0;

        if(this.contatti!=null){
            /* ripercorro tutto il primo vettore e trasferisco tutto
             * in contatti normali e nascosti */
            for(Contatto contatto : this.contatti){
                if(contatto.getStato())
                    elencoNascosti[indexNascosti++]=contatto;
                else
                    elencoNormale[indexNormali++]=contatto;
            }
        }

        /* infine visualizzo separatamente
        contatti normali e nascosti */
        System.out.println("*** CONTATTI STANDARD ***");
        if(this.contattiNormali==0) //prima controllo se ci sono almeno dei contatti di questa categoria
            System.out.println("Nessun contatto standard inserito");
        else{
            for(Contatto contatto : elencoNormale)
                System.out.println(contatto.visualizza(false)+"\n");
        }

        /* controllo anche se l'utente ha avuto accesso
        * a dei contatti nascosti */
        if(!siNascosti)
            return;

        System.out.println("*** CONTATTI NASCOSTI ***");
        if(this.contattiNascosti==0)
            System.out.println("Nessun contatto nascosto inserito");
        else{
            for(Contatto contatto : elencoNascosti)
                System.out.println(contatto.visualizza(false)+"\n");
        }
    }

    /**
     * Metodo che visualizza il registro delle ultime chiamate,
     * considerando anche quelle nascoste,
     * in base al valore inserito nel parametro
     * @param siNascosti TRUE se si può accedere al registro nascsto
     *                   FALSE se si può accedere solo al registro normale
     */
    public void visualizzaRegistro(boolean siNascosti){
        /* visualizzo prima tutto l'elenco
        del registro normale */
        System.out.println("REGISTRO NORMALE");
        if(this.registroNascosto==null)
            System.out.println("Nessuna chiamata effettuata");
        else{
            System.out.println("Ultime "+this.maxLista+" chiamate");
            for (int i=0;i<this.registroNormale.length && this.registroNormale[i]!=null;i++)
                System.out.println(this.registroNormale[i].visualizza());
        }

        if(!siNascosti)
            return;

        /* se ho l'accesso al registro nascosto,
        * visualizzo anche le ultime chiamate */
        System.out.println("\nREGISTRO NASCOSTO");
        if(this.registroNascosto==null)
            System.out.println("Nessuna chiamata effettuata");
        else{
            System.out.println("Ultime "+this.maxLista+" chiamate");
            for (int i=0;i<this.registroNascosto.length && this.registroNascosto[i]!=null;i++)
                System.out.println(this.registroNascosto[i].visualizza());
        }
    }

    /**
     * Metodo che converte i dati contenuti in una rubrica in
     * un oggetto JSON
     * @return oggetto JSON rappresentante la rubrica
     */
    public JSONObject toJSON(){
        JSONObject rubrica = new JSONObject(); //creo il nuovo oggetto

        /* assegno subito gli attributi costituiti
        * da variabili primitive */
        rubrica.put("password", this.password);
        rubrica.put("maxLista", this.maxLista);
        rubrica.put("contattiNormali", this.contattiNormali);
        rubrica.put("contattiNascosti", this.contattiNascosti);

        /* recupero tutta la lista di contatti
        * utilizzando un JSON Array */
        JSONArray contatti = new JSONArray();
        if(this.contatti!=null){//prima controllo se i contatti hanno degli elementi
            for(Contatto contatto : this.contatti)
                contatti.put(contatto.toJSON()); //per ogni contatto ottengo il JSONObject e lo inserisco nell'array

            rubrica.put("contatti", contatti); //viene inserita infine l'elenco contatti nell'oggetto rubrica
        }

        /* recupero i dati del registro normale */
        JSONArray registroNormale = new JSONArray();
        if(this.registroNormale!=null && this.registroNormale[0]!=null){
            for(Chiamata chiamata : this.registroNormale)
                registroNormale.put(chiamata.toJSON());
            rubrica.put("registroNormale", registroNormale);
        }

        /* recupero i dati anche del registro nascosto */
        JSONArray registroNascosto = new JSONArray();
        if(this.registroNascosto!=null && this.registroNascosto[0]!=null){
            for(Chiamata chiamata : this.registroNascosto)
                registroNascosto.put(chiamata.toJSON());
            rubrica.put("registroNascosto", registroNascosto);
        }

        return rubrica; //ritorno la nuova rubrica sotto formato JSONObject
    }

    /**
     * Metodo che restituisce la rubrica da quanto
     * letto nel file JSON
     * @param object - JSONObject da convertire
     * @return oggetto rubrica convertito
     */
    public static Rubrica parseJSON(JSONObject object){
        /* recupero dati "primitivi" */
        int maxLista = object.getInt("maxLista");
        String password = object.getString("password");

        Rubrica rubrica = new Rubrica(password, maxLista); //creazione nuovo oggetto rubrica

        /* recupero altri dati primitivi */
        rubrica.contattiNormali=object.getInt("contattiNormali");
        rubrica.contattiNascosti=object.getInt("contattiNascosti");

        /* recupero contatti normali */
        /* creo un nuovo vettore assegnandoli una lunghezza
        * pari ai contatti salvati sul file JSON */
        /* effettuo un try-catch perchè il vettore, se nullo,
        * potrebbe non ancora averlo inserito */
        try{
            rubrica.contatti = new Contatto[object.getJSONArray("contatti").length()]; //prendo un vettore e gli assegno la lunghezza trovata nel file
            for(int i=0;i<rubrica.contatti.length;i++)
                rubrica.contatti[i]=Contatto.parseJSON(object.getJSONArray("contatti").getJSONObject(i)); //per ogni contatto lo riconverto
        }catch(Exception e){
            rubrica.contatti = null; //altrimenti dichiaro la rubrica null
        }

        /* recupero delle chiamate nel
        * registro normale */
        rubrica.registroNormale = new Chiamata[rubrica.maxLista];
        try{
            for(int i=0;i<rubrica.registroNormale.length;i++)
                rubrica.registroNormale[i]=Chiamata.parseJSON(object.getJSONArray("registroNormale").getJSONObject(i));
        }catch(Exception e){
            rubrica.registroNormale=new Chiamata[rubrica.maxLista];
        }

        /* recupero delle chiamate
        * nel registro nascosto */
        rubrica.registroNascosto = new Chiamata[rubrica.maxLista];
        try{
            for(int i=0;i<rubrica.registroNascosto.length;i++)
                rubrica.registroNascosto[i]=Chiamata.parseJSON(object.getJSONArray("registroNascosto").getJSONObject(i));
        }catch(Exception e){
            rubrica.registroNascosto=new Chiamata[rubrica.maxLista];
        }

        return rubrica; //ritorno la rubrica ricreata
    }

    /**
     * Metodo per inserire una chiamata all'interno di uno
     * dei due registri
     * @param chiamata - oggetto da registrare
     * @param siNascosta TRUE se bisogna inserire il valore nel registro nascosto
     *                   FALSE se bisogna inserire il valore nel registro normale
     */
    public void registraChiamata(Chiamata chiamata, boolean siNascosta){
        Chiamata[] registro; //dichiaro array
        /* dichiaro le variabili */
        int index, start, finish;

        /* il vettore punterà al registro normale o nascosto in
        * base al valore del booleano */
        if(!siNascosta){
            if(this.registroNormale==null)
                this.registroNormale=new Chiamata[this.maxLista];
            registro=this.registroNormale;
        }
        else{
            if(this.registroNascosto==null)
                this.registroNascosto=new Chiamata[this.maxLista];
            registro=this.registroNascosto;
        }

        /* se non abbiamo posizioni occupate,
        * la posizione occupata è la prima */
        if(posOccupateArray(registro)==0){
            index=0; //assegno l'indice
            registro[index]=chiamata; //sposto la chiamata
            return; //termino il ciclo
        }

        /* conto quale posizione gli spetta
        * utilizzando la ricerca dicotomica */
        index=registro.length/2;
        start=0;
        finish=posOccupateArray(registro); //dichiaro indici
        /* controllo subito se la chiamata deve essere posizionata
        * in cima o in fondo alla lista */
        if(chiamata.compareTo(registro[0])<0)
            index=0;
        else if(chiamata.compareTo(registro[finish-1])>0)
            index=finish;
        else{
            /* comincio la ricerca, compiendola solo se
             * non ho già trovato la posizione ideale, in cui la posizione sunbito
             * prima è minore e la posizione subito dopo è maggiore */
            while(registro[start].compareTo(registro[index])>0 && registro[finish].compareTo(registro[index])>0
                    || registro[start].compareTo(registro[index])<0 && registro[finish].compareTo(registro[index])<0
                    && start<=finish){
                /* se il numero è minore di
                 * quello richiesto, mi sposto a sinistra,
                 * quindi modifico il limite */
                if((registro[start].compareTo(registro[index])>0 && registro[finish].compareTo(registro[index])>0))
                {
                    finish=index-1;
                    index=(start+finish)/2;
                }
                /* se il numero è maggiore
                 * di quello richiesto, mi sposto a destra,
                 * quindi modifico lo start */
                else if(registro[start].compareTo(registro[index])<0 && registro[finish].compareTo(registro[index])<0)
                {
                    start=index+1;
                    index=(start+finish)/2;
                }
            }

            /* controllo se è stato trovato davvero la posizione,
            * altrimenti verrà messo in fondo */
            if(registro[start].compareTo(registro[index])>0 && registro[finish].compareTo(registro[index])<0);
            else
                index=finish+1;
        }

        /* controllo se la posizione è all'interno del vettore */
        if(index<registro.length-1){
            /* sposto prima tutti i valori */
            for(int i=registro.length-1;i>index;i--)
                registro[i]=registro[i-1];

            registro[index]=chiamata; //posiziono infine la chiamata nella posizione corretta
        }
    }
}
