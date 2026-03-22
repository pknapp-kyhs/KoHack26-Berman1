import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;

public class LoginPage {
    
    static public void activate(){
        
        GUI.createWindow("Home Page");
        
        JTextField textField = new JTextField(20);

        //JButton button = new JButton("submit");
        JLabel label = GUI.addLabel("Enter email address", 20);
        GUI.panel.add(textField);
        
        GUI.addButton("Submit", () -> checkEmailInput(textField.getText()));
        GUI.frame.getContentPane().add(GUI.panel);
    }

    static void checkEmailInput(String email){
        if(email.indexOf("@") != -1)
        {
            if(email.substring(email.indexOf("@")).indexOf(".") != -1){
                //email worked
            }
        }
        //email did not work
    }
}
