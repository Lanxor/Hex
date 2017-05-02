/**
 * @file cell.h
 * @author PATAT Gwendal
 * @date 26 avril 2017
 * @brief Interface de la gestion de case
 */

#ifndef __CELL_H__
#define __CELL_H__

#include <stdlib.h>
#include <stdio.h>
#include "coordinates.h"

/**
 * @brief Structure d'une case d'un tablier.
 */
typedef struct s_cell *Cell;

/**
 * @brief Créer une case vide.
 * @param void
 */
Cell cell_create(void);

/**
 * @brief Crée une nouvelle case avec des coordonnées.
 * @param coordinates cellules initialiser avec ces coordonnées
 * @return Une case avec des coordonées.
 */
Cell cell_create(Coordinates coordinates);

/**
 * @brief Modifier les coordonnées d'une case
 * @param cell Cellule à modifier
 * @param coordinates coordonées à remplacer
 * @return Retourne la nouvelle cellule modifier
 */
Cell cell_modify_coordinates(Cell cell, Coordinates coordinates);

/**
 * @brief Modifier une case par une autre.
 * @param cell Remplacé par la nouvelle cellule
 * @return Nouvelle cellules modifié.
 */
Cell cell_modify_cell(Cell cell);

/**
 * @brief Supprimer une case.
 * @param Cell cell
 */
void cell_delete(Cell cell);

/**
 * @brief Libérer une case.
 * @param Cell cell
 */
void cell_free(Cell cell);

#endif
