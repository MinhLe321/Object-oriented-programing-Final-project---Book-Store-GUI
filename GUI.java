import javax.swing.*;
import java.awt.*;

class Window extends JFrame{
    public Window(){
        setTitle("Book Store GUI");
        setSize(800,600);
        setResizable(false); //Prevent resizing the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        Window window = new Window();
    }
}
