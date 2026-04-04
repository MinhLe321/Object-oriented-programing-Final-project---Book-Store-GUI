import javax.swing.*;
import java.awt.*;
<<<<<<< HEAD
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
    private final int defaultHeight = 600; 
    private final int defaultWidth = 800;

    public Window(){ //Initalize default window
        setTitle("Book Store GUI");
        setSize(defaultWidth,defaultHeight);
        setResizable(false); //Prevent resizing the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            setSize(defaultWidth,defaultHeight);
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

    // public void addPanel(JPanel panel){//This method will connect JPanel with JFrame without hardcoding JPanel into JFrame, we can use this method in the main to link JFrame and Panel together
    //     setLayout(new GridBagLayout()); //This layout is scalable :D
    //     GridBagConstraints gbc = new GridBagConstraints();
    //     gbc.gridx = 0;
    //     gbc.gridy = 0;
    //     gbc.anchor = GridBagConstraints.CENTER; //Top-left, the other options are the same on the compass
    //     //Add spacing around components within its cell
    //     /* 
    //         20 pixels from top
    //         20 pixels from left
    //         0 pixels from bottom
    //         0 pixels from right
    //     */
    //     gbc.insets = new Insets(20,20,0,0);
    //     gbc.weightx = 0;                             // do not expand horizontally
    //     gbc.weighty = 0;                             // do not expand vertically
    //     gbc.fill = GridBagConstraints.NONE; //Keep preferred size
    //     add(panel, gbc);
    // }
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
        setBackground(Color.blue);
        setPreferredSize(new Dimension(300, 300));
    }
}

public class GUI{
    public static void main(String[] args){
        Window window = new Window();
        // Panel panel = new Panel(window); //Pass the window interface to Panel so panel knows which window to modify

        // window.addPanel(panel); //Connect panel with the Window
        // window.setVisible(true);
    }
}
