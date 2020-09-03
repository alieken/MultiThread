package yazlab2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static yazlab2.arayuz.altsunucu;
import static yazlab2.arayuz.kalanistek;
import static yazlab2.arayuz.mainthread;
import static yazlab2.arayuz.yuzde;
import static yazlab2.arayuz.panels;
import static yazlab2.arayuz.bars;
import static yazlab2.arayuz.dongu;
import static yazlab2.arayuz.forbarsicin;
import static yazlab2.arayuz.kalanistek;
import static yazlab2.arayuz.kontrol;
import static yazlab2.arayuz.model;
import static yazlab2.arayuz.oncekisize;
import static yazlab2.arayuz.ozellikler;
import static yazlab2.arayuz.silinenlerindex;
import static yazlab2.arayuz.silmesayisi;
import static yazlab2.arayuz.tablosayaci;
import static yazlab2.arayuz.texts;
import static yazlab2.arayuz.threadsayi;
import static yazlab2.arayuz.threadsayisi;
import static yazlab2.subthreadolusturucu.olustur;

public class subthread extends Thread implements Runnable {

    int kapasite = 5000;
    int dolulukoran = 0;
    int isteksay;
    int donussay;
    int istekaliszm = 500;
    int donusveriszm = 300;
    int altkalanistek = 0;
    //public static volatile int index;

    @Override
    public void run() {

        boolean bitir = true;
        int id = 0;
        int index = 0;
        int isteka = 0;
        int cevapb = 0;
        for (int i = 0; i < altsunucu.size(); i++) {
            if (altsunucu.get(i).getId() == Thread.currentThread().getId()) {
                index = i;
            }
        }
        if (silmesayisi < 1) {
            if (index <= 17) {
                panels.get(index).setVisible(true);
            }
        }

        if (altsunucu.size() < 3) {
            if (altsunucu.get(0).isAlive() == true && altsunucu.get(1).isAlive() == false) {
                altkalanistek = 0;
            } else if (altsunucu.get(0).isAlive() == true && altsunucu.get(1).isAlive() == true) {
                altkalanistek = ozellikler.get(1).altkalanistek;
            }
        } else {
            altkalanistek = ozellikler.get(index).altkalanistek;
        }
        forbarsicin++;
        int forbars = forbarsicin;

        while (true) {
            sunucugoster.goster(index);

            try {
                Thread.sleep(100);
                synchronized (altsunucu) {
                    for (int i = 0; i < altsunucu.size(); i++) {
                        if (altsunucu.get(i).getId() == Thread.currentThread().getId()) {
                            index = i;
                            break;
                        }
                    }
                }
                isteka = isteka + 100;
                cevapb = cevapb + 100;
                if (isteka % istekaliszm == 0 && isteka >= 500) {
                    Random rnd = new Random();
                    int gelen = rnd.nextInt(300);
                    if (kontrol == 1) {
                        gelen = 0;
                    }
                    if (kalanistek <= 0) {
                        gelen = 0;
                    }
                    altkalanistek += gelen;
                    kalanistek -= gelen;
                    isteksay = gelen;
                    ozellikler.get(index).altkalanistek = altkalanistek;
                }
                if (cevapb % donusveriszm == 0 && cevapb >= 300) {
                    Random rnd = new Random();
                    int cevaplanan = rnd.nextInt(51);
                    if (altkalanistek - cevaplanan < 0 && isteka > 1500 && index != 0 && index != 1) {
                        altkalanistek = 0;
                        Thread.sleep(300);
                        synchronized (altsunucu) {
                            synchronized (altsunucu) {
                                for (int i = 0; i < altsunucu.size(); i++) {
                                    if (altsunucu.get(i).getId() == Thread.currentThread().getId()) {
                                        index = i;
                                        break;
                                    }
                                }
                            }
                            if (index != 0 && index != 1) {
                                altsunucu.remove(index);
                                ozellikler.remove(index);
                                model.removeRow(index);
                            }

                        }
                        silmesayisi++;
                        silinenlerindex.add(index);
                        bitir = false;
                        if (bitir == false) {
                            if (forbars != 0 && forbars != 1) {
                                panels.get(forbars).setVisible(false);
                            }
                            break;
                        }
                        break;
                    } else {

                        altkalanistek -= cevaplanan;
                        if (altkalanistek <= 0) {
                            altkalanistek = 0;
                        }
                        ozellikler.get(index).altkalanistek = altkalanistek;
                    }
                    if (forbars <= 17) {
                        bars.get(forbars).setStringPainted(true);
                        bars.get(forbars).setMinimum(0);
                        bars.get(forbars).setMaximum(100);
                    }
                    float yuzdehesapla = (float) ((altkalanistek / 5000.0) * 100);
                    ozellikler.get(index).dolulukoran = (int) Math.ceil(yuzdehesapla);
                    if (yuzdehesapla > 70 && altsunucu.size() == ozellikler.size()) {
                        Thread.sleep(300);
                        if (kontrol == 0) {
                            threadsayisi++;
                            oncekisize = altsunucu.size();
                            Thread t = new Thread(new subthread());
                            subthread sb = new subthread();
                            synchronized (altsunucu) {
                                tablosayaci++;
                                String s = "Altsunucu" + " " + (tablosayaci);
                                Object[] eklenecek = {s, String.valueOf(yuzdehesapla / 2)};
                                model.addRow(eklenecek);
                                olustur(altkalanistek);
                            }
                            altkalanistek /= 2;
                        }
                    }
                    Math.ceil(yuzdehesapla);
                    if (forbars <= 17) {
                        bars.get(forbars).setValue((int) yuzdehesapla);
                    }
                    threadsayi.setMinimum(0);
                    threadsayi.setMaximum(100);
                    threadsayi.setValue(altsunucu.size() + 1);
                    threadsayi.setStringPainted(true);
                }
            } catch (Exception ex) {
            }
        }

    }
}
