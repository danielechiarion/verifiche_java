package utility;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Classe per gestire le funzioni di un file CSV
 */
public class CSVFile {
    /**
     * Metodo che scrive in coda al file l'informazione inserita
     * @param filePath - percorso del file
     * @param content - contenuto da salvare
     * @throws IOException per permettere la scrittura e il salvataggio su file
     */
    public static void appendDataCSV(String filePath, String content){
        /* creazione di un filewriter */
        FileWriter fileWriter= null;
        try {
            fileWriter = new FileWriter(filePath,true); //inserisco TRUE per effettuare l'append
            /* se il file è vuoto, inserisco l'intestazione */
            if(contaRigheCSV(filePath)==0){
                writeDataCSV(filePath, generaIntestazione());
            }

            fileWriter.append(content+"\r\n"); //scrivo su file
            fileWriter.flush(); //svuoto il file
            fileWriter.close(); //chiudo il file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo che genera un'intestazione per i file che andremo ad utilizzare
     * @return stringa con l'intestazione da inserire */
    public static String generaIntestazione(){
        return "Data,Ora,Durata (min),Telefono,Cognome,Nome";
    }

    public static int contaRigheCSV(String filePath) {
        /* dichiarazione variabili */
        int cont=0;

        /* creazione fileReader e scanner */
        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(fileReader);

        /* fin quando è presente un'altra riga,
        * il contatore aumenta */
        while(scanner.hasNextLine())
            cont++;

        return cont; //ritorno il contatore una volta finito il conteggio
    }

    /**
     * Metodo che scrive il contenuto in un file JSON,
     * ma non lo aggiunge in coda
     * @param filePath - percorso del file
     * @param content - contenuto da inserire
     */
    public static void writeDataCSV(String filePath, String content){
        /* creazione fileWriter */
        FileWriter fileWriter;
        try {
            fileWriter=new FileWriter(filePath);
            fileWriter.write(content+"\r\n"); //inserisco contenuto
            fileWriter.flush(); //svuoto il buffer
            fileWriter.close(); //chiudo il file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
