public class QuizMaker {
    public static Quiz makeQuiz(String path) {
        Quiz out = Parser.parsePath(path, Quiz.class);
        return out;
    }
}