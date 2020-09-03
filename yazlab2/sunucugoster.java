
package yazlab2;


import javax.swing.table.DefaultTableModel;
import static yazlab2.arayuz.altsunucu;
import static yazlab2.arayuz.model;
import static yazlab2.arayuz.ozellikler;


public class sunucugoster {
    public static void goster(int index){
        for (int i = 0; i < altsunucu.size(); i++) {
           String s="Altsunucu"+index;
           Object[] eklenecek = {s,String.valueOf(ozellikler.get(i).dolulukoran)};
           model.setValueAt(String.valueOf(ozellikler.get(i).dolulukoran), i, 1);
        }
    }
}