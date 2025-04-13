import figure.*;

public class Main {
    public static void main(String[] args) {
        Rettangolo rettangolo = new Rettangolo(15, 18);
        Quadrato quadrato = new Quadrato(6);
        TriangoloRettangolo triangolo = new TriangoloRettangolo(3, 4);
        System.out.println(rettangolo.getInformazioni());
        System.out.println(quadrato.getInformazioni());
        System.out.println(triangolo.getInformazioni());
        if (rettangolo.compareTo(quadrato) > 0) {
            System.out.println("Il rettangolo ha un'area maggiore del quadrato.");
        } else {
            System.out.println("Il quadrato ha un'area minore o uguale a quella del rettangolo.");
        }
    }
}
