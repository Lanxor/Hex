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
#include <assert.h>

/**
 * @brief Structure des coordonées
 */
typedef struct s_coordinates *Coordinates;

/**
 * @brief Crée de nouvelles coordonées.
 * @fn Coordinates coordinates_create(unsigned int abscisse, 
 *                                    unsigned int ordonnee)
 * @param abscisse
 * @param ordonnee
 * @return Retourne de nouvelles coordonées initialisé.
 */
Coordinates coordinates_create(unsigned int abscisse,
                               unsigned int ordonnee);

/**
 * @brief Modifier les coordonées.
 * @fn Coordinates coordinates_modify(Coordinates coordinates, 
 *                                    unsigned int abscisse, 
 *                                    unsigned int ordonnee)
 * @param Coordinates coordinates
 * @param abscisse
 * @param ordonnee
 * @return Retourne les coordonées modifier.
 */
Coordinates coordinates_modify(Coordinates coordinates, 
                               unsigned int abscisse,
                               unsigned int ordonnee);

/**
 * @brief Supprimer les coordonées
 * @fn void coordinates_delete(Coordinates coordinates)
 * @param Coordinates coordinates
 */
void coordinates_delete(Coordinates coordinates);

/**
 * @brief Libérer les coordonées
 * @fn void coordinates_free(Coordinates coordinates)
 * @param Coordinates coordinates
 */
void coordinates_free(Coordinates coordinates);

#endif

