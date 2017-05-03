#include <stdio.h>
#include <stdlib.h>

#include "deck.h"

void test1_create() {
    printf("\ttest1_create()\n");
    Deck deck;
    
    deck = deck_create(3);
    printf("\n");
}

void test2_delete() {
    printf("\ttest2_delete()\n");
    Deck deck;
    
    deck = deck_create(3);
    deck_delete(deck);
    printf("\n");
}

void test3_print() {
    printf("\ttest3_print()\n");
    Deck deck;
    
    deck = deck_create(3);
    deck_print(deck);
    printf("\n");
}

int main() {
    printf("\nTest Simple : TEST DECK\n");
    
    test1_create();
    test2_delete();
    test3_print();
    
    printf("\n");
    return (EXIT_SUCCESS);
}