#!/bin/bash
########################################################################
# unix wrapper script for digitalreasoning.DocumentTokenizer
# it takes one optional filename parameter and defaults to nlp_data.txt
#
#  Usage:
#     run_part1 <filename>
########################################################################

BASEDIR=`dirname $0`
cd $BASEDIR

JAVA_CP=.:./classes

if [ "$1" = "" ] ; then
    java -cp "$JAVA_CP" digitalreasoning.DocumentTokenizer nlp_data.txt
else
    java -cp "$JAVA_CP" digitalreasoning.DocumentTokenizer "$1"
fi

