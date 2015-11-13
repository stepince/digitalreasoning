/**
 * This contains the Unit Test class for testing DocumentTokenizer
 * 
 */
package digitalreasoning;

public class TestDocumentTokenizer {

    public void testContains(String source, String word) {
        final DocumentTokenizer tokenizer = new DocumentTokenizer();
        DocumentTokenizer.Document doc = tokenizer.parseDocument(source);
        assert( doc.getAllWordsAsText().contains(word) );
    }

    public void testNotContains(String source, String word) {        
        final DocumentTokenizer tokenizer = new DocumentTokenizer();
        DocumentTokenizer.Document doc = tokenizer.parseDocument(source);
        assert( doc.getAllWordsAsText().contains(word) == false );
    }

    public static void main(String[] args) {
        try {
            TestDocumentTokenizer testTokenizer = new TestDocumentTokenizer();
            testTokenizer.testContains("hello world","hello");
            testTokenizer.testContains("hello world","world");
            testTokenizer.testNotContains("hello world","planet");
            testTokenizer.testNotContains("hello world","hell");
        }
        catch ( Exception e ) {
            System.err.println("Exception: " + e);
            e.printStackTrace();
        }
    }
}
