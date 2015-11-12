@echo off

@rem ################################################################
@rem # windows wrapper script for digitalreasoning.DocumentTokenizer
@rem # it takes one optional parameter (defaults: nlp_data.txt)
@rem #
@rem #  Usage:
@rem #     run_part1.bat <filename>
@rem ################################################################


setlocal
cd %~dp0
set JAVA_CP=.;classes

if "%1" == ""  (
   java -cp %JAVA_CP% digitalreasoning.DocumentTokenizer nlp_data.txt  
) else (
   java -cp %JAVA_CP% digitalreasoning.DocumentTokenizer "%1"  
)

endlocal
