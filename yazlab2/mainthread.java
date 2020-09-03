package yazlab2;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;
import static yazlab2.arayuz.altsunucu;
import static yazlab2.arayuz.calismasay;
import static yazlab2.arayuz.dongu;
import static yazlab2.arayuz.kalanistek;
import static yazlab2.arayuz.kontrol;
import static yazlab2.arayuz.yuzde;
import static yazlab2.arayuz.ozellikler;
import static yazlab2.arayuz.panels;
import static yazlab2.arayuz.threadsayi;
public class mainthread implements Runnable{
    int kapasite=10000;
    int dolulukoran;
    int isteksay;
    int donussay;
    int istekaliszm=500;
    int donusveriszm=200;   

    @Override
    public void run() { 
        int isteka=0;
        int cevapb=0;
        arayuz.calismasay++;
        while(true){
            try {
                Thread.sleep(100);
                isteka=isteka+100;
                cevapb=cevapb+100;
                if(isteka%istekaliszm==0){
                    Random rnd = new Random();
                    int gelen = rnd.nextInt(1000);
                    if(kontrol==1){
                        gelen=0;
                    }
                    kalanistek+=gelen;
                }
                if(cevapb%donusveriszm==0){
                    Random rnd = new Random();
                    int cevaplanan = rnd.nextInt(51);
                    if(kalanistek-cevaplanan<0){
                        kalanistek=0;
                    }
                    else{
                        kalanistek-=cevaplanan;   
                    }
                    yuzde.setStringPainted(true);
                    yuzde.setMinimum(0);
                    yuzde.setMaximum(100);
                    float yuzdehesapla=(float) ((kalanistek/10000.0)*100);
                    Math.floor(yuzdehesapla);
                    yuzde.setValue((int)yuzdehesapla);
                       if(kalanistek>10000){
                          kalanistek=kapasite;
                    }
                    threadsayi.setMinimum(0);
                    threadsayi.setMaximum(100);
                    threadsayi.setValue(altsunucu.size()+1);
                    threadsayi.setStringPainted(true);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(mainthread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
