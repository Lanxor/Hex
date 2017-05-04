/**
 * @file coordinates.c
 * @author PATAT Gwendal
 * @date 26 avril 2017
 * @brief Code gestion des coordonées.
 */

#include "coordinates.h"

typedef struct s_coordinates
{
  unsigned int	abscisse;
  unsigned int	ordonnee;
} t_coordinates;

Coordinates coordinates_create(unsigned int abscisse, 
                               unsigned int ordonnee)
{
    Coordinates coordinates;
    
    coordinates = malloc(sizeof(t_coordinates));
    assert( coordinates != NULL );
    
    coordinates->abscisse = abscisse;
    coordinates->ordonnee = ordonnee;
    
    return coordinates;
}

Coordinates coordinates_modify(Coordinates coordinates, 
                               unsigned int abscisse,
                               unsigned int ordonnee)
{
    coordinates->abscisse = abscisse;
    coordinates->ordonnee = ordonnee;
    
    return coordinates;
}

void coordinaes_delete(Coordinates coordinates)
{
    coordinates_free(coordinates);
}

void coordinates_free(Coordinates coordinates)
{
    free(coordinates);
}