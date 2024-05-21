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
    public static void ClrScr() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Wait(int x) {
        try {
            Thread.sleep(1000 * x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int menu(String[] opzioni, Scanner keyboard) {
        int scelta;

        do {
            ClrScr();
            System.out.println("=== " + opzioni[0] + " ===");
            for (int i = 1; i < opzioni.length; i++) {
                System.out.println("[" + i + "]" + " " + opzioni[i]);
            }
            try {
                scelta = Integer.parseInt(keyboard.nextLine());
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

    /* metodo che indica il messaggio di errore */
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

    /* metodo per l'input sicuro di interi */
    public static int safeIntInput(String testoInput, Scanner scanner){
        /* dichiarazione variabili */
        int input=0;
        boolean check;

        do {
            check=false; //reinizializzo ogni volta la variabile
            /* input dati */
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
}
