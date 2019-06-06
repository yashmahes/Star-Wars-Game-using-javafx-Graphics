package assignment1_000742792;

import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Pair;

/**
 * Use this template to create drawings in FX. Change the name of the class and
 * put your own name as author below. Change the size of the canvas and the
 * window title where marked and add your drawing code where marked.
 *
 * @author 000742792
 */
public class StarsAndConstellation extends Application {

    /**
     * Start method (use this instead of main).
     *
     * @param stage The FX stage to draw on
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(1500, 700); // Set canvas Size in Pixels
        stage.setTitle("Stars"); // Set window title
        root.getChildren().add(canvas);
        stage.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // YOUR CODE STARTS HERE 
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double width = canvas.getWidth();
        double height = canvas.getHeight();
        gc.setFill(Color.WHITE);
        for (int i = 0; i < 50; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);

            gc.fillRect(x, y, 5, 5);
        }

        gc.setFill(Color.YELLOW);
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> yValues = new ArrayList<>();

        while (true) {

            TextInputDialog dialog = new TextInputDialog("Tran");

            dialog.setTitle("Position of Star");
            dialog.setHeaderText("Enter X value:");
            dialog.setContentText("X:");

            Optional<String> resultX = dialog.showAndWait();
            if (resultX.isPresent()) {
                System.out.println("X: " + resultX.get());
            } else {
                break;
            }

            TextInputDialog dialog2 = new TextInputDialog("Tran");
            dialog2.setTitle("Position of Star");
            dialog2.setHeaderText("Enter Y value:");
            dialog2.setContentText("Y:");

            Optional<String> resultY = dialog2.showAndWait();
            if (resultY.isPresent()) {
                System.out.println("Y: " + resultY.get());
            } else {
                break;
            }

            try {
                double x = Double.parseDouble(resultX.get());
                double y = Double.parseDouble(resultY.get());
                if (x > 0 && x < width && y > 0 && y < height) {
                    gc.fillRect(x, y, 5, 5);
                    if (xValues.isEmpty() == false && yValues.isEmpty() == false) {
                        gc.setStroke(Color.RED);
                        gc.setLineWidth(1.0);
                        gc.strokeLine(xValues.get(xValues.size() - 1), yValues.get(yValues.size() - 1), x, y);
                    }

                    xValues.add(x);
                    yValues.add(y);

                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Invalid Values");
                    alert.setHeaderText("Values are out of the screen. ");
                    alert.setContentText("You cannot enter any x or y values that would appear off the screen. ");

                    alert.showAndWait();
                }

            } catch (Exception e) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Value");
                alert.setHeaderText("Please enter only Decimal values");
                alert.setContentText("Please enter only Decimal values");

                alert.showAndWait();
            }

        }

        if (xValues.isEmpty() == false && yValues.isEmpty() == false) {
            gc.setStroke(Color.RED);
            gc.setLineWidth(1.0);
            gc.strokeLine(xValues.get(xValues.size() - 1), yValues.get(yValues.size() - 1), xValues.get(0), yValues.get(0));
        }

        TextInputDialog dialog = new TextInputDialog("Tran");
        dialog.setTitle("Title");
        dialog.setHeaderText("Enter Title of constellation:");
        dialog.setContentText("constellation:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("constellation: " + result.get());
            gc.setFont(new Font(53.0));
            gc.setStroke(Color.BLANCHEDALMOND);
            gc.fillText(result.get() + ", Program by 000742792", width / 3, width / 16);
        }

        // YOUR CODE STOPS HERE
        stage.show();
    }

    /**
     * The actual main method that launches the app.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
