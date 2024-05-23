//Author: Daniele Chiarion
//Date: 20-05-2024

/* scrivi un programma che simuli il comportamento di una rubrica telefonica,
* con la possibilità di inserire contatti nascosti e visualizzarne
* i relativi registri chiamate */

import gestioneRubrica.*;
import utility.dataOra;
import static utility.tools.*;
import static utility.jsonFIle.*;
import static utility.CSVFile.*;

import java.util.Scanner;
import java.io.*;
public class Main {
    /* variabili e vettori per
     * menu della tipologia del telefono */
    public static String[] tipologia = {"MODALITA' TELEFONO","abitazione", "cellulare", "aziendale"};
    /* definizione dei vari file necessari
     * al funzionamento del programma */
    public static String[] filePath = {"data/rubrica.json",
            "data/registroNormale.csv",
            "data/registroNascosto.csv"};
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
        "Cambia stato contatto",
        "Chiudi rubrica"};

        /* creazione scanner e console
        * per la lettura delle password */
        Scanner scanner = new Scanner(System.in);
        Console console = System.console();
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
            /* creo anche i file CSV con l'aggiunta dell'intestazione */
            createNewFile(filePath[1]);
            createNewFile(filePath[2]);
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
                /* ricerca e visualizzazione dei contatti trovati */
                case 3:
                    ricercaContatti(rubrica, scanner);
                    break;
                /* ordinamento della rubrica secondo
                * nome e cognome */
                case 4:
                    rubrica.ordinaContatti(); //ordinamento
                    /* riporto che l'operazione è stata eseguita */
                    System.out.println("Ordinamento rubrica effettuato");
                    Wait(3);
                    break;
                /* effettuazione di una chiamata */
                case 5:
                    effettuaChiamata(rubrica, scanner);
                    break;
                /* viene fatto visualizzare l'elenco di tutti i contatti,
                * con un controllo per la visualizzazione di eventuali contatti
                * nascosti */
                case 6:
                    rubrica.visualizzaContatti(checkPassword(rubrica.getPassword(), scanner)); //vengono visualizzati anche i contatti nascosti se la password è corretta
                    continuaAzione(scanner);
                    break;
                /* vengono visualizzate le ultime chiamate,
                * dalla più recente alla meno recente */
                case 7:
                    rubrica.visualizzaRegistro(checkPassword(rubrica.getPassword(), scanner)); //vengono visualizzate le chiamate effettuate in ordine
                    continuaAzione(scanner);
                    break;
                /* viene cambiato lo stato del contatto */
                case 8:
                    cambiaStatoContatto(rubrica, scanner);
                    break;
                /* chiusura del programma e salvataggio dati */
                default:
                    System.out.println("Fine programma");
                    Wait(3);
                    rewriteFileJSON(filePath[0], rubrica.toJSON()); //salvataggio file
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
        /*char[] input1;
        char[] input2;*/ //array di strighe che vengono utilizzati per l'inserimento della password
        int nMax;

        /* chiedo l'inserimento dei dati */
        System.out.println("PRIMO AVVIO\nCrea la tua rubrica");
        do {
            System.out.println("Inserisci la password: ");
            password= scanner.next();
            System.out.println("Inserisci nuovamente la tua password per confermare");
            password2=scanner.next();

            /* ricostituzione stringhe */
            /*password = new String(input1);
            password2 = new String(input2);*/

            /* confronto se le due password sono uguali,
            * altrimenti richiedo di nuovo l'input */
            if(!password.equals(password2))
                messaggioErrore(5);
        }while(!password.equals(password2)); //richiedo la password due volte per motivi di sicurezza
        do {
            nMax = safeIntInput("Inserisci ora il numero di ultime chiamate che vuoi visualizzare nella tua cronologia: ", scanner);
            /* se l'input è sbagliato, restituisco un messaggio di errore */
            if(nMax<=0)
                messaggioErrore(1);
        }while(nMax<=0);

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
        char[] pw;
        Console console = System.console(); //creo la console

        /* input dati */
        try{
            pw = console.readPassword("Inserisci la password per accedere ai dati nascosti:");
            input = new String(pw);
        }catch (NullPointerException e){
            System.out.println("Inserisci la password per accedere ai dati nascosti:");
            input = scanner.nextLine();
        }catch(Exception e){
            scanner.nextLine();
            input=scanner.nextLine();
        }

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

        inputDatiBase(datiBase, false, scanner); //input dei dati base
        /* se il contatto esiste già,
        * esco dal metodo */
        if(rubrica.getContatti()!=null && rubrica.ricercainElencoContatti(datiBase[0], datiBase[1], datiBase[2], true)[0]>=0){
            messaggioErrore(4);
            return;
        }

        /* richiedo in inserimento il tipo di contratto */
        tipo=tipoContratto.valueOf(tipologia[sceltaTipologia(scanner)]);

        /* richiesta inserimento dati optional */
        System.out.println("Vuoi inserire altre informazioni? Digita S per confermare");
        input=scanner.next().charAt(0);
        scanner.nextLine();

        /* se la risposta è affermativa,
        vengono inseriti i dati aggiuntivi */
        if(Character.toUpperCase(input)=='S'){
            System.out.println("Inserisci nickname");
            stringaExtra=scanner.nextLine()+",";
            stringaExtra+=inputSoloNumeriStringa("Inserisci il secondo numero", scanner)+",";
            System.out.println("Inserisci email");
            stringaExtra+=scanner.nextLine();
        }

        /* chiedo se devo inserire i dati nel registro dei contatti
        * normali o dei contatti nascosti */
        System.out.println("Per registrare nei contatti normali, premi INVIO.\n" +
                "Per registrare nei contatti nascosti, inserisci la password corretta");
        siNascosto=checkPassword(rubrica.getPassword(), scanner);

        /* creazione stringa con informazioni base
         * e creazione oggetto contatto */
        Contatto contatto = new Contatto(datiBase[0], datiBase[1], datiBase[2], tipo, stringaExtra, siNascosto); //creo il contatto
        rubrica.inserimento(contatto); //inserisco il nuovo contatto nella rubrica
    }

    /**
     * Metodo per rimuovere contatto distinguendo tra caso normale e caso nascosto
     * @param rubrica
     * @param scanner
     */
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
                vetInput[2]=inputSoloNumeriStringa("Inserisci il numero di telefono", scanner);
            }while(!ricerca && vetInput[2].isBlank());

            /* possibile errore in caso di mancato compilamento
             * di tutti i dati */
            if(vetInput[0].isBlank() && vetInput[1].isBlank() && vetInput[2].isBlank())
                messaggioErrore(3);
        }while(vetInput[0].isBlank() && vetInput[1].isBlank() && vetInput[2].isBlank()); //controllo finale per verificare se almeno un campo è stato compilato
    }

    /**
     * Metodo che gestisce la ricerca di contatti all'interno
     * della rubrica e ne fornisce la visualizzaziome.
     * Qui non viene ritornato un solo contatto, ma anche risltati simili,
     * che per esempio hanno lo stesso nome, lo stesso cognome ecc.
     * @param rubrica - oggetto all'interno del quale ricercare
     * @param scanner - per prendere in input alcuni dati
     */
    private static void ricercaContatti(Rubrica rubrica, Scanner scanner){
        /* dichiarazione variabili e vettori */
        String[] datiBase = new String[3];
        int[] posizione;

        inputDatiBase(datiBase, true, scanner); //richiedo l'inserimento di nome, cognome, telefono
        posizione=rubrica.ricercainElencoContatti(datiBase[0], datiBase[1], datiBase[2], true); //ricerco in tutti e due i registri, visto che un singolo contatto può sempre essere reso disponibile

        /* per tutti i valori trovati, stampo l'array con
        * le informazioni del contatto */
        if(posizione[0]<0) //prima però controllo se sono stati trovati degli elementi
        {
            System.out.println("Nessun elemento trovato");
            Wait(3);
        }
        else{
            System.out.println("Risultati attinenti alla ricerca:");
            /* visualizzo tutti i contatti trovati */
            for(int pos : posizione)
                System.out.println(rubrica.getContatto(pos).visualizza(false)+"\n");
            continuaAzione(scanner);
        }
    }

    /**
     * Metodo per effettuare una chiamata prendendo in input
     * i dati necessari
     * @param rubrica
     * @param scanner
     */
    private static void effettuaChiamata(Rubrica rubrica, Scanner scanner){
        /* dichiarazione variabili */
        int anno=0, mese=0, giorno=0, ora=0, min=0;
        int durata=0;
        boolean siNascosti;
        /* dichiarazione vettore */
        int[] posizione;
        String[] datiBase = new String[3];

        /* ricerco prima il contatto */
        siNascosti=checkPassword(rubrica.getPassword(), scanner); //controllo la password
        inputDatiBase(datiBase, false, scanner); //richiedo l'inserimento dei dati
        posizione=rubrica.ricercainElencoContatti(datiBase[0], datiBase[1], datiBase[2], siNascosti); //ricerco all'interno dei contatti un elemento con questi dati

        /* se il contatto non viene trovato,
        * il metodo si ferma nella sua esecuzione */
        if(posizione[0]<0){
            System.out.println("Nessun contatto trovato");
            Wait(3);
            return;
        }

        /* eseguo un ciclo dove ogni volta aggiungo
        * un valore a giorno, mese, anno data e ora,
        * in modo tale da compilare la data */
        for(int i=0;i<7;i++){
            ClrScr();
            System.out.printf("%d/%d/%d\t%d:%d\t%d min\n", anno, mese, giorno, ora, min, durata);

            switch(i){
                case 1 :
                    anno=safeIntInput("Inserisci l'anno: ", scanner);
                    break;
                case 2 :
                    mese=safeIntInput("Inserisci il mese: ", scanner);
                    break;
                case 3 :
                    giorno=safeIntInput("Inserisci il giorno", scanner);
                    break;
                case 4 :
                    ora=safeIntInput("Inserisci l'ora", scanner);
                    break;
                case 5 :
                    min=safeIntInput("Inserisci i minuti", scanner);
                    break;
                case 6 :
                    do{durata=safeIntInput("Inserisci la durata (in minuti)", scanner);}while(durata<=0);
                    break;
            }
        }
        dataOra data = new dataOra(anno, mese, giorno, ora, min); //creo la data

        /* solo se data e ora sono corrette posso
         * creare la chiamata e inserirla all'interno della rubrica*/
        if(data.isDataCorretta() && data.isOraCorretta()){
            Chiamata chiamata = new Chiamata(rubrica.getContatto(posizione[0]), data, durata); //creo la chiamata
            rubrica.registraChiamata(chiamata, chiamata.getContatto().getStato()); //la chiamata viene registrata

            /* scelgo inoltre in quale file salvare i
            * dati, in base allo stato del contatto */
            if(chiamata.getContatto().getStato())
                appendDataCSV(filePath[2], chiamata.toCSV()); //lo salvo nel registro nascosto
            else
                appendDataCSV(filePath[1], chiamata.toCSV()); //lo salvo nel registro normale
        }
    }

    /**
     * Metodo che cambia lo stato di un contatto registrato
     * (da normale a nascosto e viceversa)
     * @param rubrica - dove ricercare il contatto
     * @param scanner - per prendere in input i dati
     */
    public static void cambiaStatoContatto(Rubrica rubrica, Scanner scanner){
        /* dichiarazione vettori e variabili
        * per il funzionamento del metodo */
        int[] posizione;
        String[] datiBase = new String[3];

        if(!checkPassword(rubrica.getPassword(), scanner)) //prima di tutto controllo se la password è stata inserita correttamente
            return; //altrimenti non è possibile cambiare lo stato di un contatto

        inputDatiBase(datiBase, false, scanner); //poi prendo in input i dati
        posizione= rubrica.ricercainElencoContatti(datiBase[0], datiBase[1], datiBase[2], true); //ricerco i dati inseriti all'interno di una rubrica

        /* se non è stato trovato alcun contatto
        * interrompo l'esecuzione del metodo */
        if(posizione[0]<0){
            System.out.println("Nessun contatto trovato");
            Wait(3);
            return;
        }

        /* altrimenti cambio lo stato del contatto */
        rubrica.getContatto(posizione[0]).setStato();

        /* cambio il contatore che indica il numero
        * di contatti normali e nascosti presenti */
        if(rubrica.getContatto(posizione[0]).getStato()){
            rubrica.setContattiNormali(+1);
            rubrica.setContattiNascosti(-1);
        }else{
            rubrica.setContattiNormali(-1);
            rubrica.setContattiNascosti(+1);
        }

        /* restituisco output del cambiamento di stato
        * riuscito */
        System.out.println("Cambiamento stato utente avvenuto con successo!");
        Wait(5);
    }

    /**
     * Metodo che termina una volta che viene inserito un carattere.
     * Viene utilizzato per far riprendere l'attività del programma al momento
     * desiderato dall'utente.
     * @param scanner - per leggere il dato, anche se non viene considerato
     */
    private static void continuaAzione(Scanner scanner){
        System.out.println("\nPremi qualsiasi tasto per continuare");
        try {
            scanner.nextLine();
        }catch (Exception e){
            scanner.nextLine();
        }
    }

    /**
     * Metodo che permette di scegliere la tipologia di telefono da inserire
     * @param keyboard - scanner
     * @return valore intero scelto dall'utente
     */
    private static int sceltaTipologia(Scanner keyboard){
        int scelta;

        /* input tipologia */
        scelta=menu(tipologia, keyboard);

        return scelta; //ritorno scelta fatta
    }
}
