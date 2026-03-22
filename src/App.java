import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class App {
    private static Voice[] voices;

    public static void main(String[] args) throws Exception {
        Question question = new Question("What color is the sky?", "blue", 5);
        question.askQuestion();
    }
}