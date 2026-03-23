public class HomePage {
    static void activate(){
        
        //Create a new quiz and run it
        Quiz quiz = Quiz.makeQuiz("resources/quiz.json");
        //dispose the previous page
        GUI.frame.dispose();
        //Create a new page and add labeld and buttons
        GUI.createWindow("Home Page");
        GUI.addLabel("Welcome to the Home Page!", 20);
        GUI.addButton("Chumash", () -> replace());
        GUI.addButton("Quiz", () -> quiz.run());
        GUI.addButton("Sign Out", () -> {
            //Upon signing out, take the user back to the login page and stop any audio that might be playing
            TextToSpeech.stopAudio();
            GUI.frame.dispose();
            LoginPage.activate();
        });

        //Use text to speech to welcome the user to the home page and explain their options
        TextToSpeech.speak("Welcome to the home page. Please select an option to continue. Option 1: Chumash. Option 2: Quiz.");
        
        
    }
    public static void replace() {
        //dispose the current page and open the passuk selector page
        GUI.frame.dispose();
        PasukSelector.activate();
    }
}