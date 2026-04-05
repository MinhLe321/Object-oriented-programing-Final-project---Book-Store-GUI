import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//This interface is like a constrast, any class that want to implements this interface must provide an implementation of the methods declared here
//It's like making function signatures before using them
//By doing this, we can manage a wide range of functions in a very compact way which is more reusable and maintainable
interface WindowActions{
    void checkTitle(String title);
    void checkSize(int width, int height);
    void checkResizeable(boolean resizeable);
}

class Window extends JFrame implements WindowActions{
    //Default height and width
    private final int defaultWindowHeight = 600; 
    private final int defaultWindowWidth = 800;

    public Window(){ //Initalize default window
        setTitle("Book Store GUI");
        setSize(defaultWindowWidth,defaultWindowHeight);
        setResizable(false); //Prevent resizing the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    }

    public void checkTitle(String title){
        if (title != null){
            setTitle(title);
        }
        else{
            setTitle("Book Store GUI");
        }
    }
    public void checkSize(int width, int height){
        if (height > 0 && width > 0){
            setSize(width, height);
        }
        else{
            System.out.println("Invalid height or width: " + height + ", " + width);
            setSize(defaultWindowWidth,defaultWindowHeight);
        }
    }
    public void checkResizeable(boolean resizeable){
        if (resizeable == true){
            setResizable(true);
        }
        else{
            setResizable(false);
        }
    }

    //Paremeterized constructor: take a string for title, two ints for width and height respectively, and a boolean for resizeability (true or false)
    public Window(String title, int width, int height, boolean resizeable){
        checkTitle(title);
        checkSize(width, height);
        checkResizeable(resizeable);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addPanel(JPanel panel){//This method will connect JPanel with JFrame without hardcoding JPanel into JFrame, we can use this method in the main to link JFrame and Panel together
        add(panel);
        setLayout(null);
    }
}

class Panel extends JPanel{ //Jpanel is a GUI component that functions as a container to hold other components
    private WindowActions actions; //Image this as a remote control for the window we made, the panel will be the user using this remote control

    public Panel(WindowActions actions){
        //We will store the reference passed to this function and Panel will now pointed to the window we are using and control it
        /*
          Connect the code above with these two lines and you will understand:
          Window window = new Window();
          Panel panel = new Panel(window); 
        */
        this.actions = actions;
        setBackground(Color.white);
        setBounds(270, 300, 250, 50); //setBounds(x, y, width, height)
    }

    public void addTextField(){ //Because panel is already linked to window, so update the panel will update the window :D
        setLayout(new BorderLayout(10, 10));
        JButton button = new JButton("Search book");
        JTextField textField = new JTextField(20); // 20 columns

        add(button, BorderLayout.SOUTH);
        add(textField, BorderLayout.NORTH);
        revalidate();
        repaint();
    }
}

public class GUI{
    public static void main(String[] args){
        Window window = new Window();
        
        Panel panel1 = new Panel(window); //Pass the window interface to Panel so panel knows which window to modify
        window.addPanel(panel1); //Connect panel with the Window
        panel1.addTextField(); //Add a text field with a button

        Panel panel2 = new Panel(window);
        panel2.setBounds(0, 0, 800, 300);
        panel2.setBackground(Color.white);
        window.addPanel(panel2);
        
        window.setVisible(true);
    }
}
