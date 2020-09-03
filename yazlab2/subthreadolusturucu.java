/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab2;

import static yazlab2.arayuz.altsunucu;
import static yazlab2.arayuz.ozellikler;
public class subthreadolusturucu {
                public static synchronized void olustur(int isteksay){
                            Thread t = new Thread(new subthread());
                            subthread sb = new subthread();
                            synchronized(t){
                                sb.altkalanistek=isteksay/2;
                                ozellikler.add(sb);
                                altsunucu.add(t);
                                t.start(); 
                            }
                }
}
