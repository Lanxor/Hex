/**
 * @file deck.c
 * @author PATAT Gwendal
 * @date 26 avril 2017
 * @brief Code gestion du tablier.
 */

#include "deck.h"

typedef struct s_deck
{
  t_cell	**cells;
  unsigned int	numberOfCells;
} t_deck;
