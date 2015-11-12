/**
 * Provides a IO utility functions  
 *
 * @author Stephen Ince
 */
package digitalreasoning;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.File;
import java.io.FilenameFilter;


public class IOUtil {

    /**
     * Helper routine to get directory listing with files that
     * match a given suffix
     * 
     * @param directory
     *            the directory name
     *            
     * @param suffix 
     *            the file suffix
     *
     * @return a File array
     */
    
    public static File[] list(final String directory, final String suffix) {

        final File dir = new File(directory);
        final File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(suffix);
            }
        });
        return files;
    }

    /**
     * Helper routine to get a directory listing of files
     * 
     * @param directory
     *            the directory name
     *            
     *
     * @return a File array
     */
    
    public static File[] list(final String directory) {

        final File dir = new File(directory);
        final File[] files = dir.listFiles();
        return files;
    }
    
    /**
     * Helper routine to get the file contents
     * 
     * @param f
     *            the file name
     * @return a String containing the file contents
     */
    public static String getFileContents(String f) throws IOException {
        final StringBuilder sb = new StringBuilder();
        final char buf[] = new char[1024];
        try (Reader rdr = new BufferedReader(new FileReader(f))) {
            // read until rdr.read != -1
            for (int len = 0; (len = rdr.read(buf, 0, buf.length)) != -1;) {
                sb.append(buf, 0, len);
            }
        }
        return sb.toString();
    }
}
