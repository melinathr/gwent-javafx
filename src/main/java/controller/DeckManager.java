package controller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DeckManager {
    private static final String FILE_PATH = "src/main/resources/decks.json";

    private static JSONObject readDecks() {
        try (InputStream is = Files.newInputStream(Paths.get(FILE_PATH))) {
            if (is == null) {
                return new JSONObject();
            }
            String content = new String(is.readAllBytes());
            return new JSONObject(content);
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    private static void writeDecks(JSONObject decks) {
        try {
            Files.write(Paths.get(FILE_PATH), decks.toString(4).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadDeck(String deckName) {
        JSONObject decks = readDecks();
        if (decks.has(deckName)) {
            JSONArray jsonArray = decks.getJSONArray(deckName);
            List<String> deck = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                deck.add(jsonArray.getString(i));
            }
            return deck;
        }
        return new ArrayList<>();
    }

    public static void saveDeck(String deckName, List<String> cards) {
        JSONObject decks = readDecks();
        JSONArray jsonArray = new JSONArray(cards);
        decks.put(deckName, jsonArray);
        writeDecks(decks);
    }

    public static List<String> loadLatestDeck() {
        return loadDeck("latest deck");
    }
}
