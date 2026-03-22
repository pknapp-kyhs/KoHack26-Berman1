import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import Perek.Perek;


public class App {
    private static Voice[] voices;

    public static void main(String[] args) throws Exception {
        TextToSpeech.speak("Hello World");
        String[][] params = {{"return_format","text_only"},{"version","hebrew"}};
        String sefaria = API.requestBody("https://www.sefaria.org/api/v3/texts", "Esther%201:1", params);
        Perek esther = Parser.parse(sefaria,Perek.class);
        System.out.println(esther);
    }
}