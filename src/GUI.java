import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;

public class GUI {
    private static JLabel label;
    private static JFrame frame;
    private static JPanel panel;
    private static JTextArea textArea;
    static void createWindow(String windowTitle){
        frame = new JFrame(windowTitle);
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); 
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.NORTH); 
        frame.setVisible(true);
    }
    static void addLabel(String text, int size){
        label = new JLabel(text);
        // Create a new Font with a specific name, style, and size (e.g., 20)
        Font newFont = new Font("Serif", Font.BOLD, size);

        // Set the new font to the component
        label.setFont(newFont);
        panel.add(label);
    }
    static void addTextArea(String text, int size){
        textArea = new JTextArea();
        Font newFont = new Font("Serif", Font.BOLD, size);
        textArea.setFont(newFont);
        textArea.setText(text);
        panel.add(textArea);
    }
}
