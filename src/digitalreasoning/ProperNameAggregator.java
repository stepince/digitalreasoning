package digitalreasoning;

import java.util.Set;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import java.io.File;


/** 
 *   A task class for retrieving all the words in a file.
 *   
 */
class ProperNameAdderTask implements Runnable {

    /*
     * The filename to process
     */
    final private String filename;
    
    /*
     * The set where the results of proper names search will be stored
     */
    final private Set<String> properNames;
    
    /*
     * A dictionary containing all the proper names
     */
    final private Set<String> dictionary;
    
    /*
     * Constructor - initialized the ProperNameAdderTask
     *  
     * @param dictionary the proper name dictionary
     * @param filename the filename of the source to search
     * @param properNamesOutput the output to store the results of the proper name search
     * 
     */    
    public ProperNameAdderTask(Set<String> dictionary, String filename, Set<String> properNamesOutput) {
        this.properNames = properNamesOutput;
        this.filename = filename;
        this.dictionary = dictionary;
    }

    /*
     * The main execute method which will be executed by a single worker thread,
     * it will search a given file and store the results in a common output set.
     * 
     */
    public void run() {
        try {
            final String fileContents = IOUtil.getFileContents(filename);
            final DocumentTokenizer tokenizer = (this.dictionary == null )
                          ? new ProperNameDocumentTokenizer()
                          : new ProperNameDocumentTokenizer(dictionary);
                          
            final DocumentTokenizer.Document doc = tokenizer.parseDocument(fileContents);
            List<String> names = ProperNameDocumentTokenizer.getAllProperNames(doc);
            properNames.addAll(names);
        } 
        catch (Exception e) {
            System.err.println(this.getClass().getName() + ".run() exception: " + e);
        }
    }
}

/**
 * This is an executor service class which will aggregate all the proper names
 * that are contained in files under a specified directory
 *
 */
public class ProperNameAggregator {

    /**
     * The default number of worker (threads) to use for aggregation.
     */    
    public final static int DEFAULT_WORKERS = 5;

    /**
     * The actually worker (threads) to use for aggregation.
     */        
    private final int workers;
    
    /**
     * The directory location of the word source files.
     */        
    private final String directory;
    
    /**
     * The proper name dictionary, if null defaults to the NER.txt resource.
     */        
    private Set<String> dictionary = null;
    
    /**
     * Constructor - that will initialize ProperNameAggregator
     * @param directory the search directory that will contain files to search
     * 
     */
    public ProperNameAggregator(String directory) {
        this.directory = directory;
        this.workers = DEFAULT_WORKERS;
    }

    /**
     * Constructor - that will initialize ProperNameAggregator
     *
     * @param directory the search directory that will contain files to search
     * @param workers the number of worker threads
     * 
     */
    public ProperNameAggregator(String directory, int workers) {
        this.directory = directory;
        this.workers = workers;
    }

    /** 
     * Set the dictionary property
     * The dictionary property contains a list of all proper names
     *
     */    
    public void setDictionary(Set<String> set ) {
        this.dictionary = set;        
    }
    
    /** 
     * Get the dictionary property
     * The dictionary property contains a list of all proper names
     *
     * @return The dictionary property
     */    
    public Set<String> getDictionary() {
        return this.dictionary;      
    }

    /** 
     * The main execute method. It will create the worker threads to collect the
     * proper names.
     *
     * @return List of all proper names
     */ 
    public List<String> execute() {

        // create a thread-safe concurrent set
        Set<String> setOutput = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
        
        File[] files = IOUtil.list(this.directory);

        ExecutorService executor = Executors.newFixedThreadPool(workers);

        for (File f : files) {
            executor.execute(new ProperNameAdderTask(this.dictionary, f.getAbsolutePath(), setOutput));
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } 
        catch (InterruptedException ignore) {
            //System.err.println("exception: " + ignore);
        }
        
        List<String> properNames = new ArrayList<String>(setOutput);
        Collections.sort(properNames);
        return properNames;
    }

    /**
     *  This is main method which tests the ProperNameAggregator class.
     * 
     * @param args takes two arguments
     *      <p>args[0] directory parameter for the word source files
     *      <p>args[1] is an optional dictionary parameter
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Missing directory parameter.");
            System.err.println("\tUsage: java digitalreasoning.ProperNameAggregator <directory> [<dictionary>]");
            System.exit(-1);
        }

        try {
            final String directory = args[0];

            final ProperNameAggregator aggr = new ProperNameAggregator(directory);

            if ( args.length > 1 ) {
                aggr.setDictionary( ProperNameDocumentTokenizer.createDictionary(args[1]) );
            }
            
            List<String> names = aggr.execute();
            for (String n : names) {
                System.out.println(n);
            }
        } 
        catch (Exception e) {
            System.err.println("Exception: " + e);
            e.printStackTrace();
        }
    }
}
