import javax.swing.*;
public class PassukDisplay extends JFrame {

    int book;
    int perek;
    int passuk;

    String hebrew;
    String english;
    
    String[][][] sefer;
    public PassukDisplay(int book, int perek, int passuk) {
        this.book = book;
        this.perek = perek;
        this.passuk = passuk;
        
        
    }
}
