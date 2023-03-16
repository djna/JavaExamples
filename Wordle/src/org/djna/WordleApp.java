package org.djna;


import java.util.Scanner;

public class WordleApp {

    private WordList myWordList;
    private Scanner myScanner;

    public static void main(String[] args) {
         WordleApp theApp = new WordleApp();
         theApp.playGame();
    }

    private WordleApp(){
        myWordList = new WordList();
        myScanner = new Scanner(System.in);
    }

    private void playGame(){
        System.out.printf("The Word List : %s%n", myWordList);
        boolean stillPlaying = true;
        while( stillPlaying ){
            System.out.printf("(P)lay or e(X)it %n");
            String action = myScanner.nextLine();
            if ( action.equalsIgnoreCase("x")){
                stillPlaying = false;
            } else if ( action.equalsIgnoreCase("p")) {
                String target = myWordList.getRandomWord();
                System.out.printf("Apologies, playing not implemented %n");
                System.out.printf("The target was :%s: %n", target);
            } else{
                System.out.printf("I don't know how to :%s: %n", action);
            }
        }
        System.out.printf("Goodbye!%n");

    }
}
