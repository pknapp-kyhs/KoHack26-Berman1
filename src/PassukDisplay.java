import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import org.w3c.dom.Text;

import java.awt.event.*;
public class PassukDisplay extends JFrame {

    String book;
    int perek;
    int passuk;
    
    String[][][] sefer;

    JLabel title;
    JTextArea hebrew;
    JTextArea english;
    JTextField position;
    JButton back;
    JButton forward;

    //a specific constructor for sefarim in chumash that allows a number to be used instead of name of sefer
    public PassukDisplay(int chumashBook, int perek, int passuk) {
        this(Chumash.books[chumashBook],perek,passuk);
    }

    public PassukDisplay(String book, int perek, int passuk) {
        //set starting conditions
        this.book = book;
        this.perek = perek;
        this.passuk = passuk;
        
        //import the text
        try {
        sefer = Chumash.getPassuk(book, 0, 0);
        } catch (IOException e) {
            System.out.println("you shouldnt be able to reach this");
        }

        //set up the gui
        JPanel main = new JPanel(new GridLayout(3, 1));
        //add the top layer (title)
        title = new JLabel(book);
        main.add(title);

        //add the middle layer (passuk text in 2 languges)
        JPanel textDisplay = new JPanel(new GridLayout(1,2));
        hebrew = makeTextArea();
        english = makeTextArea();
        textDisplay.add(hebrew);
        textDisplay.add(english);
        main.add(textDisplay);

        //add the bottom layer (the navigation control)
        JPanel control = new JPanel(new GridLayout(1,3));
        back = makeButton("Previous", ()->goBack());
        position = new JTextField();
        position.setEditable(false);
        forward = makeButton("Next", ()->goForward());
        control.add(back);
        control.add(position);
        control.add(forward);
        main.add(control);

        //start up by opening to the selecting passuk
        goToPassuk(perek, passuk);
        add(main);
    }

    //general function to make a text area that wraps correctly and cannot be edited by the user in one line
    public JTextArea makeTextArea() {
        JTextArea out = new JTextArea();
        out.setEditable(false);
        out.setLineWrap(true);
        out.setWrapStyleWord(true);
        return out;
    }
    //general function to create a button with a name and simple callback in one line
    public JButton makeButton(String text, Runnable callback) {
        JButton out = new JButton(text);
        out.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                callback.run();
            }
        });
        return out;
    }

    //goes backward one passuk
    public void goBack() {
        //checks if it is the first passuk in a perek
        if (passuk == 1) {
            if (perek != 1) {
                //if it is the first passuk in perek and it is not the first perek, go to the last passuk in the previous perek
                goToPassuk(perek-1, sefer[perek-2].length);
            }
        } else {
            //if it is not the first passuk in the perek, go back one passuk
            goToPassuk(perek,passuk-1);
        }
    }
    //moves forward one passuk
    public void goForward() {
        //checks if it is last passuk in perek
        if (passuk != sefer[perek-1].length) {
            //if not, go to next passuk
            goToPassuk(perek, passuk+1);
            //if so, checks if if it the last perek
        } else if (perek != sefer.length) {
            //if not, goes to the first passuk of the next perek
            goToPassuk(perek + 1, 1);
        }
    }

    //goes to a particular perek and passuk in the loaded sefer
    public void goToPassuk(int perek, int passuk) {
        //stops previous audio from passuk being left behind
        TextToSpeech.stopAudio();
        //sets the instance variables to reflect new location
        this.perek = perek;
        this.passuk = passuk;
        //edits displays of hebrew and english text and possition
        hebrew.setText(sefer[perek-1][passuk-1][0].replaceAll("&nbsp;"," "));
        english.setText(sefer[perek-1][passuk-1][1].replaceAll("&nbsp;", " "));
        position.setText("" + perek + ":" + passuk);
        //reads new passuk
        TextToSpeech.speak(english.getText());
    }
}
