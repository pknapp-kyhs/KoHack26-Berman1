import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import java.awt.event.*;
public class PassukDisplay extends JFrame {

    String book;
    int perek;
    int passuk;
    
    String[][][] sefer;

    JPanel main;
    JLabel title;
    JTextArea hebrew;
    JTextArea english;
    JTextField position;
    JButton back;
    JButton forward;

    public PassukDisplay(int chumashBook, int perek, int passuk) {
        this(Chumash.books[chumashBook],perek,passuk);
    }

    public PassukDisplay(String book, int perek, int passuk) {
        this.book = book;
        this.perek = perek;
        this.passuk = passuk;
        try {
        sefer = Chumash.getPassuk(book, 0, 0);
        } catch (IOException e) {
            System.out.println("you shouldnt be able to reach this");
        }
        main = new JPanel(new GridLayout(3, 1));
        title = new JLabel(book);
        main.add(title);
        JPanel textDisplay = new JPanel(new GridLayout(1,2));
        hebrew = new JTextArea();
        hebrew.setEditable(false);
        hebrew.setLineWrap(true);
        hebrew.setWrapStyleWord(true);
        english = new JTextArea();
        english.setEditable(false);
        english.setLineWrap(true);
        english.setWrapStyleWord(true);
        textDisplay.add(hebrew);
        textDisplay.add(english);
        main.add(textDisplay);
        JPanel control = new JPanel(new GridLayout(1,3));
        back = new JButton("Previous");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
        position = new JTextField();
        position.setEditable(false);
        forward = new JButton("Next");
        forward.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goForward();
            }
        });
        control.add(back);
        control.add(position);
        control.add(forward);
        goToPassuk(perek, passuk);
        main.add(control);
        add(main);
    }

    public void goBack() {
        if (passuk == 1) {
            if (perek != 1) {
                goToPassuk(perek-1, sefer[perek-2].length);
            }
        } else {
            goToPassuk(perek,passuk-1);
        }
    }
    public void goForward() {
        if (passuk != sefer[perek-1].length) {
            goToPassuk(perek, passuk+1);
        } else {
            if (perek != sefer.length) {
                goToPassuk(perek + 1, 1);
            }
        }
    }
    public void goToPassuk(int perek, int passuk) {
        this.perek = perek;
        this.passuk = passuk;
        hebrew.setText(sefer[perek-1][passuk-1][0].replaceAll("&nbsp;"," "));
        english.setText(sefer[perek-1][passuk-1][1].replaceAll("&nbsp;", " "));
        position.setText("" + perek + ":" + passuk);
        TextToSpeech.speak(english.getText());
    }
}
