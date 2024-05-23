package utility;

import java.util.Scanner;

/**
 * Classe che si occupa dei metodi base
 * per il funzionamento del software, quali:
 * <ul>
 *     <li>menu</li>
 *     <li>pulizia schermo</li>
 *     <li>attesa</li>
 *     <li>input sicuro numeri interi</li>
 *     <li>messaggi d'errore</li>
 *     <li>...</li>
 * </ul>
 */
public class tools {
    /**
     * Metodo che pulisce lo schermo nel cmd di Windows
     */
    public static void ClrScr() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che genera un'attesa all'interno del programma
     * @param x - numero di secondi per l'attesa
     */
    public static void Wait(int x) {
        try {
            Thread.sleep(1000 * x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che propone diversi altrenative e
     * permette all'utente di fare una scelta
     *
     * @param opzioni - lista di funzioni possibili
     * @param keyboard - scanner per l'input dei dati
     * @return numero intero che rappresenta la scelta effettuata
     */
    public static int menu(String[] opzioni, Scanner keyboard) {
        int scelta;

        do {
            ClrScr();
            System.out.println("=== " + opzioni[0] + " ===");
            for (int i = 1; i < opzioni.length; i++) {
                System.out.println("[" + i + "]" + " " + opzioni[i]);
            }
            try {
                scelta = safeIntInput("", keyboard);
                keyboard.nextLine();
            }catch(Exception e){
                scelta = Integer.parseInt(keyboard.next());
                keyboard.nextLine();
            }
            if (scelta < 1 || scelta > opzioni.length - 1) {
                System.out.println("Valore errato. Riprova");
                Wait(3);
            }
        } while (scelta < 1 || scelta > opzioni.length - 1);

        return scelta;
    }

    /**
     * Metodo che genera l'output dei messaggi di errore
     * più comuni
     * @param value - numero che indica il codice di errore
     */
    public static void messaggioErrore(int value) {
        /* switch case per i messaggi di errore */
        switch (value) {
            case 1:
                System.out.println("ERROR! Input out of range");
                break;
            case 2:
                System.out.println("ERROR! Wrong domain");
                break;
            case 3:
                System.out.println("ERROR! All empty data!");
                break;
            case 4:
                System.out.println("This data already exists!");
                break;
            case 5:
                System.out.println("Password inserita non valida");
                break;
            case 6:
                System.out.println("ERROR! Data not found");
                break;
            default:
                System.out.println("");
        }
        Wait(3);
        ClrScr();
    }

    /**
     * Metodo che permette di ottenere in input un numero intero
     * evitando errori di dominio
     * @param testoInput - testo con la richiesta da porre all'utente
     * @param scanner - per prendere in input il dato
     * @return input controllato dell'utente
     */
    public static int safeIntInput(String testoInput, Scanner scanner){
        /* dichiarazione variabili */
        int input=0;
        boolean check;

        do {
            check=false; //reinizializzo ogni volta la variabile
            /* input dati */
            if(!testoInput.isBlank())
                System.out.println(testoInput);
            try {
                input = scanner.nextInt();
            }catch (Exception e){
                /* messaggio di errore */
                messaggioErrore(1);
                check=true; //modifico la variabile
                scanner.nextLine();
            }
        }while (check);

        return input; //ritorno valore
    }

    /**
     * Metodo che restituisce una stringa composta da soli numeri
     * @param testoInput - richiesta per l'utente
     * @param scanner - per l'input dati
     * @return
     */
    public static String inputSoloNumeriStringa(String testoInput, Scanner scanner){
        String input; //dichiarazione variabile

        /* input dati */
        do {
            if(!testoInput.isBlank()) //prima di scrivere, controllo che la stringa non sia vuota
                System.out.println(testoInput);
            input=scanner.nextLine();
            /* restituisco un messaggio di errore
            * nel caso in cui la stringa non sia numerica */
            if(!input.isBlank() && !isStringNumeric(input))
                messaggioErrore(2);
        }while(!input.isBlank() && !isStringNumeric(input)); //ripete solo se la stringa non è vuota e se non è numerica

        return input; //alla fine ritorna la stringa
    }

    /**
     * Metodo che serve a controllare se una stringa è interamente numerica.
     * Sono stati inclusi in questa categoria anche gli spazi, in quanto
     * spesso possono essere presenti, anche erroneamente, nei numeri di telefono
     * @param input - stringa da valutare
     * @return TRUE se la stringa è solo numerica
     *          FALSE se contiene al suo interno anche altri caratteri
     */
    public static boolean isStringNumeric(String input){
        /* scorro tutta la stringa e controllo
        * se la stringa ha tutti i caratteri compresi
        * nell'intervallo 0-9 */
        for(int i=0;i<input.length();i++)
            if(input.charAt(i)<'0' || input.charAt(i)>'9' && input.charAt(i)!=' ') //se trova anche un solo carattere non numerico ritorna false
                return false;

        return true; //altrimenti ritorna true, perchè vuol dire che la stringa è interamente numerica.
    }
}
