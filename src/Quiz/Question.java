import javax.swing.*;
import java.awt.*;

public class Question {
    //instance variables
    String question;
    Answer[] answers;
    //sets up the icon used in the questions
    static ImageIcon icon;
    static {
        //make origional image icon from the file
        ImageIcon bigIcon = new ImageIcon("resources/logo.png");
        //convert it to an image so it can be scaled
        Image bigImage = bigIcon.getImage();
        //scale it down
        Image smallImage = bigImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        //make a new, smaller, image icon
        icon = new ImageIcon(smallImage);
    }

    public Question(String question, Answer[] answers) {
        if (question == null || answers == null || answers.length <= 0) {
            //debugging and error handling
            throw new IllegalArgumentException("Invalid Constructor inputs");
        }
        this.question = question;
        this.answers = answers;
    }
    
    //asks the question
    public boolean ask() {
        //returns whether the chosen answer was correct
        return answers[JOptionPane.showOptionDialog(null,question,"Question",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,icon,answers,0)].getResult();
    }
}