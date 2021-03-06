package Hub;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @FXML
    public Stage main_stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        main_stage = primaryStage;


        // Main hub scene
        FXMLLoader main_hub_loader = new FXMLLoader(getClass().getResource("FXML/main_hub.fxml"));
        Parent main_hub_pane = main_hub_loader.load();
        Scene main_hub_scene = new Scene(main_hub_pane, 500,500);

        main_stage.setTitle("Hub");
        main_stage.setScene(main_hub_scene);
        main_stage.setFullScreen(true);
        main_stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
