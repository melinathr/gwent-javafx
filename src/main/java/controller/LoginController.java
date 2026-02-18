package controller;

import model.Player;
import org.json.JSONArray;
import org.json.JSONObject;
public class LoginController {


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

    public String login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return "please fill all the fields.";
        } else if (!doesUserExist(username)) {
            return "username doesn't exist.";
        } else {
            JSONArray players = PlayerManager.readPlayers();
            for (int i = 0; i < players.length(); i++) {
                JSONObject player = players.getJSONObject(i);
                if (player.getString("username").equals(username)) {
                    if (player.getString("password").equals(password)) {
                        return "success";
                    } else {
                        return "password is incorrect";
                    }
                }
            }
        }
        return "error";
    }
}
