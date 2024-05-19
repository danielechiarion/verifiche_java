package utility;

import gestioneRubrica.Contatto;

/**
 * Classe che si occupa di
 * alcuni metodi utili alla gestione di un array
 */
public class array {
    /**
     * Metodo che aggiunge la posizione ad un array in coda,
     * copiandone il contenuto già esistente
     *
     * @param array - array da aumentare
     * @return array con una posizione in più in coda
     */
    public static Contatto[] aggiungiPosArray(Contatto[] array){
        /* creo un nuovo array con la dimensione
        * pari all'array incrementata di 1 */
        Contatto[] copia = new Contatto[array.length+1];

        /* copia dei valori dell'array originario */
        for(int i=0;i<array.length;i++)
            copia[i]=array[i];

        return copia; //ritorna l'array incrementato di una posizione
    }

    /**
     * Metodo che rimuove all'array una posizione
     * @param array - da modificare
     * @param posizione - da rimuovere
     * @return - array con tutti gli elementi tranne quello da cancellare
     */
    public static Contatto[] rimuoviPosArray(Contatto[] array, int posizione){
        /* creo un nuovo array
        * con una cella in meno
        * e dichiarazione del relativo indice */
        Contatto[] arrayRidotto = new Contatto[array.length-1];
        int indexArrayRidotto=0;

        /* copio tutti i valori tranne quello
        * nella posizione indicata */
        for(int i=0;i<array.length;i++)
            if(i!=posizione)
                arrayRidotto[indexArrayRidotto++]=array[i];

        return arrayRidotto; //ritorno l'array scalato di una posizione
    }

    /**
     * Metodo che permette di concatenare due array di interi
     * secondo l'ordine con cui vengono inseriti i parametri
     * @param array1 - primo array inserito
     * @param array2 - secondo array inserito
     * @return array in cui i due array vengono concatenati
     */
    public static int[] concatenaArray(int[] array1, int[] array2){
        int[] nuovoArray = new int[array1.length+array2.length]; //dichiaro un array con la somma delle dimensioni degli array

        /* copio i valori degli array sul nuovo array,
        * seguendo l'ordine di inserimento dei parametri
        * (prima array1, poi array2) */
        for(int i=0;i<nuovoArray.length;i++){
            /* distinguo i due casi dove si
            * prende dal primo e dove si prende
            * dal secondo array per popolare quello nuovo */
            if(i<array1.length)
                nuovoArray[i]=array1[i];
            else
                nuovoArray[i]=array2[i-array1.length];
        }

        return nuovoArray; //ritorno il nuovo array
    }

    /**
     * Metodo che conta quante posizioni sono state occupate
     * in un array
     * @param array da controllare
     * @return posizioni occupate
     */
    public static int posOccupateArray(Object[] array){
        int cont=0; //dichiaro contatore

        /* conto quante posizioni sono occupate */
        for(int i=0;array[i]!=null;i++)
            cont++;

        return cont; //ritorno il contatore
    }
}
