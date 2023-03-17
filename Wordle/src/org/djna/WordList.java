package org.djna;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordList {
    private List<String> myWords;


    public WordList() {
        Set<String> wordsFromFile = new HashSet<>();

        String datafile = "Wonder.txt";
        try (
            BufferedReader br = new BufferedReader(
                new FileReader(datafile)
                )
        ) {
            String line;
            Pattern wordPattern = Pattern.compile("\\b\\w+\\b");
            while ((line = br.readLine()) != null) {
                Matcher wordMatcher = wordPattern.matcher(line);
                while( wordMatcher.find()) {
                    wordsFromFile.add(wordMatcher.group().toLowerCase());
                }
            }
            myWords = wordsFromFile.stream().sorted().collect(Collectors.toList());
        } catch (Exception e) {
            System.out.printf("Error %s processing %s%n",
                    e.getMessage(),
                    datafile);
        }
    }

    @Override
    public String toString() {
        return "WordList{" +
                "myWords[" + myWords.size() +
                "]=" + myWords +
                '}';
    }

    public String getRandomWord(){
        Random rand = new Random();
        return myWords.get(rand.nextInt(myWords.size()));
    }
}
