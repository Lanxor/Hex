/**
 * @file deck.h
 * @author PATAT Gwendal
 * @date 26 avril 2017
 * @brief Interface de la gestion du tablier
 */

#ifndef __DECK_H__
#define __DECK_H__

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#include "cell.h"

/**
 * @brief Structure du tablier.
 */
typedef struct s_deck* Deck;

/**
 * @brief Crée un tablier vide.
 * @return Retourne un nouveau tablier
 */
Deck deck_create(unsigned int size);

/**
 * @brief Initialise un tablier (toutes les cases sont initialiser)
 * @fn Deck deck_initialize_cells(Deck deck)
 * @param deck Tablier de jeu à initialiser
 * @return Retourne un tablier
 */
Deck deck_initialize_cells(Deck deck);

/**
 * @brief Modifie la taille du tablier
 * @fn Deck deck_modify_size(Deck deck, unsigned int size)
 * @param deck Tablier à modifier
 * @param size Taille du nouveau tablier
 * @return Retourne le nouveau tablier
 */
Deck deck_modify_size(Deck deck, unsigned int size);

/**
 * @brief Modifie une case du tablier.
 */

/**
 * @brief Supprimer un tablier.
 * @fn void deck_delete(Deck deck)
 * @param Deck deck
 */
void deck_delete(Deck deck);

/**
 * @brief Libérer un tablier.
 * @fn void deck_free(Deck deck)
 * @param Deck deck
 */
void deck_free(Deck deck);

/**
 * @brief Jouer un coup.
 */

/**
 * @brief Vérifier la légalité d'un coup.
 */

/**
 * @brief Déterminer un vainqueur.
 */

#endif
