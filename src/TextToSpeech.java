import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;

public class TextToSpeech {
    static ExecutorService executor = Executors.newSingleThreadExecutor();

    static Voice voice;
    static float rate = 150;
    static JSlider slider;
    static JCheckBox onOff;

    static {
        slider = new JSlider(100, 300, (int)rate);
        slider.setMajorTickSpacing(25);
        onOff = new JCheckBox("", true);
    }
    static void speak(String textToSpeak){
        if (onOff.isSelected()) {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            VoiceManager vm;
            vm = VoiceManager.getInstance();
            voice = vm.getVoice("kevin");
            voice.setRate(slider.getValue());
            executor.execute(() -> {
                voice.allocate();
                voice.speak(textToSpeak);
                voice.deallocate();
            });
        }
    }
    public static void stopAudio(){   
        voice.getAudioPlayer().cancel();
        voice.deallocate();       
    }

    public static void voiceSettings() {
        stopAudio();
        GUI.frame.dispose();
        GUI.createWindow("Voice Settings", 3,2);
        GUI.addLabel("On/Off", 20);
        GUI.addLabel("Speed", 20);
        GUI.panel.add(onOff);
        GUI.panel.add(slider);
        GUI.addButton("Home", () -> {
            GUI.frame.dispose();
            HomePage.activate();
        });
        GUI.refresh();
    }
}