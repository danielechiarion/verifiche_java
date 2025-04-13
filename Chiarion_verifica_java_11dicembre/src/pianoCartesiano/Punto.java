package pianoCartesiano;

public class Punto {
    private double x;
    private double y;

    /* metodo costruttore della
    * classe punto,
    * che prende come parametri x e y (coordinate del punto),
    * necessarie per la sua creazione */
    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /* getter and setter
    * degli attributi del punto */
    public double getX(){
        return this.getX();
    }

    public double getY(){
        return this.getY();
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    /* metodo che calcola la distanza
    * tra un punto e un altro punto sul piano */
    public double distanza(Punto altro){
        /* viene applicato il teorema di pitagora avendo come
        * cateti la differenza delle ascisse e la differenza delle ordinate */
        return Math.sqrt(Math.pow(this.x - altro.getX(), 2) + Math.pow(this.y - altro.getY(), 2));
    }
}
