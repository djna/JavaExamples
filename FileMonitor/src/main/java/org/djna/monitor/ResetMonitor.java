
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
    
    public static void main( String argv[]) {
        System.out.printf("%nResetting file monitor in %s%n", dataDirectory);
        clearOldData();
        createInputData();
        System.out.printf("%nInput files created in %s%n", dataDirectory);
    }

    private static void clearOneDirectory(File dir){

    }

    /*
    rm -f $DATADIR/input/*.log
    rm -f $DATADIR/reports/*.txt
    rm -f $DATADIR/badFiles/*.log
    rm -f $DATADIR/processedFiles/*.log
     */
    private static void ensureDirectoryExists(String dirName){
        File dir = new File(dirName);
        if ( !dir.exists() ){
            System.out.printf("%s does not exist, creating ...",
                     dir.getAbsolutePath());
            dir.mkdirs();
        }
    }
    private static void clearOldData(){
        ensureDirectoryExists( dataDirectory );
        ensureDirectoryExists( inputDirectory );
        ensureDirectoryExists( reportsDirectory );
        ensureDirectoryExists( badFilesDirectory );
        ensureDirectoryExists( processedFilesDirectory );
       
        
        // for (File file : new java.io.File("C:\\DeleteMeFolder").listFiles())

    }

    private static void createInputData(){
        
    }
}
