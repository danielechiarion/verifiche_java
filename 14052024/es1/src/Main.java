//Author: Daniele Chiarion
//Date: 20-05-2024

/* scrivi un programma che simuli il comportamento di una rubrica telefonica,
* con la possibilità di inserire contatti nascosti e visualizzarne
* i relativi registri chiamate */

import gestioneRubrica.*;
import utility.dataOra;
import static utility.tools.*;
import static utility.jsonFIle.*;

import java.util.Scanner;
public class Main {
    /* variabili e vettori per
     * menu della tipologia del telefono */
    public static String[] tipologia = {"MODALITA' TELEFONO","abitazione", "cellulare", "aziendale"};
    public static void main(String[] args) {
        /* definizione delle varie opzioni del programma */
        String[] opzioni = {"LA TUA RUBRICA",
        "Inserisci un contatto",
        "Elimina un contatto",
        "Ricerca un contatto",
        "Ordina rubrica",
        "Effettua una chiamata",
        "Visualizza contatti",
        "Visualizza registro chiamate",
        "Chiudi rubrica"};

        /* definizione dei vari file necessari
        * al funzionamento del programma */
        String[] filePath = {"data/rubrica.json",
                            "data/registroNormale.csv",
                            "data/registroNascosto.csv"};

        /* creazione scanner */
        Scanner scanner = new Scanner(System.in);
        /* dichiarazione variabili
        * e altri oggetti */
        int scelta;
        Rubrica rubrica;
        String[] datiBase = new String[3];
        int[] posizione;

        /* provo a leggere il contenuto dal file */
        try{
            rubrica = Rubrica.parseJSON(readDataJSON(filePath[0]));
        }catch(Exception e){
            rubrica = initialBoot(scanner); //altrimenti creo da 0 la rubrica
            createNewFile(filePath[0]); //e creo il nuovo file JSON
        }

        do {
            scelta = menu(opzioni, scanner); //faccio scegliere l'opzione desiderata
            ClrScr();
            /* divido le azioni in vari switch */
            switch(scelta){
                /* inserimento di un contatto
                * all'interno della rubrica */
                case 1:
                    aggiungiContatto(rubrica, scanner);
                    break;
                /* eliminazione di un contatto
                * all'interno della rubrica */
                case 2:
                    rimuoviContatto(rubrica, scanner);
                    break;
                case 3:
                    inputDatiBase(datiBase, true, scanner); //richiedo i dati da inserire

                    break;
            }
        }while(scelta!= opzioni.length-1); //vado avanti fin quando non viene scelto di concludere il programma
    }

    /**
     * Metodo di boot iniziale che entra in funzione al primo
     * avvio del software su una macchina.
     *
     * Il programma restituisce la nuova rubrica creata
     * @param scanner - per leggere i dati
     * @return nuova rubrica
     */
    private static Rubrica initialBoot(Scanner scanner){
        /* dichiarazione variabili */
        String password, password2;
        int nMax;

        /* chiedo l'inserimento dei dati */
        System.out.println("Crea la tua rubrica");
        do {
            System.out.println("Inserisci la password: ");
            password = scanner.next();
            System.out.println("Inserisci nuovamente la tua password per confermare");
            password2=scanner.next();
            if(!password.equals(password2))
                messaggioErrore(5);
        }while(!password.equals(password2)); //richiedo la password due volte per motivi di sicurezza
        nMax = safeIntInput("Inserisci ora il numero di ultime chiamate che vuoi visualizzare nella tua cronologia: ", scanner);

        return new Rubrica(password, nMax); //ritorno la nuova rubrica
    }

    /**
     * Metodo che controlla la password e restituisce
     * un valore booleano in base a quanto inserito dall'utente
     * @param password - stabilita a priori dall'utente
     * @param scanner per eseguire l'input
     * @return TRUE se la password è corretta
     *          FALSE se la password è sbagliata
     */
    private static boolean checkPassword(String password, Scanner scanner){
        String input; //dichiarazione variabile

        /* input dati */
        System.out.println("Inserisci la password per accedere ai dati nascosti:");
        input = scanner.nextLine();

        /* restituisce TRUE se la password è corretta */
        if(password.equals(input))
            return true;
        else{
            messaggioErrore(5); //ritorna errore
            return false; //restituisce false
        }
    }

    /**
     * Metodo per l'aggiunta di un contatto alla rubrica
     * @param rubrica - oggetto considerato
     * @param scanner - per l'input dei dati
     */
    private static void aggiungiContatto(Rubrica rubrica, Scanner scanner){
        /* dichiarazione variabili e vettori */
        String[] datiBase = new String[3];
        String stringaBase, stringaExtra="";
        tipoContratto tipo;
        char input;
        boolean siNascosto;

        do {
            inputDatiBase(datiBase, false, scanner); //input dei dati base
            if(rubrica.ricercainElencoContatti(datiBase[0], datiBase[1], datiBase[2], true)[0]>=0)
                messaggioErrore(4);
        }while(rubrica.ricercainElencoContatti(datiBase[0], datiBase[1], datiBase[2], true)[0]>=0); //controllo se il contatto esiste già

        /* richiedo in inserimento il tipo di contratto */
        tipo=tipoContratto.valueOf(tipologia[sceltaTipologia(scanner)]);

        /* richiesta inserimento dati optional */
        System.out.println("Vuoi inserire altre informazioni? Digita S per confermare");
        input=scanner.next().charAt(0);

        /* se la risposta è affermativa,
        vengono inseriti i dati aggiuntivi */
        if(Character.toUpperCase(input)=='S'){
            System.out.println("Inserisci nickname");
            stringaExtra=scanner.next()+",";
            System.out.println("Inserisci secondo numero");
            stringaExtra+=scanner.next()+",";
            System.out.println("Inserisci email");
            stringaExtra+=scanner.next();
        }

        /* chiedo se devo inserire i dati nel registro dei contatti
        * normali o dei contatti nascosti */
        System.out.println("Per registrare nei contatti normali, premi INVIO.\n" +
                "Per registrare nei contatti nascosti, inserisci la password corretta");
        siNascosto=checkPassword(rubrica.getPassword(), scanner);

        /* creazione stringa con informazioni base
         * e creazione oggetto contatto */
        Contatto contatto = new Contatto(datiBase[0], datiBase[1], datiBase[2], tipo, stringaExtra); //creo il contatto
        rubrica.inserimento(contatto, siNascosto); //inserisco il nuovo contatto nella rubrica
    }

    private static void rimuoviContatto(Rubrica rubrica, Scanner scanner){
        /* dichiarazione variabili e vettori */
        boolean siNascosto;
        int[] posizione;
        String[] datiBase = new String[3];

        siNascosto=checkPassword(rubrica.getPassword(), scanner); //richiedo inserimento password per accedere ai dati nascosti
        inputDatiBase(datiBase, false, scanner); //richiedo i dati in input
        posizione = rubrica.ricercainElencoContatti(datiBase[0], datiBase[1], datiBase[2], siNascosto); //ricerco la posizione

        /* se il non viene trovata la persona, viene
        * restituito un messaggio di errore e
        * termina l'esecuzione del metodo */
        if(posizione[0]<0){
            messaggioErrore(6);
            return;
        }

        rubrica.eliminazione(posizione[0]); //altrimenti elimino il valore
    }

    /**
     * Metodo per l'inserimento dei dati base di un contatto
     * (nome, cognome, numero di telefono)
     * @param vetInput - vettore in cui inserire i valori
     * @param ricerca - se è TRUE indica che non è necessario avere tutti i dati
     * @param scanner - per prendere in input i dati
     */
    private static void inputDatiBase(String[] vetInput, boolean ricerca, Scanner scanner){
        /* input dati */
        do {
            do {
                System.out.println("Inserisci il nome");
                vetInput[0]=scanner.nextLine();
            }while(!ricerca && vetInput[0].isBlank()); //vengono applicati controlli mirati per verificare se l'inserimento è necessario
            do {
                System.out.println("Inserisci il cognome");
                vetInput[1]=scanner.nextLine();
            }while(!ricerca && vetInput[1].isBlank());
            do {
                System.out.println("Inserisci il numero di telefono");
                vetInput[2]=scanner.nextLine();
            }while(!ricerca && vetInput[2].isBlank());

            /* possibile errore in caso di mancato compilamento
             * di tutti i dati */
            if(vetInput[0].isBlank() && vetInput[1].isBlank() && vetInput[2].isBlank())
                messaggioErrore(3);
        }while(vetInput[0].isBlank() && vetInput[1].isBlank() && vetInput[2].isBlank()); //controllo finale per verificare se almeno un campo è stato compilato
    }

    /* metodo che sceglie la tipologia
     * di telefono da inserire */
    private static int sceltaTipologia(Scanner keyboard){
        int scelta;

        /* input tipologia */
        scelta=menu(tipologia, keyboard);

        return scelta;
    }
}
