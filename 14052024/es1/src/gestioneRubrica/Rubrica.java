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
    protected Contatto[] contattiNormali;
    protected Contatto[] contattiNascosti;
    protected int maxLista;
    protected Chiamata[] registroNormale;
    protected Chiamata[] registroNascosto;

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
    /* metodo per restituire un contatto
    all'interno di un array */
    public Contatto getContatto(int pos){
        /* se la posizione è minore della lunghezza
        * degli array dei contatti normali, vuol dire che
        * si trova in questo array */
        if(pos<this.contattiNormali.length)
            return this.contattiNormali[pos];
        /* altrimenti vuol dire che si trova nell'array
        * dei contatti nascosti, nella posizione meno la lunghezza
        * dell'array dei contratti normali */
        else
            return this.contattiNascosti[pos-this.contattiNormali.length];
    }

    public Chiamata[] getRegistroNormale(){
        return this.registroNormale;
    }
    public Chiamata[] getRegistroNascosto(boolean value){
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

    /**
     * Metodo costruttore della classe
     * rubrica
     * @param password - password per accedere ai dati nascosti
     * @param nMaxLista - numero massimo di chiamate da visualizzare nei registri di chiamate
     */
    public Rubrica(String password, int nMaxLista){
        this.password=password; //imposto la password
        this.maxLista=nMaxLista; //imposto gli attributi
        this.registroNascosto=new Chiamata[nMaxLista]; //imposto la lunghezza del registro normal
        this.registroNormale=new Chiamata[nMaxLista]; //imposto la lunghezza del registro nascosto
    }

    /**
     * Metodo per l'inserimento di un contatto
     * all'interno di uno dei due vettori dedicati
     * @param contatto - elemento da aggiungere in coda
     * @param siNascosto - assume TRUE se il contatto è nascosto
     *                   FALSE se il contatto è normale
     */
    public void inserimento(Contatto contatto, boolean siNascosto){
        /* differenzio i due casi, in quanto
        * andrò ad agire su due vettori differenti */
        if(siNascosto){
            this.contattiNascosti=aggiungiPosArray(this.contattiNascosti); //aggiungo una posizione
            this.contattiNascosti[this.contattiNascosti.length-1]=contatto; //aggiungo il contatto in coda
        }
        else{
            this.contattiNormali=aggiungiPosArray(this.contattiNormali);
            this.contattiNormali[this.contattiNormali.length-1]=contatto;
        }
    }

    /**
     * Metodo che rimuove un contatto in uno dei due vettori,
     * data la posizione
     * @param pos - posizione del contatto da eliminare
     */
    public void eliminazione(int pos){
        /* se la posizione è minore della lunghezza
        * dell'array dei contatti norrmali,
        * allora procedo all'eliminazione nell'array */
        if(pos<this.contattiNormali.length)
            this.contattiNormali = rimuoviPosArray(this.contattiNormali, pos); //rimuovo la posizione
        /* altrimenti, vuol dire che il contatto
        * appartiene al vettore dei contatti nascosti */
        else{
            pos-=this.contattiNormali.length; //la posizione sarà il valore della posizione meno la lunghezza dell'array
            this.contattiNascosti=rimuoviPosArray(this.contattiNascosti, pos); //rimuove la posizione
        }
    }

    /**
     * Metodo che ordina tutti i contatti normali
     * e quelli nascosti
     */
    public void ordinaContatti(){
        bubbleSort(this.contattiNascosti);
        bubbleSort(this.contattiNormali);
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
        /* creo due array per puntare alle future
        * posizioni date */
        int[] posizione; //array contatti normali
        int[] posizione2={-1}; //array contatti nascosti

        posizione=ricercaContatto(this.contattiNormali, nome, cognome, telefono); //cerco prima tra i contatti normali

        /* se non ho trovato niente e posso anche cercare tra i contatti nascosti */
        if(siNascosti)
            posizione2=ricercaContatto(this.contattiNascosti, nome, cognome, telefono); //ricerco tra i contatti nascosti


        /* prima controllo se il primo non ha posizioni,
        * quindi ritorno la posizione dei contatti nascosti solo se
        * è stato possibile ricercare al suo interno o se è stato
        * trovato qualcosa */
        if(posizione[0]<0 && posizione2[0]>0)
            return posizione2;
        /* se non è stato trovato qualcosa nella primo vettore
        * ma nel secondo sì, ritorno le posizioni del secondo vettore */
        else if(posizione2[0]<0)
            return posizione;
        /* altrimenti, ritorno un array
        * frutto della concatenazione dei due array trovati */
        else
            return concatenaArray(posizione, posizione2);
    }

    /**
     * Metodo che consente la visualizzazione in output dei
     * contatti all'interno di una rubrica
     * @param siNascosti - indica se è possibile o meno stampare i contatti nascosti
     */
    public void visualizzaContatti(boolean siNascosti){
        /* stampo in output tutti i contatti
        * normali trovati */
        System.out.println("*** ELENCO CONTATTI ***");
        for(Contatto x : this.contattiNormali)
            System.out.println(x.visualizza());

        /* stampo tutti i contatti se il valore e TRUE */
        if(siNascosti){
            System.out.println("\n*** CONTATTI NASCOSTI ***");
            for(Contatto x : this.contattiNascosti)
                System.out.println(x.visualizza());
        }
    }

    /**
     * Metodo di ricerca di uno o più contatti
     * in base ai parametri forniti.
     * Viene utilizzato il metodo brute force, perchè si potrebbe
     * ricercare solo per numero di telefono
     *
     * @param nome
     * @param cognome
     * @param telefono
     * @return vettore delle posizioni (o -1 se non ha trovato niente)
     */
    private static int[] ricercaContatto(Contatto[] array, String nome, String cognome, String telefono){
        /* dichiarazione variabili */
        int uguali=0;
        String param1="", param2; //stringhe per il confronto tra due parametri

        if(!nome.isBlank())
            param1+=nome;
        if(!cognome.isBlank())
            param1+=cognome;
        if(!telefono.isBlank())
            param1+=telefono;

        /* scorro tutto l'array in cerca di corrispondenze */
        for(int i=0;i<array.length;i++){
            param2="";//reinizializzo ogni volta la stringa
            /* pongo delle condizioni per comporre la seconda stringa, basandomi su
             * quali campi sono rimasti vuoti */
            if(!nome.isBlank())
                param2+=array[i].getNome();
            if(!cognome.isBlank())
                param2+=array[i].getCognome();
            if(!telefono.isBlank())
                param2+=array[i].getTelefono();

            /* confronto le due stringhe e vedo se ci sono uguaglianze */
            if(param1.equalsIgnoreCase(param2))
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
        for(int i=0;i<array.length;i++){
            param2="";//reinizializzo ogni volta la stringa
            /* pongo delle condizioni per comporre la seconda stringa, basandomi su
             * quali campi sono rimasti vuoti */
            if(!nome.isBlank())
                param2+=array[i].getNome();
            if(!cognome.isBlank())
                param2+=array[i].getCognome();
            if(!telefono.isBlank())
                param2+=array[i].getTelefono();

            /* confronto le due stringhe e vedo se ci sono uguaglianze */
            if(param1.equalsIgnoreCase(param2))
                vetPos[indexVetPos++]=i;
        }

        return vetPos; //ritorno l'array di posizioni
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

        /* recupero tutta la lista di contatti normali,
        * utilizzando un JSON Array */
        JSONArray contattiNormali = new JSONArray();
        for(Contatto contatto : this.contattiNormali)
            contattiNormali.put(contatto.toJSON()); //inserisco ogni volta un valore
        rubrica.put("contattiNormali", contattiNormali);

        /* recupero anche la lista di contatti nascosti */
        JSONArray contattiNascosti = new JSONArray();
        for(Contatto contatto : this.contattiNascosti)
            contattiNascosti.put(contatto.toJSON());
        rubrica.put("contattiNascosti", contattiNascosti); //inserisco un nuovo attributo al JSONObject

        /* recupero i dati del registro normale */
        JSONArray registroNormale = new JSONArray();
        for(Chiamata chiamata : this.registroNormale)
            registroNormale.put(chiamata.toJSON());
        rubrica.put("registroNormale", registroNormale);

        /* recupero i dati anche del registro nascosto */
        JSONArray registroNascosto = new JSONArray();
        for(Chiamata chiamata : this.registroNascosto)
            registroNascosto.put(chiamata.toJSON());
        rubrica.put("registroNascosto", registroNascosto);

        return rubrica; //ritorno la nuova rubrica sotto formato JSONObject
    }

    public static Rubrica parseJSON(JSONObject object){
        /* recupero dati "primitivi" */
        int maxLista = object.getInt("maxLista");
        String password = object.getString("password");

        Rubrica rubrica = new Rubrica(password, maxLista); //creazione nuovo oggetto rubrica

        /* recupero contatti normali */
        /* creo un nuovo vettore assegnandoli una lunghezza
        * pari ai contatti salvati sul file JSON */
        rubrica.contattiNormali = new Contatto[object.getJSONArray("contattiNormali").length()];
        /* per ogni contatto leggo il contenuto
        * e lo trasformo in un oggetto contatto,
        * inserendolo in un array */
        for(int i=0;i<rubrica.contattiNormali.length;i++)
            rubrica.contattiNormali[i]=Contatto.parseJSON(object.getJSONArray("contattiNormali").getJSONObject(i));

        /* recupero i dati anche dei contatti nascosti */
        rubrica.contattiNascosti = new Contatto[object.getJSONArray("contattiNascosti").length()];
        for(int i=0;i<rubrica.contattiNascosti.length;i++)
            rubrica.contattiNascosti[i]=Contatto.parseJSON(object.getJSONArray("contattiNascosti").getJSONObject(i));

        /* recupero delle chiamate nel
        * registro normale */
        rubrica.registroNormale = new Chiamata[object.getJSONArray("registroNormale").length()];
        for(int i=0;i<rubrica.registroNormale.length;i++)
            rubrica.registroNormale[i]=Chiamata.parseJSON(object.getJSONArray("registroNormale").getJSONObject(i));

        /* recupero delle chiamate
        * nel registro nascosto */
        rubrica.registroNascosto = new Chiamata[object.getJSONArray("registroNascosto").length()];
        for(int i=0;i<rubrica.registroNascosto.length;i++)
            rubrica.registroNascosto[i]=Chiamata.parseJSON(object.getJSONArray("registroNascosto").getJSONObject(i));

        return rubrica; //ritorno la rubrica ricreata
    }

    public void registraChiamata(Chiamata chiamata, boolean siNascosta){
        Chiamata[] registro;

        /* il vettore punterà al registro normale o nascosto in
        * base al valore del booleano */
        if(!siNascosta)
            registro=this.registroNormale;
        else
            registro=this.registroNascosto;

        /* conto quale posizione gli spetta
        * utilizzando la ricerca dicotomica */
        int index=registro.length/2, start=0, finish=posOccupateArray(registro); //dichiaro indici
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
            if(registro[start].compareTo(registro[index])<0 && registro[finish].compareTo(registro[index])>0);
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
    /**
     * Metodo che sfrutta l'algoritmo di ordinamento bubble sort.
     * Vengono utilizzati come attributi di riferimento il nome e il cognome
     * del contatto, in modo da ottenere un elenco in ordine alfabetico.
     * Viene inoltre effettuato un controllo se l'array è gia ordinato,
     * in modo da ridurre il numero di azioni da compiere
     *
     * @param vet array di contatti da riordinare
     */
    private static void bubbleSort(Contatto[] vet){
        /* dichiarazione variabili
         * utili all'ordinamento */
        boolean scambio; //indica se è avvenuto almeno uno scambio all'interno di un ciclo
        int passaggi=0; //indica quanti passaggi ha compiuto il bubble sort
        Contatto temp; //variabile temporanea per lo switch

        do {
            scambio=false; //reinizializzo ogni volta la variabile
            for(int i=0;i<vet.length-passaggi-1;i++){
                /* se il contatto ha un cognome che viene dopo nell'ordine alfabetico,
                 * (o il nome, in caso dello stesso cognome) ... */
                if(vet[i].getCognome().compareToIgnoreCase(vet[i+1].getCognome())>0 ||
                        vet[i].getCognome().equalsIgnoreCase(vet[i+1].getCognome()) &&
                                vet[i].getNome().compareToIgnoreCase(vet[i+1].getNome())>0){
                    /* ... si effettua lo scambio tra i contatti */
                    temp=vet[i];
                    vet[i]=vet[i+1];
                    vet[i+1]=temp;

                    scambio=true; //si aggiorna la variabile
                }
            }
            passaggi++; //si incrementa il numero di passaggi fatti
        }while(scambio); //continua se è stata fatta almeno un'operazione, altrimenti vuol dire che è già tutto ordinato
    }
}
