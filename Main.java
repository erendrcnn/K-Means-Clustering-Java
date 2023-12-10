package BIL211.Odev3;
// METIN EREN DURUCAN - 201101038

public class Main {
    public static void main (String[] args) {

        Nokta model = retriveNoktaFromDatabase();

        GUI view = new GUI();

        KMeans controller = new KMeans();

        controller.baslat(model, view);

    }

    private static Nokta retriveNoktaFromDatabase(){
        Nokta model = new Nokta();
        model.setX(0);
        model.setY(0);
        return model;
    }
}
