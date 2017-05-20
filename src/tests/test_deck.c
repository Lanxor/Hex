#include <stdio.h>
#include <stdlib.h>

#include "../../header/deck.h"

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

void printDeckGroup(Deck deck)
{
  Node otherNode;
  int  cptOtherGroup = 0;
  
  otherNode = ldg_get_sentinel(deck_get_ldg(deck));
  while (cptOtherGroup < ldg_get_number(deck_get_ldg(deck)))
  {
    otherNode = ldg_get_next(otherNode);
    group_print(ldg_get_group(otherNode));
    ++cptOtherGroup;
  }
}

void test4_modify()
{
  printf("\ttest4_modify()\n");
  Deck deck;
  
  deck = deck_create(3);
  deck_print_color(deck);
  printf("\n");
  printDeckGroup(deck);
  deck_vertice_modify(deck, BLACK, 0, 0);
  deck_print_color(deck);
  printDeckGroup(deck);
  deck_vertice_modify(deck, BLACK, 0, 1);
  deck_vertice_modify(deck, BLACK, 0, 2);
  printDeckGroup(deck);
  printf("Is winner ? : %c\n", deck_know_winner(deck));
  
}

int main() {
    printf("\nTest Simple : TEST DECK\n");
    
    test1_create();
    test2_delete();
    test3_print();
    test4_modify();
    
    printf("\n");
    return (EXIT_SUCCESS);
}
