package BIL211.Odev3;
 // METIN EREN DURUCAN - 201101038
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GUI {
    private static String inputPath = null;
    private static String inputName = null;
    private static final Color[] renkler = {Color.BLUE, Color.RED, Color.BLACK, Color.ORANGE, Color.YELLOW, Color.GRAY, Color.PINK, Color.MAGENTA, Color.WHITE, Color.CYAN};
    private static JPanel tuslar;
    private static JFrame main;
    private static JTextField iterasyon;
    private static JComboBox<Integer> kSayisiSec;

    public void execute(Nokta model) {
        main = new JFrame("K-Means Clustering");
        main.setLayout(new BorderLayout());

        //Bu kodu koymazsak Jframe penceresini kapatsakta program kapanmaz sadece gorunurlugu false olur.
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Jframe herseyi kaplar ve Swing kutuphanesi J ile baslar.
        tuslar = new JPanel();
        //Formun Buyuklugunu belirliyoruz. (Cozunurluk: 720p)
        main.setSize(1280, 720);

        JLabel label1 = new JLabel("Iterasyon: ");
        iterasyon = new JTextField(5);

        JLabel label2 = new JLabel("K sayisi:(Center) ");
        kSayisiSec = new JComboBox<>();
        kSayisiSec.addItem(1);
        kSayisiSec.addItem(2);
        kSayisiSec.addItem(3);
        kSayisiSec.addItem(4);
        kSayisiSec.addItem(5);
        kSayisiSec.addItem(6);
        kSayisiSec.addItem(7);
        kSayisiSec.addItem(8);
        kSayisiSec.addItem(9);
        kSayisiSec.addItem(10);

        //Buton olusturuyoruz.
        JButton dosyaButon = new JButton("Dosyadan Sec");
        //ic sinif tanimladik (MouseAdapter) ve metotlarindan birini ustune yazdik.
        dosyaButon.addMouseListener(new MouseAdapter() {
            //Mouse tiklandigindaki olayi yakalar.
            @Override
            public void mousePressed(MouseEvent e) {
                // Kullanicinin bulundugu klasor tespit edilir.
                String userDirLocation = System.getProperty("user.dir");
                File userDir = new File(userDirLocation);
                // Dosya secimi icin frame acilir.
                JFileChooser dosyaSec = new JFileChooser(userDir);
                int durum = dosyaSec.showOpenDialog(null);

                if (durum == JFileChooser.APPROVE_OPTION) {
                    inputPath = dosyaSec.getSelectedFile().getAbsolutePath();
                    inputName = dosyaSec.getSelectedFile().getName();
                }

                if (inputName != null && !inputName.contains(".csv")){
                    JOptionPane.showMessageDialog(main, "Sadece csv dosyasi secilebilir.", "HATA!", JOptionPane.ERROR_MESSAGE);
                    inputPath = null;
                    inputName = null;
                }
            }
        });

        JButton baslatButon = new JButton("K-Means Clustering");
        baslatButon.addMouseListener(new MouseAdapter() {
            //Mouse tiklandigindaki olayi yakalar.
            @Override
            public void mousePressed(MouseEvent e) {
                KMeans.clustering(main, inputName, inputPath, iterasyon.getText(), kSayisiSec.getSelectedItem().toString(), model.getKoordinatlar(), model.getMerkezler(), renkler, model.getNoktaRenk());
            }
        });

        //Layout olusturulan form nesnelerinin nasil frame de yerlestirilecegini belirler.
        //FlowLayout kullanilarak sira ile koyar.
        tuslar.setLayout(new FlowLayout());
        //Buton-textfield-combobox eklenir.
        //Eger kodlari koymazsak Panelde gozukmezler.
        tuslar.add(label1);
        tuslar.add(iterasyon);
        tuslar.add(label2);
        tuslar.add(kSayisiSec);
        tuslar.add(dosyaButon);
        tuslar.add(baslatButon);
        // Framein SOUTH bolgesine Paneli ekler.
        main.add(tuslar, BorderLayout.SOUTH);

        //Frame ve Panel gorunur hale getirilir.
        tuslar.setVisible(true);
        main.setVisible(true);
    }

    // Ekran nokta ve merkezlerin en son durumunu bastirir.
    public static void paint(Frame frame, ArrayList<Nokta> koordinatlar, ArrayList<Nokta> merkezler, HashMap<Nokta, Color> noktaRenk)
    {
        paintClear(main);
        paintPoint(frame, koordinatlar, noktaRenk);
        paintCenter(frame, Color.GREEN, merkezler);
    }

    // Noktalari ekrana bastirir.
    public static void paintPoint(Frame frame, ArrayList<Nokta> koordinatlar, HashMap<Nokta, Color> noktaRenk)
    {
        Graphics ekran = frame.getGraphics();
        for (Nokta nokta: koordinatlar) {
            ekran.setColor(noktaRenk.get(nokta));
            ekran.drawOval(nokta.getX(), nokta.getY(), 6, 6);
            ekran.fillOval(nokta.getX(), nokta.getY(), 6, 6);
        }
    }

    // Merkezleri ekrana bastirir.
    public static void paintCenter(Frame frame, Color renk, ArrayList<Nokta> merkezler)
    {
        Graphics ekran = frame.getGraphics();
        for (Nokta merkez: merkezler) {
            ekran.setColor(renk);
            ekran.drawOval(merkez.getX(), merkez.getY(), 12, 12);
            ekran.fillOval(merkez.getX(), merkez.getY(), 12, 12);
        }
    }

    // Ekrani temizler.
    public static void paintClear(Frame frame)
    {
        Graphics ekran = frame.getGraphics();
        ekran.setColor(frame.getBackground());
        ekran.fillRect(0,0,1920,1080);
        tuslar.setVisible(false);
        tuslar.setVisible(true);
    }
}
