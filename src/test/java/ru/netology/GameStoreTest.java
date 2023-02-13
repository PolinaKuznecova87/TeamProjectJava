package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStoreTest {

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldThrowExceptionIfAddCopyOfGame() {

        GameStore store = new GameStore();
        store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertThrows(RuntimeException.class, () -> store.publishGame("Нетология Баттл Онлайн", "Аркады"));
    }

    @Test
    public void shouldAddGameWithSameGenreAndDiffName() {
        GameStore store = new GameStore();
        store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология Баттл 2 Онлайн", "Аркады");

        assertTrue(store.containsGame(game1));
    }

    @Test
    public void shouldAddGameWithSameNameAndDiffGenre() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "MMO");

        assertTrue(store.containsGame(game1));
    }

    @Test
    public void shouldNotFoundGameWithoutErrors() {
        GameStore store = new GameStore();

        assertFalse(store.containsGame(null));
    }

    @Test
    public void shouldAddPlaytimeForExistPlayer() {
        GameStore store = new GameStore();
        store.playedTime.put("Vasya", 11);
        store.addPlayTime("Vasya", 1);

        assertEquals(12, store.playedTime.get("Vasya"));
    }

    @Test
    public void shouldAddPlaytimeForNewPlayer() {
        GameStore store = new GameStore();
        store.addPlayTime("Petya", 1);

        assertEquals(1, store.playedTime.get("Petya"));
    }

    @Test
    public void shouldGetMostActivePlayer() {
        GameStore store = new GameStore();
        store.playedTime.put("Vasya", 11);
        store.playedTime.put("Petya", 10);

        Assertions.assertEquals("Vasya", store.getMostPlayer());
    }

    @Test
    public void shouldReturnBullIfPlayersNotExist() {
        GameStore store = new GameStore();

        assertNull(store.getMostPlayer());
    }

    @Test
    public void shouldGetSumOfPlayedTime() {
        GameStore store = new GameStore();
        store.playedTime.put("Vasya", 11);
        store.playedTime.put("Petya", 10);

        Assertions.assertEquals(21, store.getSumPlayedTime());
    }

    @Test
    public void shouldGetSumBeZeroIfPlayersNotExist() {
        GameStore store = new GameStore();

        assertEquals(0, store.getSumPlayedTime());
    }

    @Test
    public void shouldGetMostActivePlayerIfOnlyOnePlayerPlayedOneHour() {
        GameStore store = new GameStore();
        store.playedTime.put("Vasya", 1);

        Assertions.assertEquals("Vasya", store.getMostPlayer());
    }
}