#!/bin/bash
########################################################################
# unix wrapper script for testing digitalreasoning.ProperNameAggregator
#
#  Usage:
#     test_part3.sh
########################################################################

BASEDIR=`dirname $0`
cd $BASEDIR

JAVA_CP=.:./classes

java -ea -cp "$JAVA_CP" digitalreasoning.TestProperNameAggregator

