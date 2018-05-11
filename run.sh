#! /bin/bash
# run OX game project, feel free to change params but follow the pattern:
# language:pl/en (boardSize lengthWinCombination X/O move move move ...)times3 continueGame:y/n/t/n
#"$@"
cd `dirname "$0"`
java -jar ./target/javaacademy-5.0.jar n 1 2 3 4 5 6 7 1 5 2 6 3 1 5 2 6 3 y y pl "2 3" "3 4" 3 Wojtek Kostek O 12 11 7 2 8 6 4 2 2 1 6 5 10 7 3 8 4 6 n