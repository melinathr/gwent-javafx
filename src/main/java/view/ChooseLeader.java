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
import model.Player;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class ChooseLeader extends Application {
    private final PreGameController controller = new PreGameController();

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("vbox");
        vBox.setAlignment(Pos.CENTER);

        HBox imageContainer = new HBox();
        imageContainer.setSpacing(10);

        Image[] images = new Image[5];
        AtomicInteger selectedLeader = new AtomicInteger(-1);
        addImage(images, selectedLeader, imageContainer);

        Button button = getButton();
        btnOnAction(button, selectedLeader);
        vBox.getChildren().addAll(imageContainer, button);

        Scene scene = new Scene(vBox, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/CSS/1.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void btnOnAction(Button button, AtomicInteger selectedLeader) {
        button.setOnAction(e -> {
            String result = controller.setLeader(selectedLeader);
            Alert alert;
            if (result.equals("ok")) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("leader set successfully");
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
            }

        });
    }

    private Button getButton() {
        Button button = new Button();
        button.setText("Set Leader");
        button.getStyleClass().add("button");
        return button;
    }

    private void addImage(Image[] images, AtomicInteger selectedLeader, HBox hBox) {
        setImage(images);

        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(images[i]);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);

            StackPane pane = new StackPane(imageView);
            controller.setImageFaction(selectedLeader, pane, imageView, i);

            hBox.getChildren().add(pane);
        }
    }

    private void setImage(Image[] images) {

        switch (Player.getTurn().getPlayerSet().getFaction()) {
            case "Monster" ->
                    addNames(images, "BringerOfDeath", "KingOfTheWildHunt", "DestroyerOfWorlds", "CommanderOfTheRedRiders", "TheTreacherous");
            case "NorthernRealms" ->
                    addNames(images, "TheSiegemaster", "TheSteel-Forged", "KingOfTemeria", "LordCommanderOfTheNorth", "SonOfMedell");
            case "Nilfgaard" ->
                    addNames(images, "TheWhiteFlame", "HisImperialMajesty", "EmperorOfNilfgaard", "TheRelentless", "InvaderOfTheNorth");
            case "Scoia'tael" ->
                    addNames(images, "TheBeautiful", "QueenOfDolBlathanna", "PurebloodElf", "DaisyOfTheValle", "HopeOfTheAenSeidhe");
            case "Skellige" ->
                    addNames(images, "CrachAnCraite", "KingBran", "KingBran", "KingBran","KingBran");
        }
    }

    private void addNames(Image[] images, String s1, String s2, String s3, String s4, String s5) {
        images[0] = new Image(Objects.requireNonNull(ChooseLeader.class.getResource("/Image/leader/" + s1 + ".png")).toExternalForm());
        images[1] = new Image(Objects.requireNonNull(ChooseLeader.class.getResource("/Image/leader/" + s2 + ".png")).toExternalForm());
        images[2] = new Image(Objects.requireNonNull(ChooseLeader.class.getResource("/Image/leader/" + s3 + ".png")).toExternalForm());
        images[3] = new Image(Objects.requireNonNull(ChooseLeader.class.getResource("/Image/leader/" + s4 + ".png")).toExternalForm());
        images[4] = new Image(Objects.requireNonNull(ChooseLeader.class.getResource("/Image/leader/" + s5 + ".png")).toExternalForm());
    }
}