package fi.arithmeticvisualizer;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * The main class of the Arithmetic Visualizer application. Loads the entry
 * scene of the application. For the controller class of the entry scene, see
 * EntrySceneController.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/EntryScene.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Arithmetic Visualizer");
            stage.setScene(scene);
        } catch (IOException ex) {
            exitOnFailureToLoadScene("entry");
        }
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Alerts the user that the program will terminate due to failure to load
     * the next scene.
     *
     * @param sceneName name of the scene which failed to load
     */
    public static void exitOnFailureToLoadScene(String sceneName) {
        Alert alert = new Alert(Alert.AlertType.ERROR,
                "Unable to load " +  sceneName + " scene. Exiting.");
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> Platform.exit());
    }

}
