package editor;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;


/**
 * Created by qianzhenli on 6/3/17.
 */
public class SuperEditor extends Application {
    private static final int WINDOW_WIDTH = 100;
    private static final int WINDOW_HEIGHT = 100;

    private static final String fontName = "Verdana";
    private static final int STARTING_FONT_SIZE = 10;

    private static FastLinkedList textContent = new FastLinkedList();
    private Group root = new Group();


    private class KeyEventHandler implements EventHandler<KeyEvent> {


        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
                String characterTyped = keyEvent.getCharacter();
                if (characterTyped.length() > 0 && characterTyped.charAt(0) != 8) {
                    textContent.addChar(characterTyped.charAt(0));
                    textContent.getText().setFont(Font.font(fontName, STARTING_FONT_SIZE));
                    textContent.getText().setTextOrigin(VPos.TOP); 
                    root.getChildren().add(textContent.getText());
                } else if (characterTyped.length() == 0) {
                    root.getChildren().remove(textContent.getText());
                    textContent.deleteChar();
                }
                keyEvent.consume();
            } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
                // events have a code that we can check (KEY_TYPED events don't have an associated
                // KeyCode).
                KeyCode code = keyEvent.getCode();
                if (code == KeyCode.UP) {
                    //fontSize += 5;
                    //displayText.setFont(Font.font(fontName, fontSize));
                    //centerText();
                } else if (code == KeyCode.DOWN) {
                    //fontSize = Math.max(0, fontSize - 5);
                    //displayText.setFont(Font.font(fontName, fontSize));
                    //centerText();
                }
            }
            textContent.coordinateUpdate(0,0,100);
        }

    }



        @Override
        public void start(Stage primaryStage) {
            // Create a Node that will be the parent of all things displayed on the screen.
            // The Scene represents the window: its height and width will be the height and width
            // of the window displayed.
            Parameters test=getParameters();
            String name=test.getRaw().get(0);
            String inputFilename = name;


            try {
                File inputFile = new File(inputFilename);
                FileReader reader = new FileReader(inputFile);
                BufferedReader bufferedReader = new BufferedReader(reader);
                int intRead = -1;
                // Keep reading from the file input read() returns -1, which means the end of the file
                // was reached.
                while ((intRead = bufferedReader.read()) != -1) {
                    // The integer read can be cast to a char, because we're assuming ASCII.
                    char charRead = (char) intRead;
                    textContent.addChar(charRead);
                    textContent.getText().setFont(Font.font(fontName, STARTING_FONT_SIZE));
                    textContent.getText().setTextOrigin(VPos.TOP);
                    root.getChildren().add(textContent.getText());
                }

            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("Open a new file" + inputFilename);
                try {
                    PrintWriter writer = new PrintWriter(inputFilename, "UTF-8");
                } catch (IOException e) {
                    System.out.println("cannot open a new file");
                }
            } catch (IOException ioException) {
                System.out.println("Error when copying; exception was: " + ioException);
            }

            textContent.coordinateUpdate(0,0,100);

            Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);



            EventHandler<KeyEvent> keyEventHandler = new KeyEventHandler();

            scene.setOnKeyTyped(keyEventHandler);
            scene.setOnKeyPressed(keyEventHandler);


            primaryStage.setTitle("SuperEditor");

            // This is boilerplate, necessary to setup the window where things are displayed.
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
}
