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
  printDeckGroup(deck);
  
  printf("\nOn insert BLACK 0, 0\n");
  deck_vertice_modify(deck, BLACK, 0, 0);
  deck_print_color(deck);
  printDeckGroup(deck);
  printf("\nOn insert BLACK 1, 1\n");
  deck_vertice_modify(deck, BLACK, 1, 1);
  deck_print_color(deck);
  printDeckGroup(deck);
  printf("\nOn insert BLACK 1, 0\n");
  deck_vertice_modify(deck, BLACK, 1, 0);
  deck_print_color(deck);
  printDeckGroup(deck);
  printf("\nOn insert BLACK 1, 2\n");
  deck_vertice_modify(deck, BLACK, 1, 2);
  deck_print_color(deck);
  printDeckGroup(deck);
  printf("\nOn insert BLACK 2, 2\n");
  deck_vertice_modify(deck, BLACK, 2, 2);
  deck_print_color(deck);
  printDeckGroup(deck);
  printf("\nOn insert WHITE 0, 2\n");
  deck_vertice_modify(deck, WHITE, 0, 2);
  deck_print_color(deck);
  printDeckGroup(deck);
  printf("\nOn insert BLACK 0, 1\n");
  deck_vertice_modify(deck, BLACK, 0, 1);
  deck_print_color(deck);
  printDeckGroup(deck);
  
  printf("\n");
  printf("Is winner ? : %c\n", deck_know_winner(deck));
  
  printf("\nOn insert TRANSPARENT 2, 2\n");
  deck_vertice_modify(deck, TRANSPARENT, 2, 2);
  deck_print_color(deck);
  printDeckGroup(deck);
  
  printf("\n");
  printf("Is winner ? : %c\n", deck_know_winner(deck));
  
  
}

int main() 
{
  printf("\nTest Simple : TEST DECK\n");

  test1_create();
  test2_delete();
  test3_print();
  test4_modify();
  printf("\n");
  return (EXIT_SUCCESS);
}
