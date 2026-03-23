import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.*;
import java.util.stream.IntStream;
public class PassukDisplay extends JFrame {

    String book;
    int perek;
    int passuk;
    
    String[][][] sefer;

    JLabel title;
    JTextArea hebrew;
    JTextArea english;
    JComboBox<Integer> perekBox;
    JComboBox<Integer> passukBox;
    JButton back;
    JButton forward;

    boolean watch = false;

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
        //add the top layer (title and home button)
        JPanel top = new JPanel(new GridLayout(1,2));
        title = new JLabel(book);
        JButton homeButton = makeButton("Home Page",()->{dispose(); HomePage.activate();});
        top.add(title);
        top.add(homeButton);
        main.add(top);

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
        //set up the position control
        JPanel position = new JPanel(new GridLayout(1, 2));
        perekBox = makeComboBox(sefer.length, ()->perekCallback());
        perekBox.setSelectedItem(Integer.valueOf(perek));
        passukBox = makeComboBox(sefer[perek].length, ()->passukCallback());
        passukBox.setSelectedItem(Integer.valueOf(passuk));
        position.add(perekBox);
        position.add(passukBox);
        //bakc to buttons
        forward = makeButton("Next", ()->goForward());
        control.add(back);
        control.add(position);
        control.add(forward);
        main.add(control);

        //start up by opening to the selecting passuk
        goToPassuk(perek, passuk);
        add(main);
        watch = true;
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
    //make a combo box to select passuk or perek number up to the max in a single line
    public JComboBox<Integer> makeComboBox(int max, Runnable callback) {
        Integer[] nums = IntStream.rangeClosed(1, max).boxed().toArray(Integer[]::new);
        JComboBox<Integer> out = new JComboBox<Integer>(nums);
        out.addActionListener(e -> {if (watch) {callback.run();}});
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

    //switches position when perek is edited
    public void perekCallback() {
        goToPassuk((int)perekBox.getSelectedItem(), 1);
    }
    //switches position when passuk is edited
    public void passukCallback() {
        
        goToPassuk(perek, (int)passukBox.getSelectedItem());
    }

    //goes to a particular perek and passuk in the loaded sefer
    public void goToPassuk(int perek, int passuk) {
        watch = false;
        //stops previous audio from passuk being left behind
        TextToSpeech.stopAudio();
        //sets the instance variables to reflect new location
        if (this.perek != perek) {
            this.perek = perek;
            passukBox.setModel(new DefaultComboBoxModel<Integer>(IntStream.rangeClosed(1, sefer[perek-1].length).boxed().toArray(Integer[]::new)));
        }
        this.perek = perek;
        this.passuk = passuk;
        //edits displays of hebrew and english text and possition
        hebrew.setText(sefer[perek-1][passuk-1][0].replaceAll("&nbsp;"," "));
        english.setText(sefer[perek-1][passuk-1][1].replaceAll("&nbsp;", " "));
        perekBox.setSelectedItem(Integer.valueOf(perek));
        passukBox.setSelectedItem(Integer.valueOf(passuk));
        watch = true;
        //reads new passuk
        TextToSpeech.speak(english.getText());
    }
}