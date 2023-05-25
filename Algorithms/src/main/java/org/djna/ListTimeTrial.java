package org.djna;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTimeTrial {
    public static void main(String argv[]){

        System.out.printf("Time Trial starting %n");

        int start = 50_000;
        int stop = 1_000_000;
        int increment = 50_000;
        // int start = 500_000;
        // int stop = 10_000_000;
        // int increment = 500_000;
        for ( int iterations = start ; iterations < stop; iterations += increment ) {
            List<Integer> theList = new LinkedList<>();
            // List<Integer> theList = new ArrayList<>();
            testListPerformance(theList, iterations);
        }
        System.out.printf("Time Trial complete %n");
    }

    private static void testListPerformance(List<Integer> numbers, int howMany){

        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < howMany; i++) {
            numbers.add( i);
        }
        final long endTime = System.currentTimeMillis();

        System.out.printf("Total execution time for %d items is %dms %n: ",howMany , (endTime - startTime));

    }
}
