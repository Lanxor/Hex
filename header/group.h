/**
 * @file group.h
 * @autho PATAT Gwendal - OLIVIER Thomas
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

/**
 * @brief Supprime le groupe donné en paramètre
 * @param initialGroup : groupe à supprimer
 */
void group_delete(Group initialGroup);

/**
 * @brief Affiche les différentes Vertices du groupe
 * @param initialGroup : groupe à inspecter
 */
void group_print(Group initialGroup);

/**
 * @brief Affiche la couleur du groupe
 * @param initialGroup : groupe à inspecter
 */
char group_color(Group initialGroup);

/**
 * @brief Intègre le second groupe dans le premier
 * @param firstGroup : Groupe qui recevra les éléments
 * @param secondGroup : Groupe qui donnera et sera supprimé
 * @return Retourne le groupe le premier groupe possédant les éléments des deux
 */
Group group_fusion(Group firstGroup, Group secondGroup);

int group_get_number(Group initialGroup);

Vertice group_get_vertice(Group initialGroup, int numberOfTheVertice);

int group_search_vertice(Group initialGroup, Vertice verticeToSearch);



#endif

