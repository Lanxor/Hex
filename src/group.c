
#include "group.h"

typedef struct s_group
{
    Vertice *list_vertice;
    unsigned int number_vertice;
} t_group;

Group group_create()
{
  Group initialGroup;
  initialGroup = (Group) malloc(sizeof(t_group));
  initialGroup->number_vertice = 0;
  initialGroup->list_vertice = NULL;
  return (initialGroup);
}

Group group_insert(Group initialGroup, Vertice verticeToInsert)
{
  ++initialGroup->number_vertice;
  initialGroup->list_vertice = (Group) realloc(initialGroup->list_vertice, sizeof(Vertice) * initialGroup->number_vertice);
  initialGroup->list_vertice[initialGroup->number_vertice - 1] = verticeToInsert;
  return (initialGroup);
}

/*
 * A faire :
 * Créer une fonction remove
 * Créer une fonction fusion
 * Créer une fonction de recherche/vérification de gagnant
 */