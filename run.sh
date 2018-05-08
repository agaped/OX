#! /bin/bash
# run OX game project, feel free to change params but follow the pattern:
# language:pl/en (boardSize lengthWinCombination X/O move move move ...)times3 continueGame:y/n/t/n
#"$@"
cd `dirname "$0"`
java -jar ./target/javaacademy-4.0.jar pl "3 3" 3 X 1 2 3 4 5 6 7 "3 4" 4 X 1 5 2 6 3 7 7 11 55 cx 8 "4 4" 4 O 1 5 2 6 3 7 4 n
java -jar ./target/javaacademy-4.0.jar pl "2 3" "5 6" 5 X 1 2 8 9 15 16 22 23 29 "2000 5230" "5 7" 4 X  1 1 6 2 11 3 16 4 21 "6 3" 6 O 1 2 3 6 5 4 7 8 9 12 11 10 13 14 15 18 17 16 n
java -jar ./target/javaacademy-4.0.jar en "3 12" 3 X 1 2 13 4 27 6 7 15 10 28 "10 2" "10 3" 4 X 1 5 2 6 3 7 7 i7 *3 4 16 26 13 23 10 "4 4" 4 O 1 5 2 6 3 7 4 n
