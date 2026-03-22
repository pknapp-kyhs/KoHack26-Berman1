import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import Perek.Perek;


public class App {
    private static Voice[] voices;

    public static void main(String[] args) throws Exception {
        Question question = new Question("What color is the sky?דגעה", "blue", 5);
        //question.askQuestion();
        String[][] params = {{"return_format","text_only"},{"version","hebrew"}};
        String sefaria = API.requestBody("https://www.sefaria.org/api/v3/texts", "Esther%201", params);
        Perek esther = Parser.parse(sefaria,Perek.class);
        System.out.println(esther);
        String passukOne = esther.versions[0].text[0][0];
        question = new Question(passukOne + "\nTranslate to English", passukOne, 0);
        //question.askQuestion();

        HomePage.activate();
    }
}