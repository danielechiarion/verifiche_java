package figure;

public class TriangoloRettangolo extends FiguraGeometrica {
    private double base;
    private double altezza;

    public TriangoloRettangolo(double base, double altezza) {
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

    /* metodo che calcola l'ipotenusa del triangolo rettangolo
    * conoscendo base e altezza.
    * Viene quindi applicato il teorema di Pitagora */
    public double getIpotenusa(){
        return Math.sqrt(Math.pow(this.base, 2) + Math.pow(this.altezza, 2));
    }

    @Override
    public double calcolaArea() {
        return (base * altezza) / 2;
    }

    /* metodo che calcola il perimetro di un triangolo
    * rettangolo sommando i valori di ipotenusa, base e altezza */
    @Override
    public double calcolaPerimetro() {
        return this.altezza+this.base+this.getIpotenusa();
    }

    /* metodo che confronta
    * un triangolo rettangolo con un altro
    * in base alla loro area.
    * Il metodo ritorna:
    *  -1 se l'area dell'attuale (this) triangolo rettangolo è minore di quella dell'altra triangolo rettangolo
    *  0 se le aree dei due triangoli rettangoli sono uguali tra di loro
    *  1 se l'area dell'attuale (this) triangolo rettangolo ha un'area maggiore di quella dell'altro triangolo rettangolo */
    public int compareTo(TriangoloRettangolo altro) {
        return Double.compare(this.calcolaArea(), altro.calcolaArea());
    }

    /* metodo che stampa le caratteristiche del triangolo
    * rettangolo (base, altezza e ipotenusa) */
    @Override
    public String toString() {
        return String.format("Triangolo Rettangolo con base %.1f, altezza %.1f e ipotenusa %.1f", this.base, this.altezza, this.getIpotenusa());
    }
}
