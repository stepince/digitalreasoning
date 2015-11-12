#!/bin/bash
#########################################################################
# unix wrapper script for digitalreasoning.ProperNameAggregator
# it takes two optionals filename and dictionary parameters
# filename defaults: nlp_data.txt, dictionary defaults: NER.txt(resource)
#
#  Usage:
#     run_part3 [<filename> [<dictionary>]]
#########################################################################

BASEDIR=`dirname $0`
cd $BASEDIR

JAVA_CP=.:./classes

if [ "$1" = "" ] ; then
    java -cp "$JAVA_CP" digitalreasoning.ProperNameAggregator nlp_data
elif [ "$2" = "" ] ; then
    java -cp "$JAVA_CP" digitalreasoning.ProperNameAggregator nlp_data "$1"
else
    java -cp "$JAVA_CP" digitalreasoning.ProperNameAggregator "$1" "$2"
fi

