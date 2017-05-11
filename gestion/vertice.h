/**
 * @file vertice.h
 * @autho PATA Gwendal - OLIVIER Thomas
 * @date Mai 2017
 * @brief Fichier de prototype de la gestion des sommets.
 */

#ifndef __VERTICE_H__
#define __VERTICE_H__

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#define TRANSPARENT 't'
#define BLACK 'b'
#define WHITE 'w'

typedef struct s_vertice *Vertice;

/**
 * @brief Créer un sommet.
 * @param color : La couleur du sommet.
 * @param abscisse : L'abscisse du sommet.
 * @param ordonnee : L'ordonnée du sommet.
 * @return 
 */
Vertice vertice_create(char color, unsigned int abscisse,
                                   unsigned int ordonnee);

/**
 * @brief Fonction qui modifie la couleur d'un sommet.
 * @param vertice : Le sommet à modifier.
 * @param color : Couleur vers laquel le sommet sera modifié.
 */
void vertice_modify_color(Vertice vertice, char color);

/**
 * @brief Fonction qui récupère l'asbcisse d'un sommet.
 * @param vertice : Le sommet où il faut chercher.
 * @return Retourne l'abscisse du sommet.
 */
unsigned int vertice_get_abscisse(Vertice vertice);

/**
 * @brief Fonction qui récupère l'ordonnée d'un sommet.
 * @param vertice : Le sommet où il faut chercher.
 * @return Retourne l'ordonnée du sommet.
 */
unsigned int vertice_get_ordonnee(Vertice vertice);

/**
 * @brief Fonction qui retourne la couleur du sommet.
 * @param vertice : Le sommet où il faut chercher.
 * @return Retourne la couleur du sommet.
 */
char vertice_get_color(Vertice vertice);

/**
 * @brief Fonction qui modifie la couleur d'un sommet.
 * @param vertice : Le sommet à modifier.
 * @param color : La couleur à modifier.
 */
void vertice_set_color(Vertice vertice , char color);

/**
 * @brief Fonction qui affiche les coordonées d'un sommet.
 * @param vertice : Le sommet à afficher.
 */
void vertice_print_coordinates(Vertice vertice);

/**
 * @brief Fonction qui affiche la couleur du sommet.
 * @param vertice
 */
void vertice_print_color(Vertice vertice);

/**
 * @brief Fonction qui supprime un sommet.
 * @param vertice : Le sommet à supprimer.
 */
void vertice_delete(Vertice vertice);

/**
 * @brief Fonction qui libère les zonnes mémoire du sommet.
 * @param vertice : Le sommet à libérer.
 */
void vertice_free(Vertice vertice);

#endif

