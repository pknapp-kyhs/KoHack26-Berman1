import javax.swing.JPanel;

public class HomePage {
    static void activate(){
        TextToSpeech.speak("Welcome to the home page. Please select an option to continue.");
        TextToSpeech.speak("Option 1: Chumash. Option 2: Quiz.");

        Quiz quiz = QuizMaker.makeQuiz("resources/quiz.json");
        GUI.frame.dispose();
        GUI.createWindow("Home Page");
        GUI.addLabel("Welcome to the Home Page!", 20);
        GUI.addButton("Chumash", () -> replace());
        GUI.addButton("Quiz", () -> quiz.run());
    
        
        
    }
    public static void replace() {
        GUI.frame.dispose();
        PasukSelectorTwo.activate();
    }
}
