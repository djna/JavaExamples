package org.djna.countdown.test;

import org.djna.countdown.CountdownSong;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerseTest {

    @Test
    public void checkVerse99() {
        CountdownSong song = new CountdownSong();
        String expectedSongText =
                "99 cans of Lilt on the wall, 99 cans of Lilt.\n" +
                        "Take one down and pass it around, 98 cans of Lilt on the wall.\n";
        assertEquals(expectedSongText, song.verse(99));
    }

    @Test
    public void checkVerse98() {
        CountdownSong song = new CountdownSong();
        String expectedSongText =
                "98 cans of Lilt on the wall, 98 cans of Lilt.\n" +
                        "Take one down and pass it around, 97 cans of Lilt on the wall.\n";
        assertEquals(expectedSongText, song.verse(98));
    }

    @Test
    public void checkVerse2() {
        CountdownSong song = new CountdownSong();
        String expectedSongText =
                "2 cans of Lilt on the wall, 2 cans of Lilt.\n" +
                        "Take one down and pass it around, 1 can of Lilt on the wall.\n";
        assertEquals(expectedSongText, song.verse(2));
    }

    @Test
    public void checkVerse1() {
        CountdownSong song = new CountdownSong();
        String expectedSongText =
                "1 can of Lilt on the wall, 1 can of Lilt.\n" +
                        "Take it down and pass it around, no more cans of Lilt on the wall.\n";
        assertEquals(expectedSongText, song.verse(1));
    }

    @Test
    public void checkVerse7() {
        CountdownSong song = new CountdownSong();
        String expectedSongText =
                "7 cans of Lilt on the wall, 7 cans of Lilt.\n" +
                        "Take one down and pass it around, a six-pack of Lilt on the wall.\n";
        assertEquals(expectedSongText, song.verse(7));
    }

    @Test
    public void checkVerse6() {
        CountdownSong song = new CountdownSong();
        String expectedSongText =
                "A six-pack of Lilt on the wall, a six-pack of Lilt.\n" +
                        "Take one down and pass it around, 5 cans of Lilt on the wall.\n";
        assertEquals(expectedSongText, song.verse(6));
    }

    @Test
    public void checkVerseZero() {
        CountdownSong song = new CountdownSong();
        String expectedSongText =
                "No more cans of Lilt on the wall, no more cans of Lilt.\n" +
                        "Go to the store and buy some more, 99 cans of Lilt on the wall.\n";
        assertEquals(expectedSongText, song.verse(0));
    }

}
