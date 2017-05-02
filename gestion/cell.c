/**
 * @file cell.c
 * @author PATAT Gwendal
 * @date 26 avril 2017
 * @brief Code gestion de cases.
 */

#include "cell.h"

typedef struct s_cell
{
  Coordinates coordinates;
  struct s_cell **adjacentCells;
  unsigned int	value; // replace with pointer to player
} t_cell;

Cell cell_create_void(void)
{
    return NULL;
}

Cell cell_create(Coordinates coordinates)
{
    Cell cell = malloc(sizeof(t_cell));
    assert( cell != NULL );
    
    cell->coordinates = coordinates;
    cell->value = 0;
    
    return cell;
}

void cell_modify_cell(Cell cell_old, Cell cell_new)
{
    cell_old = cell_new;
}

void cell_modify_coordinates(Cell cell, Coordinates coordinates)
{
    cell->coordinates = coordinates;
}

void cell_modify_value(Cell cell, unsigned int value)
{
    cell->value = value;
}

void cell_delete(Cell cell)
{
    cell_free(cell);
}

void cell_free(Cell cell)
{
    free(cell);
}