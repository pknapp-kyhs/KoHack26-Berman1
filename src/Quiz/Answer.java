//a class to hold a potential answer to a question
public class Answer {
    //a result, representing the countries that answer would indicate
    boolean result;
    //the display text of the question
    String text;
    
    public Answer(boolean result, String text) {
        //checks parameter validity
        this.text = text;
        this.result = result;
    }

    //Returns the text of the answer
    public String getText() {
        return text;
    }
    //returns the weight values of this answer. This is an array of ints. Each int represents a different country.
    public boolean getResult() {
        return result;
    }
    public String toString() {
        return text;
    }
}