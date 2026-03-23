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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.NORTH); 
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    //create a new window on the desktop
    static void createWindow(String windowTitle, int x, int y){
        frame = new JFrame(windowTitle);
        panel = new JPanel();
        panel.setLayout(new GridLayout(x, y)); 
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.NORTH); 
    }
    //add a label element to main display
    static JLabel addLabel(String text, int size){
        JLabel label = new JLabel(text);
        // Create a new Font with a specific name, style, and size (e.g., 20)
        Font newFont = new Font("Serif", Font.BOLD, size);

        // Set the new font to the component
        label.setFont(newFont);
        panel.add(label);
        return label;
    }
    //add a text area element to main display
    static JTextArea addTextArea(String text, int size, boolean editable) {
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setText(text);
        textArea.setLineWrap(true); // Enable line wrapping
        textArea.setWrapStyleWord(true); // Wrap at word boundaries

        Font newFont = new Font("Serif", Font.BOLD, size);

        // Set the new font to the component
        textArea.setFont(newFont);
        textArea.setEditable(editable);
        // Put the JTextArea inside a JScrollPane for scrolling functionality
        JScrollPane scrollPane = new JScrollPane(textArea);
        // Set scrollbar policies
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        // Add the JScrollPane to the center of the frame
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        panel.revalidate(); // Re-calculate layout
        panel.repaint();    // Re-draw screen

        return textArea;
        
    }

    //refresh the window view,
    static void refresh() {
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        panel.revalidate(); // Re-calculate layout
        panel.repaint();    // Re-draw screen
    }
    //add a button to the main display
    public static void addButton(String text, Runnable action) {
        JButton button = new JButton(text);
        panel.add(button);
        panel.revalidate(); // Re-calculate layout
        panel.repaint();    // Re-draw screen
        //set the action the button does
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
    }
    //add a dropdown box to the main display
    public static JComboBox<String> addDropdown(String[] options, Runnable action) {
        JComboBox<String> dropdown = new JComboBox<>(options);
        panel.add(dropdown);
        panel.revalidate(); // Re-calculate layout
        panel.repaint();    // Re-draw screen
        //set action for when someone switches their selection
        dropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
        return dropdown;
    }
    public static void addPanel(int x, int y, String position) {
        //Set the existing panel object to a new panel
        //Tht way, the next time you call any of these add functions, it will automatically add it to the new panel rather than the old one
        panel = new JPanel();
        panel.setLayout(new GridLayout(x, y));
        //Add the new panel to the frame in the specified position
        if(position.equals("North")) {
            frame.add(panel, BorderLayout.NORTH);
        }
        else if(position.equals("South")) {
            frame.add(panel, BorderLayout.SOUTH);
        }
        else if(position.equals("East")) {
            frame.add(panel, BorderLayout.EAST);
        }
        else if(position.equals("West")) {
            frame.add(panel, BorderLayout.WEST);
        }
        else if (position.equals("Center")) {
            frame.add(panel, BorderLayout.CENTER);
        }
        else {
            //If the position specified is not valid, throw an error
            throw new IllegalArgumentException("Invalid position. Please specify 'North', 'South', 'East', 'West', or 'Center'.");
        }   
    }
}