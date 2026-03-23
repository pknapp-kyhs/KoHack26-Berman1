import javax.swing.UIManager;

import org.w3c.dom.Text;

public class App {   
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        LoginPage.activate();
    }
}