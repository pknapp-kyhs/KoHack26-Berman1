import javax.swing.UIManager;

public class App {   
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        LoginPage.activate();
    }
}