package BIL211.Odev3;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

// METIN EREN DURUCAN - 201101038
public class Nokta {

    private int x;
    private int y;
    private static ArrayList<Nokta> koordinatlar = new ArrayList<>();
    private static ArrayList<Nokta> merkezler = new ArrayList<>();
    private static HashMap<Nokta, Color> noktaRenk = new HashMap<>();

    public Nokta() {
        this.x = 0;
        this.y = 0;
    }

    public Nokta(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newX) { this.x = newX; }

    public void setY(int newY) { this.y = newY; }

    public static ArrayList<Nokta> getKoordinatlar() { return koordinatlar; }

    public static ArrayList<Nokta> getMerkezler() { return merkezler; }

    public static HashMap<Nokta, Color> getNoktaRenk() { return noktaRenk; }

    public static void setKoordinatlar(ArrayList<Nokta> koordinatlar) { Nokta.koordinatlar = koordinatlar; }

    public static void setMerkezler(ArrayList<Nokta> merkezler) { Nokta.merkezler = merkezler; }

    public static void setNoktaRenk(HashMap<Nokta, Color> noktaRenk) { Nokta.noktaRenk = noktaRenk; }

    public static double getUzaklik(Nokta nokta1, Nokta nokta2){
        return Math.sqrt((nokta1.getY() - nokta2.getY())*(nokta1.getY() - nokta2.getY()) + (nokta1.getX() - nokta2.getX())*(nokta1.getX() - nokta2.getX()));
    }

    @Override
    public String toString() {
        return (x + "," + y);
    }

}