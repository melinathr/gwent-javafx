package view;

import controller.GameController;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Game;

public class GameMenu extends Application {
    private Pane pane;
    private Stage stage;
    private final GameController gameController = new GameController();
    private Game game;
//

    public GameMenu(Game game) {
        this.game = game;
    }

//
//    PlayerSet first = new PlayerSet(new Player("fhs", "", "", "", "", ""));
//    PlayerSet second = new PlayerSet( new Player("fsf", "f", "fsf", "f", "", ""));

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        pane = new Pane();
        setBackground();

//
//
//
//        first.getHand().add(new Card(UnitCard.DUN_BANNER_MEDIC));
//        first.getHand().add(new Card(UnitCard.SCHIRRU));
//        first.getHand().add(new Card(UnitCard.IMPENETRABLE_FOG));
//        first.getDiscardPile().add(new Card(UnitCard.SIEGE_ENGINEER));
//        first.getDiscardPile().add(new Card(UnitCard.SIEGE_ENGINEER));
//
//        second.getHand().add(new Card(UnitCard.SIEGE_ENGINEER));
//        second.getHand().add(new Card(UnitCard.DANDELION));
//        second.getHand().add(new Card(UnitCard.SIEGE_TOWER));
//        first.setRivalPlayerSet(second);
//        second.setRivalPlayerSet(first);


//        game = new Game(first, second);


        setStage();

        gameController.initializeBoard(stage, pane, game);
        setPassButton();

    }


    private void setPassButton() {
        Button button = new Button("Pass");
        button.setLayoutX(313 / 1.5);
        button.setLayoutY(868 / 1.5);
        pane.getChildren().add(button);
        button.setMaxWidth(80);
        button.setOnAction(e -> gameController.passRound());
    }

    private void setStage() {
        Scene scene = new Scene(pane);
        // To set the cursor for the entire scene

        Image cursorImage = new Image(GameMenu.class.getResource("/Image/cursor.png").toExternalForm());

        // Create an ImageCursor object with the resized image
        ImageCursor customCursor = new ImageCursor(cursorImage, cursorImage.getWidth() / 2, cursorImage.getHeight() / 2);

        scene.setCursor(customCursor);

// To set the cursor for a specific node

        stage.setScene(scene);
        scene.getStylesheets().add(GameMenu.class.getResource("/CSS/halo.css").toExternalForm());
        scene.getStylesheets().add(GameMenu.class.getResource("/CSS/1.css").toExternalForm());
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
        pane.requestFocus();
    }



    private void setBackground() {
        Image image = new Image(GameMenu.class.getResource("/Image/board.jpg").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(image.getWidth() / 1.5);
        imageView.setFitHeight(image.getHeight() / 1.5);
        pane.getChildren().add(imageView);
    }

}