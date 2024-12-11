package figure;

public class Quadrato extends FiguraGeometrica{
    private double lato;

    public Quadrato(double lato) {
        this.lato = lato;
    }

    public double getLato() {
        return lato;
    }

    public void setLato(double lato) {
        this.lato = lato;
    }

    @Override
    public double calcolaArea() {
        return lato * lato;
    }

    /* metodo per il calcolo del perimetro */
    @Override
    public double calcolaPerimetro() {
        return this.lato*4;
    }

    /* metodo per l'output delle caratteristiche
    * relative al quadrato (dimensione lato) */
    @Override
    public String toString() {
        return String.format("Quadrato di lato %.1f", this.lato);
    }

    /* metodo compareTo che confronta
    * l'area di un quadrato con l'area di un
    * altro quadrato.
    *
    * Il metodo ritorna:
    *  -1 se l'area dell'attuale (this) quadrato ha un'area minore di quella dell'altro quadrato
    *  0 se le aree dei due quadrati sono uguali tra di loro
    *  1 se l'area dell'attuale (this) quadrato ha un'area maggiore di quella dell'altro quadrato */
    public int compareTo(Quadrato altro){
        return Double.compare(this.calcolaArea(), altro.calcolaArea());
    }
}
