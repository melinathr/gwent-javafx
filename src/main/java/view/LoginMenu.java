package view;

import controller.LoginController;
import controller.PlayerManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Player;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;
public class LoginMenu extends Application {
    public TextField username;
    public PasswordField password;
    LoginController controller = new LoginController();

    @Override
    public void start(Stage stage) throws Exception {

        try {
            URL url = getClass().getResource("/FXML/Login.fxml");
            if (url == null) {
                throw new IOException("Cannot find FXML file at /FXML/firstMenu.fxml");
            }

            BorderPane borderPane = FXMLLoader.load(url);
            stage.setScene(new Scene(borderPane));
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void login(MouseEvent mouseEvent) {
        String result = controller.login(username.getText(), password.getText());
        if (result.equals("success")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Login Successful");
            alert.showAndWait();

            JSONArray players = PlayerManager.readPlayers();
            Player loggedInPlayer = null;
            for (int i = 0; i < players.length(); i++) {
                JSONObject playerJson = players.getJSONObject(i);
                if (playerJson.getString("username").equals(username.getText())) {
                    loggedInPlayer = new Player(
                            playerJson.getString("username"),
                            playerJson.getString("password"),
                            playerJson.getString("nickname"),
                            playerJson.getString("email"),
                            playerJson.getString("securityQuestion"),
                            playerJson.getString("securityAnswer")
                    );
                    break;
                }
            }
            if (loggedInPlayer != null) {
                Player.setLoggedInUser(loggedInPlayer);
                try {
                    (new MainMenu()).start(Main.stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login Failed");
            alert.setContentText(result);
            alert.showAndWait();
        }

    }

    public void back(MouseEvent mouseEvent) throws Exception {
        (new StartMenu()).start(Main.stage);
    }

    public boolean isPasswordValid(String password) {
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        if (!password.matches(passwordPattern)) {
            return false;
        }
        return true;
    }

    public void forgotPassword(MouseEvent mouseEvent) {

        if (username.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please enter your username");
            alert.showAndWait();
        }
        Player player = Player.getUserByUsername(username.getText());
        if (player == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("User not found");
            alert.showAndWait();
        }
        String securityQuestion = player.getSecurityQuestion();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Forgot Password");
        dialog.setHeaderText(securityQuestion);
        dialog.setContentText("Please enter your answer:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String answer = result.get();
            if (answer.equals(player.getSecurityAnswer())) {

                dialog = new TextInputDialog();
                dialog.setTitle("Reset Password");
                dialog.setHeaderText("Enter your new password:");
                dialog.setContentText("New password:");

                Optional<String> newPassword = dialog.showAndWait();
                if (newPassword.isPresent()) {
                    if (isPasswordValid(newPassword.get())) {
                        player.setPassword(newPassword.get());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Password reset successfully");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Password must contain at least 8 characters, one letter, one number and one special character");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Incorrect answer");
                    alert.showAndWait();
                }
            }
        }
    }
}