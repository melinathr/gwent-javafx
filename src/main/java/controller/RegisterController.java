package controller;
import org.json.JSONArray;
import org.json.JSONObject;
import model.Player;

import java.security.SecureRandom;

public class RegisterController {

    private boolean doesUserExist(String username) {
        JSONArray players = PlayerManager.readPlayers();
        for (int i = 0; i < players.length(); i++) {
            JSONObject player = players.getJSONObject(i);
            if (player.getString("username").equals(username)) {
                return true;
            }
        }
        return false;
    }


    public String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "@$!%*?&";
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 6; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }

        for (int i = 0; i < 3; i++) {
            password.append(digits.charAt(random.nextInt(digits.length())));
        }

        for (int i = 0; i < 3; i++) {
            password.append(specialChars.charAt(random.nextInt(specialChars.length())));
        }

        return password.toString();
    }

    public boolean isUsernameValid(String username) {
        String usernamePattern = "^[a-zA-Z0-9-]{3,}$";
        return username.matches(usernamePattern);
    }

    public boolean isEmailValid(String email){
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailPattern);
    }

    public boolean isNicknameValid(String nickname){
        String nicknamePattern = "^[a-zA-Z0-9.]{3,}$";
        return nickname.matches(nicknamePattern);
    }

    public boolean isPasswordValid(String password){
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordPattern);
    }

    public String register(String username, String password, String passConfirm, String email, String nickname, String securityQuestion, String securityAnswer) {

        String usernamePattern = "^[a-zA-Z0-9-]{3,}$";
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String nicknamePattern = "^[a-zA-Z0-9.]{3,}$";

        if (username.isEmpty() || email.isEmpty() || nickname.isEmpty() || securityAnswer.isEmpty()) {
            return "please fill the fields.";
        } else if (password.isEmpty() || passConfirm.isEmpty()) {
            return "generateRandomPassword";
        } else if (!password.equals(passConfirm)) {
            return "passwords don't match.";
        } else if (!username.matches(usernamePattern)) {
            return "username at least 3 characters long and can only contain letters, numbers and dots.";
        } else if (!password.matches(passwordPattern)) {
            return "password must be at least 8 characters long and contain at least one letter, one special character and one digit.";
        } else if (!email.matches(emailPattern)) {
            return "email is not valid.";
        } else if (!nickname.matches(nicknamePattern)) {
            return "nickname at least 3 characters long and can only contain letters, numbers and dots.";
        } else if (doesUserExist(username)) {
            return "username already exists.";
        } else {
            JSONArray players = PlayerManager.readPlayers();
            JSONObject newPlayer = new JSONObject();
            newPlayer.put("username", username);
            newPlayer.put("password", password);
            newPlayer.put("nickname", nickname);
            newPlayer.put("email", email);
            newPlayer.put("securityQuestion", securityQuestion);
            newPlayer.put("securityAnswer", securityAnswer);
            players.put(newPlayer);
            PlayerManager.writePlayers(players);
          return "successful";
        }
    }
}
