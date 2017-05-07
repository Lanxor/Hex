/**
 * @file deck.h
 * @autho PATA Gwendal - OLIVIER Thomas
 * @date Mai 2017
 * @brief Fichier de prototype des groupes du Jeu.
 */

#ifndef __GROUP_H__
#define __GROUP_H__

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#include "vertice.h"

/**
 * @brief Structure des groupes de jeu.
 * @typedef Structure des groupes de jeu
 */
typedef struct s_group *Group;

/**
 * @brief Crée un nouveau groupe de jeu
 * @return Retourne un groupe.
 */
Group group_create();

/**
 * @brief Ajoute un vertice dans un groupe
 * @param initialGroup : groupe où l'on insert le vertice
 * @param verticeToInsert : vertice à insérer dans le groupe
 * @return Retourne le groupe modifié avec la nouvelle vertice
 */
Group group_insert(Group initialGroup, Vertice verticeToInsert);

#endif /* GROUP_H */

