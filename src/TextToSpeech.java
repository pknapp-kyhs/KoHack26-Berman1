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
        //sets up the settings in the setting page, so they can be used to set up the speaker
        slider = new JSlider(100, 300, (int)rate);
        slider.setMajorTickSpacing(25);
        onOff = new JCheckBox("", true);
    }

    //a method to play any string that can be used anywhere
    static void speak(String textToSpeak){
        //only runs if the screenreader is enabled in settings (it defaults to enabled)
        if (onOff.isSelected()) {
            //sets up the voice
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            VoiceManager vm;
            vm = VoiceManager.getInstance();
            voice = vm.getVoice("kevin");
            //sets the speed based on user settings (default is 150 wpm, range is 100-300)
            voice.setRate(slider.getValue());
            //play the voice
            executor.execute(() -> {
                voice.allocate();
                voice.speak(textToSpeak);
                voice.deallocate();
            });
        }
    }
    //a method to cut off the currently playing audio, useful for when a screen is switching to something new
    public static void stopAudio(){   
        voice.getAudioPlayer().cancel();
        voice.deallocate();       
    }

    //a method to open the settings page.
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