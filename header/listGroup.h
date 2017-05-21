/**
 * @file listGroup.h
 * @autho PATAT Gwendal - OLIVIER Thomas
 * @date Mai 2017
 * @brief Fichier de prototype de la liste chaînée de Groupe
 */
#ifndef __LISTG_H__
#define __LISTG_H__

#include <assert.h>
#include "group.h"

typedef struct s_node *Node;
typedef struct s_ldg* LDG;


/**
 * @brief Créer une liste chainée LDG de groupe
 * @return La liste LDG créée
 */
LDG listGroup_create();

Node linkThreeNode(Node leftNode, Node midNode, Node rightNode);

/**
 * @brief Ajoute un groupe à la liste LDG
 * @param ldg : la liste
 * @param groupToInsert : le groupe a ajouter
 * @return Retourne une liste ldg.
 */
LDG listGroup_append(LDG ldg, Group groupToInsert);

/**
 * @brief Supprime un groupe de la liste LDG
 * @param ldg : la liste
 * @param groupToInsert : le groupe a supprimer
 * @return Retourne une liste ldg.
 */
LDG listGroup_remove(LDG ldg, Group groupToRemove);

/**
 * @brief Supprime la liste ldg
 * @param ldg : la liste
 */
void listGroup_delete(LDG ldg);

/**
 * @brief Fonction donnant la sentinelle d'une LDG
 * @param ldg : la liste ldg à inspecter
 * @return la node sentinelle
 */
Node ldg_get_sentinel(LDG ldg);

/**
 * @brief Fonction donnant le noeud suivant de celui en cour
 * @param currentNode : le noeud de la liste courante
 * @return la node suivante
 */
Node ldg_get_next(Node currentNode);

/**
 * @brief Fonction donnant le groupe du noeud donné
 * @param currentNode : le noeud de la liste courante
 * @return le groupe de la node
 */
Group ldg_get_group(Node currentNode);

/**
 * @brief Fonction donnant le nombre de noeud d'une LDG
 * @param ldg : la liste ldg à inspecter
 * @return le nombre de noeud
 */
int ldg_get_number(LDG ldg);


#endif