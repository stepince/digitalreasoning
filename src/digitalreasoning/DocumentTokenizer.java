/**
 * Provides the classes necessary to parse  
 * a text document into Sentences/Words/Non-words
 * 
 * <p>
 * The DocumentTokenizer framework involves four entities:
 * the DocumentTokenizer.Document a container class for SentenceTokens
 * the DocumentTokenizer.SentenceToken a container class for Word/NonWord tokens
 * the DocumentTokenizer.WordToken which encapsulates a Word type
 * the DocumentTokenizer.NonWordToken which encapsulates a NonWord type 
 *       (e.g. !,"')
 * </p>
 *
 * @author Stephen Ince
 */
package digitalreasoning;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import java.text.BreakIterator;

import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This is a factory class to produce a DocumentTokenizer.Document object.
 *
 */
public class DocumentTokenizer {

    /**
     * Encapsulates a Document type, which is a container for Sentences
     */
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Document {

        @XmlElement(name = "sentenceToken")
        private List<SentenceToken> sentenceTokens = new ArrayList<SentenceToken>();

        /**
         * Get all the words in the documents
         * 
         *  @return List a list words
         */
        public List<WordToken> getAllWords() {
            List<WordToken> words = new ArrayList<WordToken>();
            for (SentenceToken tok : sentenceTokens) {
                words.addAll(tok.getAllWords());
            }
            return words;
        }
        
        /**
         * Get all the words in the documents
         * 
         *  @return List a list words as string text
         */
        public List<String> getAllWordsAsText() {
            List<String> words = new ArrayList<String>();
            for (WordToken tok : getAllWords()) {
                words.add(tok.getText());
            }
            return words;
        }
    }

    /**
     * Encapsulates an abstract Token type, which can be a word or non-word
     * (e.g. ",!).
     */
    @XmlTransient
    @XmlSeeAlso({ NonWordToken.class, WordToken.class })
    public static abstract class Token {
        String text;

        public String getText() {
            return this.text;
        }

        public void setText(String t) {
            this.text = t;
        }
    }

    /**
     * Encapsulates a word type.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class WordToken extends Token {
        public WordToken(String t) {
            this.text = t;
        }
    }

    /**
     * Encapsulates a non-word type, e.g. punctuations, spaces, commas. ... s
     * parens
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class NonWordToken extends Token {
        NonWordToken(String t) {
            this.text = t;
        }
    }

    /**
     * Encapsulates a sentence.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class SentenceToken {
        @XmlElement(name = "token")
        final List<Token> tokens = new ArrayList<Token>();

        /**
         * Get all the words in the sentence
         * 
         *  @return List a list of words
         */        
        public List<WordToken> getAllWords() {
            final List<WordToken> words = new ArrayList<WordToken>();
            for (Token tok : tokens) {
                if (tok instanceof WordToken) {
                    words.add( (WordToken)tok);
                }
            }
            return words;
        }
    }

    /**
     * sentence tokenizer, tokenizes a document into sentences
     */
    final private BreakIterator sentenceIterator;

    /**
     * word tokenizer, tokenizes a sentence into words/non-words
     */
    final private BreakIterator wordIterator;

    /**
     * Empty Constructor - uses Locale.US as the default local for parsing
     */
    public DocumentTokenizer() {
        this.sentenceIterator = BreakIterator.getSentenceInstance(Locale.US);
        this.wordIterator = BreakIterator.getWordInstance(Locale.US);
    }

    /**
     * Constructor - uses the specified locale when parsing a text document.
     * 
     * @param l the locale
     *            
     */
    public DocumentTokenizer(Locale l) {
        this.sentenceIterator = BreakIterator.getSentenceInstance(l);
        this.wordIterator = BreakIterator.getWordInstance(l);
    }

    /** 
     * Get the word iterator property
     *
     * @return The word iterator property
     */      
    BreakIterator getWordIterator() {
        return this.wordIterator;
    }

    /** 
     * Get the sentence iterator property
     *
     * @return The sentence iterator property
     */      
    BreakIterator getSentenceIterator() {
        return this.sentenceIterator;
    }

    /**
     * Parse a sentence string into a sentence object
     * 
     * @param source
     *            the string contents of a sentence
     * @return a SentenceToken which encapsulates a sentence
     */
    SentenceToken parseSentence(String source) {

        final SentenceToken sentenceTok = new SentenceToken();

        this.wordIterator.setText(source);
        int firstIndex = this.wordIterator.first();
        int lastIndex = this.wordIterator.next();

        // loop-thru all the tokens (words/non-words) and
        // add the tokens to the sentence token
        while (lastIndex != BreakIterator.DONE) {
            final String token = source.substring(firstIndex, lastIndex);
            if (Character.isLetterOrDigit(token.charAt(0))) {
                sentenceTok.tokens.add(new WordToken(token));
            } 
            else {
                sentenceTok.tokens.add(new NonWordToken(token));
            }
            firstIndex = lastIndex;
            lastIndex = this.wordIterator.next();
        }
        return sentenceTok;
    }

    /**
     * Parse a text document and objectify it.
     * 
     * @param source
     *            the string contents of a document
     * @return a Document which encapsulates a text document
     */
    public Document parseDocument(String source) {
        final Document doc = new Document();

        this.sentenceIterator.setText(source);
        int lastIndex = sentenceIterator.first();

        // loop-thru all the sentences
        // parse and add the sentences to the doc document
        while (lastIndex != BreakIterator.DONE) {
            int firstIndex = lastIndex;
            lastIndex = this.sentenceIterator.next();

            // parse all the sentences and add the words to the sentence
            if (lastIndex != BreakIterator.DONE) {
                String sentence = source.substring(firstIndex, lastIndex);
                SentenceToken sentenceTok = parseSentence(sentence);
                doc.sentenceTokens.add(sentenceTok);
            }
        }
        return doc;
    }

    /**
     * Helper routine to xml pretty print a Document.
     * 
     * @param doc
     *            the Document object represents a text document
     * @param out
     *            the PrintStream which to print
     */
    public void outputXml(Document doc, PrintStream out)
            throws JAXBException {
        final JAXBContext jc = JAXBContext
                .newInstance(DocumentTokenizer.Document.class);
        final Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(doc, out);
    }

    
    /**
     *  This is main method which tests the DocumentTokenizer class.
     * 
     * @param args takes one argument
     *      <p>args[0] file word source parameter 
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Missing filename parameter.");
            System.err.println("\tUsage: java digitalreasoning.DocumentTokenizer <filename>");
            System.exit(-1);
        }

        try {
            final DocumentTokenizer tokenizer = new DocumentTokenizer();
            final String filename = args[0];

            final String fileContents = IOUtil.getFileContents(filename);
            final Document doc = tokenizer.parseDocument(fileContents);
            tokenizer.outputXml(doc, System.out);
        }
        catch (Exception e) {
            System.err.println("Exception: " + e);
            e.printStackTrace();
        }
    }
}