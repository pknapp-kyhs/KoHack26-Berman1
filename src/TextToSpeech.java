import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {
    static void speak(String textToSpeak){
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager vm;
        vm = VoiceManager.getInstance();
        Voice voice  = vm.getVoice("kevin");
        voice.allocate();
        voice.speak(textToSpeak);
        voice.deallocate();
    }
}
