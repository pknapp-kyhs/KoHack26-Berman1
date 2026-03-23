//a class to hold a potential answer to a question
public class Answer {
    //a result, representing the countries that answer would indicate
    boolean answer;
    //the display text of the question
    String text;
    
    public Answer(boolean result, String text) {
        this.text = text;
        this.answer = result;
    }

    //Returns the text of the answer
    public String getText() {
        return text;
    }
    //returns whether the answer was correct
    public boolean getResult() {
        return answer;
    }
    public String toString() {
        return text;
    }
}