import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;

public class HomePage {
    
    static public void activate(){
        
        GUI.createWindow("Home Page");
        
        JButton button = new JButton("Test Button");
        GUI.panel.add(button);
        GUI.frame.getContentPane().add(GUI.panel);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // Code to execute when the button is clicked
            System.out.println("Button was clicked!");
    }
});
    }
}
