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

        JButton button = new JButton("submit");
        JLabel label = GUI.addLabel("Enter email address", 20);
        GUI.panel.add(textField);
        GUI.panel.add(button);
        GUI.frame.getContentPane().add(GUI.panel);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            String inputText = textField.getText();
            if(checkEmailInput(inputText)){
                //proceed
            }
            else{
                label.setText("enter a valid email");
                
            }
            System.out.println("Button was clicked!");
            }
        });
    }

    static Boolean checkEmailInput(String email){
        if(email.indexOf("@") != -1)
        {
            if(email.substring(email.indexOf("@")).indexOf(".") != -1){
                return true;
            }
        }
        return false;
    }
}
