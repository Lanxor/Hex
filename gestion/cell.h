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
 * @param int x
 * @param int y
 * @return Une case avec des coordonées.
 */
Cell cell_create(int x, int y);

/**
 * @brief Modifier une case.
 * @param Cell cell
 * @return Nouvelle cellules modifié.
 */
Cell cell_modify(Cell cell);

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
