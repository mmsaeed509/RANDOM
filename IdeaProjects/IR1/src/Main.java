/*
 * ----------------------------------
 *
 * 﫥  @author   : 00xWolf
 *   GitHub    : @mmsaeed509
 *   Developer : Mahmoud Mohamed
 *
 * ---------------------------------
 *
*/

import java.io.File;
import java.io.IOException;

public class Main {

    /* docs directory */
    private static final String DOCS_DIR = "docs/";

    /* get docs names */
    private static String[] getFileNames(String docsDir) {

        /* calculate the No. files/docs in the directory */
        File dir = new File(DOCS_DIR);
        File[] files = dir.listFiles();
        int numFiles = files.length;

        String[] fileNames = new String[numFiles];

        for (int i = 0; i < numFiles; i++) {

            fileNames[i] = docsDir + (i + 1) + ".txt";

        }

        return fileNames;
        
    }

    public static void main(String[] args) throws IOException {

        String[] fileNames = getFileNames(DOCS_DIR);

        InvertedIndex index = new InvertedIndex();
        index.buildIndex(fileNames);
        String query = "navigation";
        index.printPostingList(query);

    }

}
