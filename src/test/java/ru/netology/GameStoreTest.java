package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameStoreTest {
    GameStore store = new GameStore();
    List<String> games = new ArrayList<String>();

    Map<String, Integer> playedTime = new HashMap<>();


    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Танки", "Трек");

        assertTrue(store.containsGame(game));
        assertTrue(store.containsGame(game1));
    }

    @Test
    public void shouldAddTheSameNameGame() {
        GameStore store = new GameStore();
        store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология Баттл  Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Баттл  Онлайн", "Аркады");
        assertTrue(store.containsGame(game1));
        assertTrue(store.containsGame(game2));

    }

    @Test
    public void shouldNotFoundGameWithoutErrors() {
        GameStore store = new GameStore();

        assertFalse(store.containsGame(null));
    }

    @Test
    public void shouldAddPlayersTime() {

        GameStore store = new GameStore();
        store.playedTime.put("Pety", 5);
        store.playedTime.put("Oly", 1);
        store.addPlayTime("Pety", 5);
        store.addPlayTime("Oly", 1);


        assertEquals(10, store.playedTime.get("Pety"));
        assertEquals(2, store.playedTime.get("Oly"));


    }

    @Test
    public void shouldGetMostPlayer() {
        GameStore store = new GameStore();
        store.playedTime.put("Pety", 10);
        store.playedTime.put("Oly", 2);

        assertEquals("Pety", store.getMostPlayer());

    }

    @Test
    public void shouldGetMostPlayesNoPlayers() {
        GameStore store = new GameStore();


        assertEquals(null, store.getMostPlayer());

    }

    @Test
    public void shouldGetSumPlayedTime() {
        GameStore store = new GameStore();
        store.playedTime.put("Pety", 10);
        store.playedTime.put("Oly", 2);

        assertEquals(12, store.getSumPlayedTime());
    }

    @Test
    public void shouldGetSumPlayedTimeZiro() {
        GameStore store = new GameStore();
        store.playedTime.put("Pety", 0);
        store.playedTime.put("Oly", 2);

        assertEquals(2, store.getSumPlayedTime());
    }


}

