/**
 * @file deck.c
 * @author PATAT Gwendal
 * @date 26 avril 2017
 * @brief Code gestion du tablier.
 */

#include "deck.h"

typedef struct s_deck
{
  Cell	**cells;
  unsigned int	size;
} t_deck;

Deck deck_create(unsigned int size)
{
    Deck deck;
    
    deck = malloc(sizeof(t_deck));
    assert( deck != NULL );
    deck->size = size;
    deck = deck_initialize_cells(Deck deck);

    return deck;
}

Deck deck_initialize_cells(Deck deck)
{
    //TODO Il faut crée les size*size cellules
}

Deck deck_modify_size(Deck deck, unsigned int size)
{
    deck->size = size;
    return deck;
}

void deck_delete(Deck deck)
{
    //TODO Il faut supprimer les cellules avec cell_delete()
    deck_free(deck);
}

void deck_delete(Deck deck)
{
    free(deck);
}