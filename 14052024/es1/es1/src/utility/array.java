package utility;

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
    private static Object aggiungiPosArray(Object[] array){
        /* creo un nuovo array con la dimensione
        * pari all'array incrementata di 1 */
        Object[] copia = new Object[array.length+1];

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
    private static Object[] rimuoviPosArray(Object[] array, int posizione){
        /* creo un nuovo array
        * con una cella in meno
        * e dichiarazione del relativo indice */
        Object[] arrayRidotto = new Object[array.length-1];
        int indexArrayRidotto=0;

        /* copio tutti i valori tranne quello
        * nella posizione indicata */
        for(int i=0;i<array.length;i++)
            if(i!=posizione)
                arrayRidotto[indexArrayRidotto++]=array[i];

        return arrayRidotto; //ritorno l'array scalato di una posizione
    }
}
