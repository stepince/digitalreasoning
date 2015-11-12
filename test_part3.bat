@echo off

@rem #################################################################################
@rem # windows wrapper script for testing digitalreasoning.TestProperNameAggregator
@rem #
@rem #  Usage:
@rem #     test_part3.bat
@rem #################################################################################

setlocal
cd %~dp0
set JAVA_CP=.;classes

java -ea -cp %JAVA_CP% digitalreasoning.TestProperNameAggregator


endlocal
