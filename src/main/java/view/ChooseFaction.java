package view;

import controller.PreGameController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class ChooseFaction extends Application {
    private final PreGameController controller = new PreGameController();

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("vbox");
        vBox.setAlignment(Pos.CENTER);

        HBox imageContainer = new HBox();
        imageContainer.setSpacing(10);

        Image[] images = new Image[5];
        AtomicInteger selectedFaction = new AtomicInteger(-1);


        Button button = getButton();
        button.setOnAction(e -> {
            String result = controller.setFaction(selectedFaction);
            Alert alert;
            if (result.equals("ok")) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("faction set successfully");
                alert.showAndWait();
                try {
                    (new PreGameMenu()).start(Main.stage);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(result);
                alert.showAndWait();
            }
        });
        vBox.getChildren().addAll(imageContainer, button);


        addImage(images, selectedFaction, imageContainer);

        Scene scene = new Scene(vBox, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/CSS/1.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private Button getButton() {
        Button button = new Button();
        button.setText("Set Faction");
        button.getStyleClass().add("button");
        return button;
    }

    private void addImage(Image[] images, AtomicInteger selectedFaction, HBox hBox) {
        setImage(images);

        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(images[i]);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);

            StackPane pane = new StackPane(imageView);
            controller.setImageFaction(selectedFaction, pane, imageView, i);

            hBox.getChildren().add(pane);
        }
    }

    private void setImage(Image[] images) {
        images[0] = new Image(ChooseFaction.class.getResource("/Image/SkelligeBack.jpg").toExternalForm());
        images[1] = new Image(ChooseFaction.class.getResource("/Image/Scoia'taelBack.jpg").toExternalForm());
        images[2] = new Image(ChooseFaction.class.getResource("/Image/NorthernRealmsBack.jpg").toExternalForm());
        images[3] = new Image(ChooseFaction.class.getResource("/Image/NilfgaardianEmpireBack.jpg").toExternalForm());
        images[4] = new Image(ChooseFaction.class.getResource("/Image/MonstersBack.jpg").toExternalForm());
    }
}