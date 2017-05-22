#! /bin/bash

gcc test_deck.c ../../obj/* -o test_deck
gcc test_edge.c ../../obj/* -o test_edge
gcc test_vertice.c ../../obj/* -o test_vertice
gcc test_group.c ../../obj/* -o test_group

./test_vertice
./test_edge
./test_group
./test_deck
