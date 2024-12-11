package figure;

public class Rettangolo extends FiguraGeometrica {
    private double base;
    private double altezza;

    public Rettangolo(double base, double altezza) {
        this.base = base;
        this.altezza = altezza;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getAltezza() {
        return altezza;
    }

    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }

    @Override
    public double calcolaArea() {
        return base * altezza;
    }

    public int compareTo(Rettangolo altro) {
        double areaQuesto = this.calcolaArea();
        double areaAltro = altro.calcolaArea();
        return Double.compare(areaQuesto, areaAltro);
    }

    /* metodo che calcola il perimetro di un rettangolo,
    raddoppiando la somma di base e altezze */
    @Override
    public double calcolaPerimetro(){
        return (this.base+this.altezza)*2;
    }
    /* RISPOSTA DOMANDA 3:
    * è utile in una classe avere metodi di calcolo in quanto
    * è possibile mantenere i dati aggiornati successivamente ad una modifica.
    * Se per esempio si andasse a cambiare il valore della base e/o dell'altezza,
    * un'ipotetico attributo perimetro non andrebbe ad aggiornarsi automaticamente.
    * Un metodo di calcolo, invece, esegue le operazioni considerando i dati al momento dell'invocazione,
    * quindi il risultato è certamente aggiornato.
    *
    * Un altro esempio è la determinazione dell'età di una persona.
    * Mettere questo dato come attributo rende più difficile il suo aggiornamento con il passare del tempo.
    * Utilizzare un metodo per il calcolo dell'età permette di avere al momento
    * dell'invocazione l'età corretta e aggiornata. */

    /* metodo che stampa le caratteristiche del rettangolo
    * (base e altezza) */
    @Override
    public String toString() {
        return String.format("Rettangolo con base %.1f e altezza %.1f", this.base, this.altezza);
    }
}
