package utility;

import org.json.*;
import java.io.*;
import java.nio.file.*;

/**
 * Metodo che si occupa della gestione dei file JSON,
 * con la scrittura e la lettura
 */
public class jsonFIle {
    /**
     * Metodo che riscrive tutti i dati del file JSON
     *
     * @param path - percorso del file
     * @param object - oggetto da salvare
     */
    public static void rewriteFileJSON(String path, Object object)
    {
        try {
            FileWriter file = new FileWriter(path); //search file to write on
            file.write(object.toString()); //upload element to file
            file.flush();
            file.close(); //close file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per convertire la stringa
     * di dati in un array JSON
     * @param path
     */
    public static JSONObject readDataJSON(String path)
    {
        /* variable declaration
         * out of try-catch */
        String content;
        /* array creation */
        JSONObject object;

        /* using a string to catch all datas from JSON */
        try {
            content = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /* trasforming string into an array */
        object = new JSONObject(content);
        return object;
    }

    /**
     * Metodo per creare un nuovo file vuoto in una
     * cartella
     * @param path - percorso per la creazione del file
     */
    public static void createNewFile(String path) {
        Path file = Paths.get(path);

        if(Files.exists(file))
            return;

        String[] pathSection = path.split("/");

        // Definisci il percorso della cartella "data"
        Path cartellaData = Paths.get(pathSection[pathSection.length-2]);

        try {
            // Crea la cartella "data" se non esiste già
            if (!Files.exists(cartellaData)) {
                Files.createDirectory(cartellaData);
            }

            // Definisci il percorso del nuovo file nella cartella "data"
            Path filePath = cartellaData.resolve(pathSection[pathSection.length-1]);

            // Crea il nuovo file
            Files.createFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
