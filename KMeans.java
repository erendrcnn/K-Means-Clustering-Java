package BIL211.Odev3;
// METIN EREN DURUCAN - 201101038
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class KMeans {
    public void baslat(Nokta model, GUI view){
        view.execute(model);
    }

    public static void clustering(Frame main, String inputName, String inputPath, String iterasyon, String kSayisiSec, ArrayList<Nokta> koordinatlar, ArrayList<Nokta> merkezler, Color[] renkler, HashMap<Nokta,Color> noktaRenk){
        GUI.paintClear(main);
        try {
            if (inputName != null && iterasyon != null && Integer.parseInt(iterasyon) >= 0 && kSayisiSec != null) {
                try {
                    koordinatlar = CSVOkuyucu.read(inputPath);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                // Alinan merkez sayisi ve iterasyon sayisi getirilir.
                int kSayisi = Integer.parseInt(kSayisiSec);
                int iSayisi = Integer.parseInt(iterasyon);

                for (int i = 0; i < kSayisi; i++) {
                    int xMerkez = (int) (Math.random() * 1280);
                    int yMerkez = (int) (Math.random() * 720);
                    merkezler.add(new Nokta(xMerkez,yMerkez));
                }

                GUI.paintPoint(main, koordinatlar, noktaRenk);
                Thread.sleep(2000);
                GUI.paintCenter(main, Color.GREEN, merkezler);
                Thread.sleep(3000);

                int[] minIndex = new int[koordinatlar.size()];
                double[] uzakliklar = new double[koordinatlar.size()];

                for (int iterasyonAdimi = 0; iterasyonAdimi < iSayisi; iterasyonAdimi++) {
                    int[] xYeni = new int[kSayisi];
                    int[] yYeni = new int[kSayisi];
                    int[] kumeSayisi = new int[kSayisi];

                    for (int i = 0; i < koordinatlar.size(); i++) {
                        Nokta nokta = koordinatlar.get(i);
                        uzakliklar[i] = Double.POSITIVE_INFINITY;

                        for (int j = 0; j < kSayisi; j++) {
                            double uzaklik = Nokta.getUzaklik(merkezler.get(j), nokta);
                            if (uzakliklar[i] > uzaklik) {
                                uzakliklar[i] = uzaklik;
                                minIndex[i] = j;
                            }
                            xYeni[minIndex[i]] += nokta.getX();
                            yYeni[minIndex[i]] += nokta.getY();
                            kumeSayisi[minIndex[i]]++;
                        }
                        noktaRenk.put(nokta, renkler[minIndex[i]]);
                    }

                    GUI.paint(main, koordinatlar, merkezler, noktaRenk);

                    for (int i = 0; i < kSayisi; i++) {
                        if( kumeSayisi[i] != 0 ) {
                            xYeni[i] = xYeni[i] / kumeSayisi[i];
                            yYeni[i] = yYeni[i] / kumeSayisi[i];
                            Nokta yeniMerkez;
                            try {
                                yeniMerkez = new Nokta(xYeni[i], yYeni[i]);
                            } catch (IllegalArgumentException exp2) {
                                yeniMerkez = merkezler.get(i);
                            }
                            merkezler.set(i, yeniMerkez);
                        }
                    }
                }

                GUI.paint(main, koordinatlar, merkezler, noktaRenk);
                merkezler.clear();
                Thread.sleep(1000);
            }
            else {
                JOptionPane.showMessageDialog(main, "Oncelikle iterasyon sayisi giriniz ve csv dosyasini seciniz.", "HATA!", JOptionPane.ERROR_MESSAGE);
            }

            koordinatlar.clear();
            merkezler.clear();
            noktaRenk.clear();

        } catch (NumberFormatException nfExp) {
            JOptionPane.showMessageDialog(main, "Iterasyon kismina gecerli bir sayi girin.", "HATA!", JOptionPane.ERROR_MESSAGE);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
