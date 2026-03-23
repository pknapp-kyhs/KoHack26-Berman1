//quiz class, will contain code to open window and load questions
public class Quiz {
    //contains an array of questions to ask
    Question[] questions;
    int result;

    
    public Quiz(Question[] questions) {
        this.questions = questions;
        this.result = 0;
    }

    //a method to run the quiz program
    public void run() {
        //iterates through questions
        for (Question question: questions) {
            //asks question, then adds its value to the result
            addResult(question.ask());
        }

        //set up the leaderboard after the player finishes
        SortElement playerScore = new SortElement("Player", result);
        GUI.frame.dispose();
        Leaderboard.activate(playerScore);
    }

    //adds the result of a question to the running total
    public void addResult(boolean qResult) {
        if (qResult) {
            result += 10;
        }
    }

    //returns player score
    public int getResult() {
        return result;
    }

    public static Quiz makeQuiz(String path) {
        //Calls the parser to make a quiz object from the json file at the given path, then returns that quiz
        return Parser.parsePath(path, Quiz.class);
    }
}