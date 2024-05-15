package tools;

import java.util.Scanner;
public class utilty {
    /**
     * Metodo per la pulizia di uno schermo
     * utilizzando cmd di Windows
     */
    public static void ClrScr() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo per l'attesa
     * @param x
     */
    public static void Wait(int x) {
        try {
            Thread.sleep(1000 * x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che visualizza in output i vari messaggi
     * di errore
     * @param value - codice dell'errore
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
            default:
                System.out.println("");
        }
        Wait(3);
    }

    /**
     * Metodo per l'input sicuro di numeri interi,
     * evitando errori di dominio
     * @param testoInput - richiesta testuale da inserire
     * @param scanner - scanner per l'input dati
     * @return dato controllato
     */
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
                System.out.println("Dominio errato!");
                Wait(3);
                System.out.println("Riprova");
                check=true; //modifico la variabile
                scanner.nextLine();
            }
        }while (check);

        return input; //ritorno valore
    }
}
