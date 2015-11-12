#!/bin/bash
########################################################################
# unix wrapper script for digitalreasoning.ProperNameDocumentTokenizer
# it takes two optionals filename and dictionary parameters
# filename defaults: nlp_data.txt, dictionary defaults: NER.txt(resource)
#
#  Usage:
#     run_part2 [<filename> [<dictionary>]]
########################################################################

BASEDIR=`dirname $0`
cd $BASEDIR

JAVA_CP=.:./classes

if [ "$1" = "" ] ; then
    java -cp "$JAVA_CP" digitalreasoning.ProperNameDocumentTokenizer nlp_data.txt
elif [ "$2" = "" ] ; then
    java -cp "$JAVA_CP" digitalreasoning.ProperNameDocumentTokenizer nlp_data.txt "$1"
else
    java -cp "$JAVA_CP" digitalreasoning.ProperNameDocumentTokenizer "$1" "$2"
fi

