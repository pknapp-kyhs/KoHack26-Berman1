import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import Perek.Perek;


public class App {
    
    public static void main(String[] args) throws Exception {
        //question.askQuestion();
        HomePage.activate();
        String[][] params = {{"return_format","text_only"},{"version","hebrew"}};
        String sefaria = API.requestBody("https://www.sefaria.org/api/v3/texts", "Esther%201:1", params);
        Perek esther = Parser.parse(sefaria,Perek.class);
        System.out.println(esther);
        String passukOne = esther.versions[0].text[0][0];
        QuizMaker.makeQuiz("resources/quiz.json").run();
        System.out.println(Chumash.getPassuk(0, 0, 0));
    }
}