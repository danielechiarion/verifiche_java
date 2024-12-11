package figure;

/* a differenza di tutte le altre figure,
* la classe Poligono non estende la classe FiguraGeometrica
* in quanto il metodo toString() di questa classe, come previsto da consegna,
* non segue le indicazioni del toString() delle altre figure geometriche. */
public class Poligono {
    private int numeroLati;
    private double lunghezzaLato;

    /* metodo costruttore che prende in input
    * il numero di lati e la lunghezza di ogni lato.
    *
    * Secondo quanto stabilito dalla consegna,
    * viene considerato il poligono come regolare,
    * quindi con tutti i lati congruenti tra loro. */
    public Poligono(int numeroLati, double lunghezzaLato)throws Exception {
        /* prima di istanziare un poligono,
        * effetto un controllo sulla validità dei dati inseriti,
        * in aggiunta alle verifiche che dovrebbero essere fatte nella parte front-end.
        * In particolare, controllo che il numero di lati sia minore di 2 (un poligono
        * ha minimo 3 lati) e controllo che la lunghezza del lato non sia negativa o nulla. */
        if(numeroLati<=2)
            throw new Exception("ERRORE! Un poligono deve avere almeno 3 lati.");
        if(lunghezzaLato<=0)
            throw new Exception("ERRORE! Non si può inserire una lunghezza di un lato negativa o nulla");

        this.numeroLati = numeroLati;
        this.lunghezzaLato = lunghezzaLato;
    }

    /* metodo che calcola il perimetro di un poligono
    * moltiplicando la lunghezza del singolo lato
    * per il numero di lati del poligono.
    *
    * Il poligono viene infatti considerato come regolare,
    * quindi con tutti i lati congruenti tra loro. */
    public double calcolaPerimetro(){
        return this.numeroLati * this.lunghezzaLato;
    }

    /* metodo che ritorna una stringa contenente la descrizione del poligono.
    * In particolare viene scritto il numero di lati, la lunghezza di ogni lato e il perimetro del poligono. */
    @Override
    public String toString(){
        /* %.1f è un segnaposto che stampa in output un numero
        * double visualizzando fino alla prima cifra decimale */
        return String.format("Poligono con %d lati di lunghezza %.1f. Perimetro: %.1f", this.numeroLati, this.lunghezzaLato, this.calcolaPerimetro());
    }
}
