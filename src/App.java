import javax.swing.UIManager;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import Perek.Perek;


public class App {
    
    public static void main(String[] args) throws Exception {
        //question.askQuestion();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        LoginPage.activate();
    }
}