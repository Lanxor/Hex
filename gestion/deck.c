#include "cell.h"
#include "deck.h"

typedef struct s_coordinates
{
  unsigned int	x;
  unsigned int	y;
} t_coordinates;

typedef struct s_cell
{
  t_coordinates	*coordinates;
  struct s_cell **adjacentCells;
  unsigned int	value; // replace with pointer to player
} t_cell;

typedef struct s_deck
{
  t_cell	**cells;
  unsigned int	numberOfCells;
} t_deck;
