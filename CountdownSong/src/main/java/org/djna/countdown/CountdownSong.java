package org.djna.countdown;

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

    public String verse(int number) {
        switch (number) {
            case 0:
                return (
                        "No more cans of Lilt on the wall, " +
                                "no more cans of Lilt.\n" +
                                "Go to the store and buy some more, " +
                                "99 cans of Lilt on the wall.\n"
                );
            case 1:
                return (
                        "1 can of Lilt on the wall, " +
                                "1 can of Lilt.\n" +
                                "Take it down and pass it around, " +
                                "no more cans of Lilt on the wall.\n"
                );
            case 2:
                return (
                        "2 cans of Lilt on the wall, " +
                                "2 cans of Lilt.\n" +
                                "Take one down and pass it around, " +
                                "1 can of Lilt on the wall.\n"
                );
            default:
                String howManyCans = "" + number;
                String howManyCansLeft = "" + (number - 1);
                return "%s cans of Lilt on the wall, %s cans of Lilt.\n".formatted(
                        howManyCans, howManyCans) +
                        "Take one down and pass it around, %s cans of Lilt on the wall.\n".formatted(
                                howManyCansLeft);
        }
    }
}
