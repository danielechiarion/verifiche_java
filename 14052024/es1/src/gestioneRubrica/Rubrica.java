package gestioneRubrica;

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
}
