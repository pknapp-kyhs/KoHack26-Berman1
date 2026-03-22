import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class App {
    private static Voice[] voices;

    public static void main(String[] args) throws Exception {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager vm;
        vm = VoiceManager.getInstance();
        voices = vm.getVoices();
        for(Voice voice : voices){
            System.out.println(voice.getName() + " - " + voice.getDescription());
        }
        Voice voice  = vm.getVoice("kevin");
        voice.allocate();
        voice.speak("Hello World!");
        voice.deallocate();
    }
}
