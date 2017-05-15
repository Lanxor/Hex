/**
 * @file edge.h
 * @autho PATAT Gwendal - OLIVIER Thomas
 * @date Avril 2017
 * @brief Fichier de prototype des arêtes.
 */

#ifndef __EDGE_H__
#define __EDGE_H__

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#include "vertice.h"

/**
 * @typedef Structure Arête
 */
typedef struct s_edge *Edge;

/**
 * @brief Crée une arête entre deux sommet.
 * @param first : Premier sommet.
 * @param second : Deuxième sommet.
 * @return Retourne une arête.
 */
Edge edge_create(Vertice first, Vertice second);

/**
 * @brief Fonction qui affiche les deux sommet avec une relation "-" pour définir le lien.
 * @param edge : Arête à afficher.
 */
void edge_print(Edge edge);

/**
 * @brief Fonction qui récupère le premier sommet de l'arête.
 * @param edge : Arête où il faut récupéré l'élément.
 * @return 
 */
Vertice edge_get_vertice_first(Edge edge);

/**
 * @brief Fonction qui récupère le second sommet de l'arête.
 * @param edge : Arête où il faut récupéré l'élément.
 * @return 
 */
Vertice edge_get_vertice_second(Edge edge);

/**
 * @brief Supprime l'arête.
 * @param edge
 */
void edge_delete(Edge edge);

/**
 * @brief Libérer les zonnes mémoires de l'arête.
 * @param edge
 */
void edge_free(Edge edge);

#endif

