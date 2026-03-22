

public class HomePage {
    static void activate(){
        Quiz quiz = QuizMaker.makeQuiz("resources/quiz.json");
        GUI.frame.dispose();
        GUI.createWindow("Home Page");
        GUI.addLabel("Welcome to the Home Page!", 20);
        GUI.addButton("button lol", () -> quiz.run());
    }
}
