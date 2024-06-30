import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainJavaFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        KNNController knnController = new KNNController(null,null);
        KNNModel knnModel = new KNNModel(null);
        KNNView knnView = new KNNView(null,null);
        knnController.set(knnView,knnModel);
        knnModel.set(knnView);
        knnView.set(knnController,knnModel);
        knnView.create(primaryStage);
    }
}
