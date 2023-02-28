package org.djna.countdown;

import java.util.Locale;

public class CountdownSong {
    private int upper;

    public CountdownSong() {
        this.upper = 99;
    }

    public static void main(String args[]) {
        CountdownSong mySong = new CountdownSong();
        System.out.println(mySong.song());
    }

    public String song() {
        String songText = "Singing ... \n";
        for (int i = this.upper; i >= 0; i--) {
            songText += verse(i);
        }
        return songText;
    }

    private static String quantityText(int howMany) {
        switch (howMany) {
            case 0:
                return "no more cans";
            case 1:
                return "1 can";
            case 6:
                return "a six-pack";
            default:
                return howMany + " cans";
        }
    }

    private static String action(int howMany) {
        switch (howMany) {
            case 0:
                return "Go to the store and buy some more";
            case 1:
                return "Take it down and pass it around";
            default:
                return "Take one down and pass it around";
        }

    }

    private static String capitalize(String str) {
        if(str== null || str.isEmpty()) {
            return str;
        }

        String remainder = str.length() == 1 ? "" : str.substring(1);

        return str.substring(0, 1).toUpperCase() + remainder;
    }

    private static int nextQuantity( int currentQuantity ){
        return currentQuantity <= 0 ? 99 : (currentQuantity - 1);
    }

    public String verse(int number) {


        return "%s of Lilt on the wall, %s of Lilt.\n".formatted(
                capitalize(quantityText(number)), quantityText(number)) +
                "%s, %s of Lilt on the wall.\n".formatted(
                        action(number), quantityText(nextQuantity(number)));

    }
}
