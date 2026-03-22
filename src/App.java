import javax.swing.UIManager;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import Perek.Perek;


public class App {
    
    public static void main(String[] args) throws Exception {
        //question.askQuestion();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //LoginPage.activate();
        String[][] params = {{"return_format","text_only"},{"version","hebrew"}};
        String sefaria = API.requestBody("https://www.sefaria.org/api/v3/texts", "Esther%201:5", params);
        Perek esther = Parser.parse(sefaria,Perek.class);
        System.out.println(esther);
        String passukOne = esther.versions[0].text[0][0];
        System.out.println(passukOne);
        //QuizMaker.makeQuiz("resources/quiz.json").run();
    }
}