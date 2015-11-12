#!/bin/bash
###############################################################################
# unix wrapper script for testing digitalreasoning.ProperNameDocumentTokenizer
#
#  Usage:
#     test_part2.sh
###############################################################################

BASEDIR=`dirname $0`
cd $BASEDIR

JAVA_CP=.:./classes

java -ea -cp "$JAVA_CP" digitalreasoning.TestProperNameDocumentTokenizer

