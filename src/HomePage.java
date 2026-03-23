//a class that uses GUI.java to setup and run the home page
public class HomePage {
    //the email adress of the user
    public static String email = "";
    public static final String quizPath = "resources/quiz.json";
    static void activate(){
        TextToSpeech.stopAudio();
        //Create a new quiz
        Quiz quiz = Quiz.makeQuiz(quizPath);
        //dispose the previous page
        GUI.frame.dispose();
        //Create a new page and add labels and buttons
        GUI.createWindow("Home Page");
        GUI.addLabel("Welcome to the Home Page!", 20);
        GUI.addButton("Chumash", () -> {
            //dispose the current page and switch to pasuk selector
            GUI.frame.dispose();
            PasukSelector.activate();
        });
        //uses a built in method of the quiz class to ask all questions
        GUI.addButton("Quiz", () -> quiz.run());
        //uses the built in method of TextToSpeech class to switch to settings window
        GUI.addButton("Voice Settings", () -> TextToSpeech.voiceSettings());
        GUI.addButton("Sign Out", () -> {
            //Upon signing out, take the user back to the login page and stop any audio that might be playing
            TextToSpeech.stopAudio();
            GUI.frame.dispose();
            LoginPage.activate();
        });

        //Use text to speech to welcome the user to the home page and explain their options
        TextToSpeech.speak("Welcome to the home page. Please select an option to continue. Option 1: Chumash. Option 2: Quiz. Option 3: Voice Settings. Option 4: Sign Out.");
        
        
    }
}