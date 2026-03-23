import javax.swing.*;

//a class that uses GUI.java to make and run a loginpage;
public class LoginPage {
    static JTextField textField;
    //set up the login page
    static public void activate(){
        
        GUI.createWindow("Home Page");
        textField = new JTextField(20);

        GUI.addLabel("Enter email address", 20);
        GUI.panel.add(textField);
        
        GUI.addButton("Submit", () -> checkEmailInput(textField.getText()));
        GUI.frame.getContentPane().add(GUI.panel);
        
        TextToSpeech.speak("Welcome to the login page. Please enter your email address to continue.");
    }

    //validates that the inputed email is an email adress.
    static void checkEmailInput(String email){
        if(email.indexOf("@") != -1)
        {
            if(email.substring(email.indexOf("@")).indexOf(".") != -1){
                System.out.println("Login successful");
                TextToSpeech.stopAudio();
                HomePage.activate();
                HomePage.email = email;
                return;
            }
        }
        textField.setText("Type in a valid email address");
        TextToSpeech.speak("Please enter a valid email address");
        
    }
}