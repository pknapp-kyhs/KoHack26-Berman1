//a class to make a Quiz object given a json of questions
public class QuizMaker {
    public static Quiz makeQuiz(String path) {
        Quiz out = Parser.parsePath(path, Quiz.class);
        return out;
    }
}