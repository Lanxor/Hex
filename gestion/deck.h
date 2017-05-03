#ifndef __DECK_H__
#define __DECK_H__

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#include "edge.h"
#include "vertice.h"

typedef struct s_deck* Deck;

Deck deck_create(unsigned int size);

Vertice deck_get_vertice(Deck deck, unsigned int abscisse,
                                    unsigned int ordonnee);

void deck_print(Deck deck);

void deck_print_coordinates(Deck deck);

void deck_print_color(Deck deck);

void deck_print_edge(Deck deck);

unsigned int deck_get_number_edge(unsigned int size);

void deck_delete(Deck deck);

void deck_free(Deck deck);

#endif
