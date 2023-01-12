
/*
 * Example File Manipulation
 * 
 * Clears down the File Monitor data and creates a new set of input files.
 */

package org.djna.monitor;

import java.io.File;
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

    public static void main( String argv[]) {
        System.out.printf("%nResetting file monitor in %s%n", dataDirectory);
        clearOldData();
        createInputData();
        System.out.printf("%nInput files created in %s%n", dataDirectory);
    }

    /*
     * Clears a specified directory, creating the directory if necessary.
     */
    private static void clearOneDirectory(String dirName){
         ensureDirectoryExists(dirName);
         File dir = new java.io.File(dirName);
        for (File file : dir.listFiles()){
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
    private static void ensureDirectoryExists(String dirName){
        File dir = new File(dirName);
        if ( ! dir.exists() ){
            System.out.printf("%s does not exist, creating ...",
                     dir.getAbsolutePath());
            dir.mkdirs();
        } else {
            System.out.printf("%s exists.%n", dir.getAbsolutePath());
        }
    }
    private static void clearOldData(){
        ensureDirectoryExists( dataDirectory );
        for ( String dirName : allSubDirectories ){
              clearOneDirectory(dirName);
        }
    }

    private static void createInputData(){
        
    }
}
