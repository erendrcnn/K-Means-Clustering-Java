package BIL211.Odev3;
// METIN EREN DURUCAN - 201101038
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVOkuyucu {
    public static ArrayList<Nokta> veriler = new ArrayList<>();

    public static ArrayList<Nokta> read(String csvDosyasi) throws Exception {
        Scanner sc = new Scanner(new File(csvDosyasi));

        while (sc.hasNext()) {
            String deger = sc.next();
            int xDeger = Integer.parseInt(deger.substring(0, deger.indexOf(',')));
            int yDeger = Integer.parseInt(deger.substring(deger.indexOf(',') + 1));
            veriler.add(new Nokta(xDeger,yDeger));
        }
        sc.close();

        return veriler;
    }
}