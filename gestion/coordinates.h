/**
 * @file coordinates.h
 * @author PATAT Gwendal
 * @date 26 avril 2017
 * @brief Interface de la gestion des coordonnées.
 */

#ifndef __COORDINATES_H__
#define __COORDINATES_H__

#include <stdlib.h>
#include <stdio.h>

/**
 * @brief Structure des coordonées
 */
typedef struct s_coordinates *Coordinates;

/**
 * @brief Crée de nouvelles coordonées.
 * @param int x
 * @param int y
 * @return Retourne de nouvelles coordonées initialisé.
 */
Coordinates coordinates_create(int x, int y);

/**
 * @brief Modifier les coordonées.
 * @param Coordinates coordinates
 * @param int x
 * @param int y
 * @return Retourne les coordonées modifier.
 */
Coordinates coordinates_modify(Coordinates coordinates, int x, int y);

/**
 * @brief Supprimer les coordonées
 * @param Coordinates coordinates
 */
void coordinates_delete(Coordinates coordinates);

/**
 * @brief Libérer les coordonées
 * @param Coordinates coordinates
 */
void coordinates_free(Coordinates coordinates);

#endif

