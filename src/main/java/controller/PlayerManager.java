package controller;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PlayerManager {
    private static final String FILE_PATH = "src/main/resources/players.json";  // Update the path

    public static JSONArray readPlayers() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            JSONObject jsonObject = new JSONObject(content);
            return jsonObject.getJSONArray("players");
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public static void writePlayers(JSONArray players) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("players", players);
            Files.write(Paths.get(FILE_PATH), jsonObject.toString(4).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
