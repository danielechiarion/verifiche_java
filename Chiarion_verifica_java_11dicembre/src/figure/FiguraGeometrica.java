package figure;

/* per risolvere il problema finale decido
* di creare una classe FiguraGeometrica, la quale
* sarà estesa dalle classi Quadrato, Rettangolo, TriangoloRettangolo, Circonferenza.
*
* Facendo così è possibile eseguire il compareTo tra una figura geometrica ed un altra,
* (quindi tra un rettangolo e qualsiasi altra figura creata) senza dover agire in ogni classe
* e prevedere tutte le possibili figure da confrontare.
*
* All'interno di questa classe saranno presenti anche alcuni metodi comuni a tutte le figure create,
* come il calcolo dell'area e del perimetro, che serviranno a stampare le informazioni relative alla figura.
*
* In questo modo le righe di codice vengono ridotte e viene reso il tutto più efficiente.
* Sarà infatti possibile creare in futuro un altra figura che estenderà la classe figura geometrica
* e possedere tutti i metodi della classe genitore.
* Sarà quindi possibile anche per nuove figure fare il compareTo con le figure già esistenti. */
public class FiguraGeometrica {
    /* metodo che calcola l'area di una figura geometrica.
    * Verrà utilizzato per il getInformazioni() e per il compareTo().
    * Il metodo ritorna 0 in quanto non ho previsto attributi specifici per
    * una figura geometrica (i dati di un triangolo non sono gli stessi di una circonferenza).
    * Il metodo per il calcolo dell'area verrà poi sovrascritto da tutte le classi figlie */
    public double calcolaArea(){
        return 0;
    }

    /* metodo che calcola il perimetro di una figura geometrica.
     * Verrà utilizzato per il getInformazioni().
     * Il metodo ritorna 0 in quanto non ho previsto attributi specifici per
     * una figura geometrica (i dati di un triangolo non sono gli stessi di una circonferenza).
     * Il metodo per il calcolo del perimetro verrà poi sovrascritto da tutte le classi figlie */
    public double calcolaPerimetro(){
        return 0;
    }

    /* metodo che stampa le informazioni relative
    * agli attributi della classe indicata
    * (non viene inserito il calcolo dell'area e del perimetro).
    * Il metodo toString serve quindi a descrivere com'è composta la figura.
    *
    * Anche questo metodo sarà funzionale per il getInformazioni().
    *
    * Il metodo ritorna null perchè in una figuraGeometrica generica
    * non posso creare un output che sia ottimale per tutte le figure geometriche.
    * (in un triangolo rettangolo indico la dimensione dei lati, in una circonferenza ho bisogno del raggio) */
    @Override
    public String toString(){
        return null;
    }

    /* metodo che stampa le informazioni relative
    * alla figura geometrica, quindi combina
    * l'output delle caratteristiche della figura (toString()) ai valori
    * di area e perimetro della figura.
    * Questi ultimi saranno determinati dalla classe figlia */
    public String getInformazioni(){
        return String.format("%s\nArea: %.1f\tPerimetro: %.1f", this.toString(), this.calcolaArea(), this.calcolaPerimetro());
    }

    /* metodo che confronta due figure tra di loro
    * in base alla loro area.
    * Il metodo ritorna:
    *  -1 se la figura attuale (this) ha un'area minore di quella dell'altra figura
    *  0 se la figura attuale e l'altra figura hanno la stessa area
    *  1 se la figura attuale ha un'area maggiore di quella dell'altra figura */
    public int compareTo(FiguraGeometrica altro){
        return Double.compare(this.calcolaArea(), altro.calcolaArea());
    }
}
