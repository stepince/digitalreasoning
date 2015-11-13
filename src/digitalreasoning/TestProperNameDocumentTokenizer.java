/**
 * This contains the Unit Test class for testing ProperNameDocumentTokenizer
 * 
 */
package digitalreasoning;


public class TestProperNameDocumentTokenizer {

    public void testContains(String source, String word) {
        final DocumentTokenizer tokenizer = new ProperNameDocumentTokenizer();
        DocumentTokenizer.Document doc = tokenizer.parseDocument(source);
        assert( doc.getAllWordsAsText().contains(word) );
    }

    public void testNotContains(String source, String word) {        
        final DocumentTokenizer tokenizer = new ProperNameDocumentTokenizer();
        DocumentTokenizer.Document doc = tokenizer.parseDocument(source);
        assert( doc.getAllWordsAsText().contains(word) == false );
    }

    public static void main(String[] args) {
        try {
            TestProperNameDocumentTokenizer testTokenizer = new TestProperNameDocumentTokenizer();
            testTokenizer.testContains("hello world","hello");
            testTokenizer.testNotContains("hello word","planet");
            testTokenizer.testContains("Gavrilo Princip","Gavrilo Princip");
            testTokenizer.testNotContains("Gavrilo Principe","Gavrilo Princip");
        }
        catch ( Exception e ) {
            System.err.println("Exception: " + e);
            e.printStackTrace();
        }
    }
}
