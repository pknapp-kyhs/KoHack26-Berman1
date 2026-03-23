import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TextToSpeech {
    static ExecutorService executor = Executors.newSingleThreadExecutor();

    static Voice voice;
    static void speak(String textToSpeak){
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager vm;
        vm = VoiceManager.getInstance();
        voice = vm.getVoice("kevin");
        executor.execute(() -> {
            voice.allocate();
            voice.speak(textToSpeak);
            voice.deallocate();
        });   
    }
    public static void stopAudio(){   
        voice.getAudioPlayer().cancel();
        voice.deallocate();       
    }
}