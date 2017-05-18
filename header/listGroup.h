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

typedef struct s_ldg* LDG;

/**
 * @brief Créer une liste chainée LDG de groupe
 * @return La liste LDG créée
 */
LDG listGroup_create();

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

#endif