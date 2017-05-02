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
#include <assert.h>
#include "coordinates.h"

/**
 * @def
 */
#define CELL_VOID 0
#define CELL_PLAYER_1 1
#define CELL_PLAYER_2 2

/**
 * @brief Structure d'une case d'un tablier.
 */
typedef struct s_cell *Cell;

/**
 * @brief Créer une case vide.
 * @fn Cell cell_create(void)
 * @param void
 */
Cell cell_create(void);

/**
 * @brief Crée une nouvelle case avec des coordonnées.
 * @fn Cell cell_create(Coordinates coordinates)
 * @param coordinates cellules initialiser avec ces coordonnées
 * @return Une case avec des coordonées.
 */
Cell cell_create(Coordinates coordinates);

/**
 * @brief Modifier une case par une autre.
 * @fn void cell_modify_cell(Cell cell_old, Cell cell_new)
 * @param cell_old Cellule à remplacer.
 * @param cell_new nouvelle cellule.
 * @return Nouvelle cellules modifié.
 */
void cell_modify_cell(Cell cell_old, Cell cell_new);

/**
 * @brief Modifier les coordonnées d'une case.
 * @fn void cell_modify_coordinates(Cell cell, Coordinates coordinates)
 * @param cell Cellule à modifier
 * @param coordinates coordonées à remplacer
 * @return Retourne la nouvelle cellule modifier
 */
void cell_modify_coordinates(Cell cell, Coordinates coordinates);

/**
 * @brief Modifie la valeur de la cellule
 * @fn void cell_modify_value(Cell cell, unsigned int value)
 * @param cell Cellule à modifier
 * @param value Valeur à remplacer.
 */
void cell_modify_value(Cell cell, unsigned int value);

/**
 * @brief Supprimer une case.
 * @fn void cell_delete(Cell cell)
 * @param Cell cell
 */
void cell_delete(Cell cell);

/**
 * @brief Libérer une case.
 * @fn void cell_free(Cell cell);
 * @param Cell cell
 */
void cell_free(Cell cell);

#endif
