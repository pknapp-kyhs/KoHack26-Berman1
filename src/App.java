import javax.swing.UIManager;

public class App {
    
    public static void main(String[] args) throws Exception {
        //question.askQuestion();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        LoginPage.activate();
    }
}