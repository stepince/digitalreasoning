@echo off
@rem #########################################################################
@rem # windows wrapper script for digitalreasoning.ProperNameAggregator
@rem # it takes two optionals directory and dictionary parameters
@rem # directory defaults: nlp_data, dictionary defaults: NER.txt(resource)
@rem #
@rem #  Usage:
@rem #     run_part3.bat [<direcotry> [<dictionary>]]
@rem #########################################################################

setlocal
cd %~dp0
set JAVA_CP=.;classes

if "%1" == ""  (
   java -cp %JAVA_CP% digitalreasoning.ProperNameAggregator nlp_data  
) else if "%2" == ""  (
   java -cp %JAVA_CP% digitalreasoning.ProperNameAggregator "%1"
) else (
   java -cp %JAVA_CP% digitalreasoning.ProperNameAggregator "%1" "%2"
)

endlocal
