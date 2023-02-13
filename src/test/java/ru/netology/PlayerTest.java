package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PlayerTest {

    @Test
    public void shouldInstallGameIfNotInstalled() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        player.installGame(game1);

        Map<Game, Integer> expected = new HashMap<>() {{
            put(game1, 0);
        }};
        Map actual = player.playedTime;

        assertEquals(expected, actual);
    }

    @Test
    public void shouldInstallSecondGameIfNotInstalled() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Бой Онлайн", "Шуттер");

        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);

        Map<Game, Integer> expected = new HashMap<>() {{
            put(game1, 0);
            put(game2, 0);
        }};
        Map actual = player.playedTime;

        assertEquals(expected, actual);
    }

    @Test
    public void shouldPlayOnce() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        player.installGame(game1);

        int expected = 1;
        int actual = player.play(game1, 1);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldPlayTwice() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Бой Онлайн", "Шуттер");

        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);

        player.play(game1, 30);
        player.play(game2, 1);
        player.play(game1, 20);

        int expected = 50;
        int actual = player.playedTime.get(game1);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldPlayIfGameNotInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game, 3);
        });
    }

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        player.installGame(game);

        player.play(game, 10);

        int expected = 10;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGameTwicePlay() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Баттл Онлайн2", "Аркады");
        Game game3 = store.publishGame("Нетология Бой Онлайн", "Шуттер");

        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 2);
        player.play(game3, 100);
        player.play(game2, 1);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfNoGame() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Баттл Онлайн2", "Аркады");
        Game game3 = store.publishGame("Нетология Бой Онлайн", "Шуттер");

        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game3, 10);

        int expected = 0;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreIfOne() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 7);

        Game expected = game;
        Game actual = player.mostPlayerByGenre(game.getGenre());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreIfTwo() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Баттл Онлайн2", "Аркады");
        Game game3 = store.publishGame("Нетология Бой Онлайн", "Шуттер");

        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 2);
        player.play(game3, 100);
        player.play(game2, 10);

        Game expected = game2;
        Game actual = player.mostPlayerByGenre(game1.getGenre());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreIfNotInstalled() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Баттл Онлайн2", "Аркады");
        Game game3 = store.publishGame("Нетология Бой Онлайн", "Шуттер");

        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);

        player.play(game1, 2);
        player.play(game2, 10);

        Game expected = null;
        Game actual = player.mostPlayerByGenre(game3.getGenre());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreIfNoPlay() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Баттл Онлайн2", "Аркады");
        Game game3 = store.publishGame("Нетология Бой Онлайн", "Шуттер");

        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 2);
        player.play(game2, 10);

        Game expected = null;
        Game actual = player.mostPlayerByGenre(game3.getGenre());

        assertEquals(expected, actual);
    }
}