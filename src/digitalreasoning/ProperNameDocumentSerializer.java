/**
 * Provides a driver to xml serialize a ProperNameDocumentTokenizer.Document
 * a text document into Sentences/Words/Non-words
 *
 * @author Stephen Ince
 */
package digitalreasoning;

public class ProperNameDocumentSerializer {

    /**
     *  This is main method which tests the ProperNameDocumentTokenizer class.
     * 
     * @param args takes two arguments
     *      <p>args[0] the file word source parameter
     *      <p>args[1] is an optional dictionary parameter
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Missing filename parameter.");
            System.err.println("\tUsage: java digitalreasoning.ProperNameDocumentSerializer <filename> [dictionary]");
            System.exit(-1);
        }
        
        try {
            final String filename = args[0];

            final DocumentTokenizer tokenizer = ( args.length > 1) 
                    ? new ProperNameDocumentTokenizer( ProperNameDocumentTokenizer.createDictionary(args[1]) )
                    : new ProperNameDocumentTokenizer();
                                    
            final String fileContents = IOUtil.getFileContents(filename);
            
            final ProperNameDocumentTokenizer.Document doc = tokenizer.parseDocument(fileContents);
            
            tokenizer.outputXml(doc, System.out);
        }
        catch (Exception e) {
            System.err.println("Exception: " + e);
            e.printStackTrace();
        }
    }

}
