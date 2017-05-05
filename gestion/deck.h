#ifndef __DECK_H__
#define __DECK_H__

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#include "edge.h"
#include "vertice.h"

typedef struct s_deck* Deck;

/**
 * @brief Crée un nouveau plateau de jeu.
 * @fn Deck deck_create(unsigned int size)
 * @param size : Taille du tablier de jeu.
 * @return Retourne un plateau de jeu initialiser.
 */
Deck deck_create(unsigned int size);

/**
 * @brief Cherche un sommet dans un plateau de jeu en fonction de ces coordonnées.
 * @fn deck_get_vertice(Deck deck, unisgned int abscisse, unsigned int ordonnee)
 * @param deck : Plateau de jeu dans lequel on cherche.
 * @param abscisse : Coordonnée à chercher.
 * @param ordonnee : Coordonnée à chercher.
 * @return Retourne le sommet chercher.
 */
Vertice deck_get_vertice(Deck deck, unsigned int abscisse,
                                    unsigned int ordonnee);

/**
 * @brief Vérifie si un sommet est une bordure.
 * @fn deck_vertice_is_border(Deck deck, Vertice vertice)
 * @param deck : Plateau de jeu dans lequel on vérifie.
 * @param vertice : Sommet à vérifier.
 * @return
 */
int deck_vertice_is_border(Deck deck, Vertice vertice);

/**
 * @brief Affiche le tablier de jeu trois fois, coordonnée, couleur et toutes les arrêtes.
 * @fn deck_print(Deck deck)
 * @param deck : Tablier à afficher.
 */
void deck_print(Deck deck);

/**
 * @brief Affiche le tablier de jeu avec les coordonnées des sommets.
 * @fn deck_print_coordinates(Deck deck)
 * @param deck : Tablier à afficher.
 */
void deck_print_coordinates(Deck deck);

/**
 * @brief Affiche le tablier de jeu avec les couleurs des sommets.
 * @fn deck_print_color(Deck deck)
 * @param deck : Tablier à afficher.
 */
void deck_print_color(Deck deck);

/**
 * @brief Affiche toutes les arrêtes d'un tablier de jeu.
 * @fn deck_print_edge(Deck deck)
 * @param deck : Tablier à afficher
 */
void deck_print_edge(Deck deck);

/**
 * @brief Récupère un sommet bordure en fonction de ces caractèristiques.
 * @fn deck_get_border(Deck deck, char color, int number)
 * @param deck : Le tablier de jeu où il faut chercher.
 * @param color : La couleur de la bordure. (BLACK/WHITE)
 * @param number : Le numéro de la bordure. (1/2)
 * @return 
 */
Vertice deck_get_border(Deck deck, char color, int number);

/**
 * @brief Fonction qui calcule le nombre d'arrêtes il y a dans un tablier sans compter les doublons.
 * @fn deck_get_number_edge(unsigned int size)
 * @param size : Taille d'un tablier.
 * @return 
 */
unsigned int deck_get_number_edge(unsigned int size);

/**
 * @brief Supprime un tablier de jeu.
 * @fn deck_delete(Deck deck)
 * @param deck : Tablier à supprimer.
 */
void deck_delete(Deck deck);

/**
 * @brief Libérer les zonnes mémoires du tablier.
 * @fn deck_free(Deck deck)
 * @param deck : Tablier à libérer.
 */
void deck_free(Deck deck);

/**
 * @brief Fonction qui vérifie si un sommet peut être modifier ou non par un joueur.
 * @fn deck_vertice_modify_is_possible(Deck deck, char color, unsigned int, unsigned int ordonnee)
 * @param deck : Tablier de jeu à vérifier.
 * @param color : Couleur vers laquel le sommet sera modifier.
 * @param abscisse : Abscisse du sommet à modifier.
 * @param ordonnee : Ordonnée du sommet à modifier.
 * @return 
 */
int deck_vertice_modify_is_possible(Deck deck, char color, int abscisse, int ordonnee);

/**
 * @brief Fonction qui modifie la couleur d'un sommet.
 * @fn deck_vertice_modify(Deck deck char color, unsigned int abscisse, unsigned int ordonnee)
 * @param deck
 * @param color
 * @param abscisse
 * @param ordonnee
 */
void deck_vertice_modify(Deck deck, char color, int abscisse, int ordonnee);

#endif
