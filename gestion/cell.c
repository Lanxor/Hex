/**
 * @file cell.c
 * @author PATAT Gwendal
 * @date 26 avril 2017
 * @brief Code gestion de cases.
 */

#include "cell.h"

typedef struct s_cell
{
  t_coordinates	*coordinates;
  struct s_cell **adjacentCells;
  unsigned int	value; // replace with pointer to player
} t_cell;