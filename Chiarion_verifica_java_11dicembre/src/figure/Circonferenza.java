package figure;

public class Circonferenza extends FiguraGeometrica{
    private double raggio;

    public Circonferenza(double raggio) {
        this.raggio = raggio;
    }

    public double getRaggio() {
        return raggio;
    }

    public void setRaggio(double raggio) {
        this.raggio = raggio;
    }

    @Override
    public double calcolaArea() {
        return Math.PI * raggio * raggio;
    }

    /* metodo che calcola il perimetro di una circonferenza
    * conoscendo il raggio e moltiplicandolo per il doppio di pigreco */
    @Override
    public double calcolaPerimetro() {
        return 2 * Math.PI * this.raggio;
    }

    /* metodo compareTo che confronta una
    * circonferenza con un altra
    * in base alla loro area.
    * Il metodo ritorna:
    *  -1 se l'area dell'attuale (this) circonferenza ha un'area minore di quella dell'altra circonferenza
    *  0 se le aree delle due circonferenze sono uguali tra di loro
    *  1 se l'area dell'attuale (this) circonferenza ha un'area maggiore di quella dell'altra circonferenza */
    public int compareTo(Circonferenza altra) {
        return Double.compare(this.calcolaArea(), altra.calcolaArea());
    }

    /* metodo che stampa le caratteristiche
    della circonferenza (raggio) */
    @Override
    public String toString(){
        return String.format("Circonferenza con raggio %.1f", this.raggio);
    }
}
