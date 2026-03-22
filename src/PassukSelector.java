import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.text.NumberFormatter;


public class PassukSelector extends JPanel {
    JComboBox<String> sefer;
    JFormattedTextField perek;
    JFormattedTextField passuk;
    JPanel inputs;
    JPanel labels;
    JButton submit;
    static NumberFormatter formatter;
    static {
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false); //
        formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
    }
    
    public PassukSelector() {
        super(new GridLayout(2, 1));
        labels = new JPanel(new GridLayout(1,3));
        labels.add(new JLabel("Sefer"));
        labels.add(new JLabel("Perek"));
        labels.add(new JLabel("Passuk"));

        sefer = new JComboBox<>(Chumash.books);
        perek = new JFormattedTextField(formatter);
        passuk = new JFormattedTextField(passuk);

        inputs = new JPanel(new GridLayout(1, 3));

        inputs.add(sefer);
        inputs.add(perek);
        inputs.add(passuk);
        add(labels);
        add(inputs);

        submit = new JButton("submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Chumash.getPassuk(0, 1, 50);
                    removeAll();
                } catch (NullPointerException a) {
                    JOptionPane.showMessageDialog(null, "We couldnt find that passuk\nPlease search again");
                } catch (IOException a) {
                    System.out.println("ioException");
                }
            }
        });
        add(submit);
    }
}
