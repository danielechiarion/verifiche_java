//Author: Daniele Chiarion
//Date: 14-05-2024

/* Dato un insieme disordinato (della dimensione minima 10 e massima 11 elementi) di
numeri interi positivi non ripetuti (valore minimo 1, valore massimo 30), occorre poter ordinare,
in ordine crescente, i soli numeri PARI, lasciando gli eventuali dispari nella loro posizione originaria,
lasciando anche l'ultimo valore (pari o dispari non importa) nella propria posizione originaria. */

import java.util.Scanner;

import static tools.utilty.*;

public class Chiarion_3E_es2A_ordinamento {
    public static void main(String[] args) {
        /* creazione scanner */
        Scanner keyboard = new Scanner(System.in);
        /* dichiarazione variabili */
        int dimensione;

        /* richiesta inserimento numeri */
        do {
            dimensione = safeIntInput("Vuoi inserire 10 o 11 numeri? ", keyboard);
            /* possibile messaggio in caso di
             * errore */
            if (dimensione != 10 && dimensione != 11){
                messaggioErrore(2);
                ClrScr();
            }
        } while (dimensione != 10 && dimensione != 11);

        int[] array = new int[dimensione]; //creo l'array con la giusta dimensione

        /* inserimento numeri */
        boolean repeat; //variabile che controlla se ci sono degli errori

        System.out.println("Inserisci numeri positivi non ripetuti (da 1 a 30): ");
        /* ripetizione dell'input */
        for (int i = 0; i < array.length; i++) {
            do {
                repeat = false; //reinizializzo ogni volta la variabile
                array[i] = keyboard.nextInt();

                /* messaggio di errore se il numero è negativo */
                if (array[i] < 1 || array[i]>30){
                    messaggioErrore(2);
                    repeat=true; //cambio anche il valore della variabile booleana
                }
                /* messaggio di errore se il numero si ripete */
                if (checkRipetizioni(array, i)){
                    messaggioErrore(4);
                    repeat=true;
                }
            } while (repeat);
        }

        ordinamento(array); //ordino vettore
        stampaNumeri(array); //output risultati
    }

    private static void ordinamento(int[] array){
        int contaPari=0; //dichiarazione contatore

        /* scorro l'array per contare quanti
        * numeri pari sono presenti
        * lasciando l'ultimo numero presente*/
        for(int i=0;i<array.length-1;i++)
            if(array[i]%2==0)
                contaPari++;

        /* se il contatore è pari a 0,
        * non ha senso procedere per come
        * è stato strutturato l'ordinamento */
        if(contaPari==0)
            return;

        /* dichiarazione del vettore e del suo
        * indice */
        int[] arrayPari = new int[contaPari];
        int indexPari=0;
        /* popolo il vettore con i numeri pari */
        for(int i=0;i<array.length-1;i++)
            if(array[i]%2==0)
                arrayPari[indexPari++]=array[i];

        mergeSort(arrayPari); //ordinamento array pari
        indexPari=0; //riazzeramento indice

        /* ripopolo l'array originale cambiando
        * solo i numeri pari */
        for(int i=0;i<array.length-1;i++)
            if(array[i]%2==0)
                array[i]=arrayPari[indexPari++];
    }

    /**
     * Metodo che controlla se sono presenti ripetizioni
     * di numeri in un vettore
     * @param array - array da esaminare
     * @param posValue - posizione dell'ultimo valore inserito
     * @return TRUE se il valore è già presente
     *          FALSE se non lo è
     */
    private static boolean checkRipetizioni(int[] array, int posValue) {
        /* scorro tutti i valori del vettore
         * fino alla posizione dell'ultimo valore*/
        for (int i = 0; i < posValue; i++)
            if (array[i] == array[posValue])
                return true; //se ho almeno un valore uguale, ho una ripetizione

        return false; //se arrivo alla fine vuol dire che non ho trovato ripetizioni
    }

    /**
     * Algoritmo di ordinamento che impiega il Merge Sort
     * @param array - vettore con valori da riordinare
     */
    public static void mergeSort(int[] array){
        /* controllo se il vettore ha
         * più di un valore al suo interno,
         * altrimenti termino l'esecuzione */
        if(array.length<=1)
            return;

        int middle = array.length/2; //imposto la separazione del vettore a metà

        /* dichiaro quindi due nuovi array */
        int[] left = new int[middle];
        int[] right = new int[array.length-middle];

        /* copio i valori */
        for(int i=0;i<left.length;i++)
            left[i]=array[i];
        for(int i=middle;i<array.length;i++)
            right[i-middle]=array[i];

        /* dimezzo ricorsivamente gli array,
        fin quando non avrò due elementi per ognuno */
        mergeSort(left);
        mergeSort(right);

        mergeofMerge(left, right, array); //unisco i valori ottenuti riordinandoli
    }

    /**
     * Metodo che unisce i valori di due vettori riordinandoli.
     * Metodo associato all'algoritmo di merge sort
     * @param left - primo vettore
     * @param right - secondo vettore
     * @param array - array dove unire i due vettori precedenti
     */
    public static void mergeofMerge(int[] left, int[] right, int[] array){
        int indexLeft=0, indexRight=0, indexArray=0; //dichiarazione degli indici del vettore

        /* finchè tutti e due gli indici rientrano nella
         * lunghezza dei loro array */
        while(indexLeft<left.length && indexRight<right.length){
            if(left[indexLeft]<=right[indexRight]) //se il valore a del primo vettore è minore del secondo
                array[indexArray++]=left[indexLeft++]; //viene inserito il valore del primo vettore incrementando gli indici
            else
                array[indexArray++]=right[indexRight++]; //viene inserito il valore del secondo vettore incrementando gli indici
        }

        /* vengono copiati i valori rimanenti del primo array,
         * qualora ve ne fossero */
        while(indexLeft<left.length)
            array[indexArray++]=left[indexLeft++];
        /* vengono copiati i valori rimanenti del secondo array,
         * qualora ve ne fossero */
        while(indexRight<right.length)
            array[indexArray++]=right[indexRight++];
    }

    /**
     * Metodo che stampa i valori di un vettore
     * in formato tabellare
     * @param vet
     */
    private static void stampaNumeri(int[] vet)
    {
        System.out.println("ARRAY: ");
        for(int i=0;i<vet.length;i++)
        {
            System.out.print(vet[i]+"\t");
        }
        System.out.println(); //metto un a capo
    }
}
