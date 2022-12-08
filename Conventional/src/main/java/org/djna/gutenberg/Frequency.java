package org.djna.gutenberg;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Frequency {
    public static void main(String args[]) throws IOException {

        Path path = Paths.get("Gutenberg/Wonder.txt");

        String content = Files.readString(path, StandardCharsets.UTF_8);
        String [] words = content.split("[^a-zA-Z]+");

        Path stopPath = Paths.get("Gutenberg/stopwords");
        String stopText = Files.readString(stopPath, StandardCharsets.UTF_8);
        List<String> stopWords = Arrays.asList(stopText.split("[\n]+"));

        String [] lcWords = Arrays.stream(words)
            .filter(word -> word.length() > 2)
            .map( word -> word.toLowerCase() )
            .filter( word -> ! stopWords.contains(word))
            .toArray(String[]::new);

        Map<String, Long> result = Arrays.stream(lcWords).collect(
            Collectors.groupingBy(
                Function.identity(), Collectors.counting()
            )
        ).entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .limit(10)
        .collect(Collectors.toMap(
           Map.Entry::getKey, Map.Entry::getValue, 
           (e1, e2) -> e1, LinkedHashMap::new)
        );

       result.forEach(
           (k,v) -> System.out.printf("%s = %d%n", k, v)
       );
    }
}