@echo off
@rem #########################################################################
@rem # windows wrapper script for digitalreasoning.ProperNameDocumentTokenizer
@rem # it takes two optionals filename and dictionary parameters
@rem # filename defaults: nlp_data.txt, dictionary defaults: NER.txt(resource)
@rem #
@rem #  Usage:
@rem #     run_part2.bat [<filename> [<dictionary>]]
@rem #########################################################################

setlocal
cd %~dp0
set JAVA_CP=.;classes

if "%1" == ""  (
   java -cp %JAVA_CP% digitalreasoning.ProperNameDocumentTokenizer nlp_data.txt  
) else if "%2" == ""  (
   java -cp %JAVA_CP% digitalreasoning.ProperNameDocumentTokenizer "%1"
) else (
   java -cp %JAVA_CP% digitalreasoning.ProperNameDocumentTokenizer "%1" "%2"
)

endlocal
