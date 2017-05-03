/**
 * @file historical.c
 * @author PATAT Gwendal
 * @date 26 avril 2017
 * @brief Code gestion de l'historique des coups
 */

typedef struct s_stroke
{
    struct s_stroke *before;
    unsigned int player;
    Coordinates coordinates;
    struct s_stroke *after;
} t_stroke;

typedef struct s_historical
{
    t_stroke *first;
    t_stroke *last;
    unsigned int number_of_stroke;
} t_historical;