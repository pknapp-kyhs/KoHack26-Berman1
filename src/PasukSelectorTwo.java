import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class PasukSelectorTwo {
    static JComboBox<String> sefer = null;
    static JTextArea perek = null;
    static JTextArea pasuk = null;
    public static void activate(){
        
        GUI.createWindow("Passuk Selector", 1,3);
        GUI.addLabel("Sefer", 20);
        GUI.addLabel("Perek", 20);
        GUI.addLabel("Passuk", 20);
        GUI.addPanel(1, 3, "South");
        GUI.addButton("Submit", () -> {
            try {
                int seferNum = sefer.getSelectedIndex();
                int perekNum = Integer.parseInt(perek.getText());
                int passukNum = Integer.parseInt(pasuk.getText());
                Chumash.getPassuk(seferNum, perekNum, passukNum);
                PassukDisplay reader = new PassukDisplay(seferNum,perekNum,passukNum);
                GUI.frame.dispose();
                reader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                reader.setExtendedState(JFrame.MAXIMIZED_BOTH);
                reader.setVisible(true);
            } catch (NullPointerException a) {
                JOptionPane.showMessageDialog(null, "We couldnt find that passuk\nPlease search again");
            } catch (IOException e) {
                System.out.println("at this point i forget what this does.");
            }
        });
        GUI.addPanel(1,3, "Center");
        sefer = GUI.addDropdown(Chumash.books, () -> {});
        perek = GUI.addTextArea("", 20);
        pasuk = GUI.addTextArea("", 20);
        
        
    }
}
