import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;

public class GUI {
    //private static JLabel label;
    public static JFrame frame;
    public static JPanel panel;
    //private static JTextArea textArea;
    static void createWindow(String windowTitle){
        frame = new JFrame(windowTitle);
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1)); 
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.NORTH); 
        frame.setVisible(true);
    }
    static JLabel addLabel(String text, int size){
        JLabel label = new JLabel(text);
        // Create a new Font with a specific name, style, and size (e.g., 20)
        Font newFont = new Font("Serif", Font.BOLD, size);

        // Set the new font to the component
        label.setFont(newFont);
        panel.add(label);
        return label;
    }
    static void addTextArea(String text, int size){
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setText(text);
        textArea.setLineWrap(true); // Enable line wrapping
        textArea.setWrapStyleWord(true); // Wrap at word boundaries

        Font newFont = new Font("Serif", Font.BOLD, size);

        // Set the new font to the component
        textArea.setFont(newFont);
        textArea.setEditable(false);
        // Put the JTextArea inside a JScrollPane for scrolling functionality
        JScrollPane scrollPane = new JScrollPane(textArea);
        // Set scrollbar policies
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        // Add the JScrollPane to the center of the frame
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
        
    }
    public static void addButton(String text, Runnable action) {
        JButton button = new JButton(text);
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
    }
}

