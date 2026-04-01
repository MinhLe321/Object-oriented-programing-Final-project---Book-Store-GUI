//Command that link library together: javac --module-path "javafx-sdk-26"/lib --add-modules javafx.controls GUI.java
//Command run: java --module-path "javafx-sdk-26"/lib --add-modules javafx.controls GUI.java
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application{ //All core functionalies are in Application class and we need to inherent it to use
    public static void main(String[] args){
        launch(args); //This method is inside Application class: All this do is going to Application class and set up JavaFx for us
    }

    @Override
    public void start(Stage primaryStage) throws Exception{ //After running the method launch and set up everything with Application class, we will run this start function and everything we build will be inside it
        /*Short terminology: for javafx, the entire window is called a stage, including the close, maximize and minize button, etc (basically all the basic buttons that a normal window will need)
        Everything inside the stage (window) is called scene
        
        +)primageStage is the main window*/
        primaryStage.setTitle("Book Store Manager");

        //Creating button
        Button button = new Button();
        button.setText("Add book");

        //Before we display everything into the scene, we need a layout (in simple term, it's how we want everything to be arranged on the scene)
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        //We need a scene
        Scene scene = new Scene(layout, 300, 250); //This method can be used to set the size of the window
        primaryStage.setScene(scene);
        primaryStage.show(); //Display it to the user

    }
}
