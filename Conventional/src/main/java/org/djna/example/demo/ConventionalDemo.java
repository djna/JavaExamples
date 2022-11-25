package org.djna.example.demo;

import org.djna.example.strutil.Conventional;

public class ConventionalDemo {

    public static void main( String args[]){
        String inputText = "Some human readable text";
        String camelText = Conventional.camelize(inputText);
        System.out.printf("Human text %s, camelized to %s%n", inputText, camelText);
    }
}
