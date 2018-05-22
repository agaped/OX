#! /bin/bash
# run OX game project, feel free to change params but follow the pattern:
# default settings:y/n language:pl/en (boardSize lengthWinCombination X/O move move move ...)times3 continueGame:y/n/t/n
cd `dirname "$0"`
java -jar ./target/javaacademy-7.0.jar n 1 2 3 4 5 6 7 1 5 2 6 3 1 5 2 6 3 n