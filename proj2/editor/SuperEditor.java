package editor;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;


/**
 * Created by qianzhenli on 6/3/17.
 */
public class SuperEditor extends Application {
    private static final int WINDOW_WIDTH = 100;
    private static final int WINDOW_HEIGHT = 100;

    private static final String fontName = "Verdana";
    private static final int STARTING_FONT_SIZE = 16;

    private final FastLinkedList textContent;
    private final Group root;
    private final Line cursor;

    public SuperEditor() {
        // Create a rectangle to surround the text that gets displayed.  Initialize it with a size
        // of 0, since there isn't any text yet.
        textContent = new FastLinkedList();
        root = new Group();
        cursor = new Line();
    }


    /** An EventHandler to handle keys that get pressed. */
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

                //deal with Arrow keys
                textContent.arrowKeyHandler(code);

            }
            textContent.coordinateUpdate(0,0,100);
            cursorPositionChange();
        }
    }

    /** An EventHandler to handle changing the position of the cursor. */
    private class CursorEventHandler implements EventHandler<ActionEvent> {
        private int currentColorIndex = 0;
        private int[] cursorWidth = {1, 0};

        private CursorEventHandler() {
            cursor.setStartX(0);
            cursor.setEndX(0);
            cursor.setStartY(0);
            cursor.setEndY(10);
            cursor.setStrokeWidth(1);
        }

        private void changeColor() {
            cursor.setStrokeWidth(cursorWidth[currentColorIndex]);
            currentColorIndex = (currentColorIndex + 1) % cursorWidth.length;
        }

        @Override
        public void handle(ActionEvent event) {changeColor();}
    }

    /** Makes the cursor blink periodically. */
    public void makeCursorBlink(CursorEventHandler x) {
        // Create a Timeline that will call the "handle" function of RectangleBlinkEventHandler
        // every 1 second.
        final Timeline timeline = new Timeline();
        // The rectangle should continue blinking forever.
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5), x);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    /** Changes the cursor's position. */
    public void cursorPositionChange() {
        double startX;
        double startY;
        double endY;

        if (textContent.size() == 0 || textContent.getText() == null) {
            startX = 0;
            startY = 0;
            endY = STARTING_FONT_SIZE;
        } else if (textContent.getText().getText().equals("\r")) {
            startX = textContent.getText().getX() + textContent.getText().getLayoutBounds().getWidth();
            startY = textContent.getText().getY();
            endY =textContent.getText().getY() + textContent.getText().getLayoutBounds().getHeight()/2;
        } else {
            startX = textContent.getText().getX() + textContent.getText().getLayoutBounds().getWidth();
            startY = textContent.getText().getY();
            endY =textContent.getText().getY() + textContent.getText().getLayoutBounds().getHeight();
        }
        cursor.setStartX(startX);
        cursor.setEndX(startX);
        cursor.setStartY(startY);
        cursor.setEndY(endY);
    }

    private class MouseClickEventHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent mouseEvent) {
            // Because we registered this EventHandler using setOnMouseClicked, it will only called
            // with mouse events of type MouseEvent.MOUSE_CLICKED.  A mouse clicked event is
            // generated anytime the mouse is pressed and released on the same JavaFX node.
            double mousePressedX = mouseEvent.getX();
            double mousePressedY = mouseEvent.getY();
            textContent.setCurrentNode(mousePressedX, mousePressedY, STARTING_FONT_SIZE);
            cursorPositionChange();
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

            //deal with the key input
            EventHandler<KeyEvent> keyEventHandler = new KeyEventHandler();

            scene.setOnKeyTyped(keyEventHandler);
            scene.setOnKeyPressed(keyEventHandler);

            //deal with the blinking cursor
            root.getChildren().add(cursor);
            CursorEventHandler cursorChange = new CursorEventHandler();
            makeCursorBlink(cursorChange);

            //deal with the mouse click
            scene.setOnMouseClicked(new MouseClickEventHandler());

            primaryStage.setTitle("SuperEditor");

            // This is boilerplate, necessary to setup the window where things are displayed.
            primaryStage.setScene(scene);
            primaryStage.show();
            textContent.print();
        }

        public static void main(String[] args) {
            launch(args);
        }
}
