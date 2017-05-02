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

#include "cell.h"

/**
 * @brief Structure du tablier.
 */
typedef struct s_deck* Deck;

/**
 * @brief Crée un tablier vide.
 */
Deck deck_create();

/**
 * @brief Modifie une case du tablier.
 */

/**
 * @brief Supprimer un tablier.
 * @param Deck deck
 */
void deck_delete(Deck deck);

/**
 * @brief Libérer un tablier.
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

/**
 * @brief Sauvegarder une partie en cours.
 * @param Deck deck
 * @param char *name_file
 * @return Retourne si la partie à bien été sauvegardé.
 * @returns 1 : Partie bien sauvegardé
 * @returns 0 : Erreur
 */
int deck_save_file(Deck deck, char *name_file);

/**
 * @brief Restaurer une partie en cours.
 * @param Deck deck
 * @param char *name_file
 * @return Retourne si la partie à bien été chargé.
 * @returns 1 : Partie bien chargé
 * @returns 0 : Erreur
 */
int deck_restore_file(Deck deck, char *name_file);

#endif
