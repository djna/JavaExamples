
/*
 * Example File Manipulation
 * 
 * Clears down the File Monitor data and creates a new set of input files.
 */

package org.djna.monitor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.*;

public class ResetMonitor {
    static private String dataDirectory = "Data";
    static private String inputDirectory = dataDirectory + "/input";
    static private String reportsDirectory = dataDirectory + "/reports";
    static private String badFilesDirectory = dataDirectory + "/badFiles";
    static private String processedFilesDirectory = dataDirectory + "/processedFiles";

    static private String[] allSubDirectories = {
            inputDirectory,
            reportsDirectory,
            badFilesDirectory,
            processedFilesDirectory
    };

    static private String dataSpec = dataDirectory + "/sampleInput.txt";

    public static void main(String argv[]) {
        System.out.printf("%nResetting file monitor in %s%n", dataDirectory);
        clearOldData();

        createInputData();
        System.out.printf("%nInput files created in %s%n", dataDirectory);
    }

    /*
     * Clears a specified directory, creating the directory if necessary.
     */
    private static void clearOneDirectory(String dirName) {
        ensureDirectoryExists(dirName);
        File dir = new java.io.File(dirName);
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();

            }
        }
        System.out.printf("%s cleared.%n", dir.getAbsolutePath());
    }

    /*
     * creates the specified directory if it does not already exist
     * 
     * not a complete implementation, really should check for
     * a non-directory with the specified name and also check permissions
     */
    private static void ensureDirectoryExists(String dirName) {
        File dir = new File(dirName);
        if (!dir.exists()) {
            System.out.printf("%s does not exist, creating ...",
                    dir.getAbsolutePath());
            dir.mkdirs();
        } else {
            System.out.printf("%s exists.%n", dir.getAbsolutePath());
        }
    }

    private static void clearOldData() {
        ensureDirectoryExists(dataDirectory);
        for (String dirName : allSubDirectories) {
            clearOneDirectory(dirName);
        }
    }

    private static void createInputData() {
        
        try (BufferedReader br = new BufferedReader(
                new FileReader(dataSpec))) {
            String line;
            while ((line = br.readLine()) != null) {
                processOneLine(line);
            }
        } catch (Exception e) {
            System.out.printf("Error %s processing %s%n",
                    e.getMessage(),
                    dataSpec);
        }
    }

    private static void processOneLine(final String line) {
        final String COMMA_DELIMITER = ",";
        String[] values = line.split(COMMA_DELIMITER);
        if (values.length != 2) {
            System.out.printf("Ignoring invalid line :%s:%n", line);
        } else {
            String branchName = values[0];
            try {
                int specifiedCount = Integer.parseInt(values[1].strip());
                createOneFile(branchName, specifiedCount);
            } catch (NumberFormatException nfe) {
                System.out.printf("Ignoring invalid count :%s:%n", values[1]);
            }
        }
    }

    // ensure filenames are unique by using a counter
    private static int fileCount = 0;

    private static void createOneFile(String branchName, int value) {

        String branchFileName = inputDirectory + "/" + branchName + "." + fileCount + ".log";
        fileCount++;
        try {
            FileWriter fstream = new FileWriter(branchFileName, true);
            BufferedWriter out = new BufferedWriter(fstream);

            out.write(branchName + ", " + value);
            out.newLine();

            out.close();
            System.out.printf("created %s%n", branchFileName);

        } catch (Exception e) {
            System.out.printf("Failed to write to %s, %s%n",
                    branchFileName, e.getMessage());
        }

    }
}
