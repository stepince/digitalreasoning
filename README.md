Digital Reasonal Remote Programming exercise

Below are instructions for building, testing and executing the exercise.
I have checked-in all sources,classes,docs and outputs.
If you have any questions please do not hesitant to call or email. 
tel: 201 955-8113
email: stephenpince@gmail.com


The ant command can be used to build, test and execute the exercise.

Unix(bash) and windows batch commands have also been provided. No arguments are
necessary to execute the scripts.  The scripts do have optional parameters. 
See each script for usage details.


USING ANT


Building
-------------------------------- 
$ ant


Unit Testing
-------------------------------- 
$ ant test


Executing
-------------------------------- 
##### the output will be in part1_output.xml, part2_output.txt, part2_output.xml, part3_output.txt.
$ ant run



Javadoc
-------------------------------- 
$ ant javadoc


Cleaning
-------------------------------- 
$ ant clean




USING SCRIPTS


Unit Testing
-------------------------------- 
$ test_part[123]

where [123] represents the question number. 
e.g. to run to test question 1

$ test_part1


Executing
-------------------------------- 
$ run_part[123]

Where [123] represents the question number. 
e.g. to execute question 1.

$ run_part1




DESIGN and IMPLEMENTATION
=============================================

All the java source are packaged in digitalreasoning are under <basedir>/src.

Question 1.
----------------------------------------------

assumptions: US locale.  
limitations: size of source files, size of dictionary.    
alternative implementation: use a trie for data storage, it is more memory efficient.  
<basedir>/src/digitalreasoning/DocumentTokenizer.java is the java source implementing for question 1.
<basedir>/src/digitalreasoning/TestDocumentTokenizer.java is the unit test driver source for testing question 1.


Question 2.
----------------------------------------------

assumptions: US locale.  
limitations: is the linear searching of proper names in a sentence, size of source files, size of dictionary.   
alternative implementation: You could use a suffix tree for the sentence data structure. 
This would allow for constant time lookup of proper names. 
Ukkonen's Algorithm can build a suffix tree in O(n).

<basedir>/src/digitalreasoning/ProperNameDocumentTokenizer.java is the java source implementingfor question 2.   
<basedir>/src/digitalreasoning/TestProperNameDocumentTokenizer.java is the unit test driver source for testing question 2.     


Question 3.
----------------------------------------------

assumptions: US locale.    
limitations: is the linear searching of proper names in a sentence, size of source files, size of dictionary.      
alternative implementation: You could use a suffix tree for the sentence data structure. 
This would allow for constant time lookup of proper names. 
Ukkonen's Algorithm can build a suffix tree in O(n).

<basedir>/src/digitalreasoning/ProperNameAggregator.java is the java source implementing for question 3.   
<basedir>/src/digitalreasoning/TestProperNameAggregator.java is the unit test driver source for testing question 3.  


