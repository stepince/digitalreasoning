/**
 * This contains the Unit Test class for testing ProperNameAggregator
 * 
 */
package digitalreasoning;


public class TestProperNameAggregator {

    public void testContains(String word) {
        final ProperNameAggregator aggr = new ProperNameAggregator("nlp_data");
        assert( aggr.execute().contains(word) );
    }

    public void testNotContains(String word) { 
        final ProperNameAggregator aggr = new ProperNameAggregator("nlp_data");
        assert( aggr.execute().contains(word) == false);       
    }

    public static void main(String[] args) {
        try {
            TestProperNameAggregator testAggr = new TestProperNameAggregator();

            testAggr.testContains("James Clerk Maxwell");
            testAggr.testNotContains("James Clerk Maxwelle");
            testAggr.testNotContains("James Clerk Maxwel");

            testAggr.testContains("Oracle Corporation");
            testAggr.testNotContains("Oracle");
            testAggr.testNotContains("Corporation");
        }
        catch ( Exception e ) {
            System.err.println("Exception: " + e);
            e.printStackTrace();
        }
    }
}
