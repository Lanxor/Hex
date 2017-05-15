#include "../header/group.h"


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
  initialGroup->list_vertice = (Vertice*) malloc(sizeof(Vertice));
  return (initialGroup);
}

Group group_insert(Group initialGroup, Vertice verticeToInsert)
{
  ++initialGroup->number_vertice;
  initialGroup->list_vertice = (Vertice*) realloc(initialGroup->list_vertice, sizeof(Vertice) * initialGroup->number_vertice);
  initialGroup->list_vertice[initialGroup->number_vertice - 1] = verticeToInsert;
  return (initialGroup);
}

void group_delete(Group initialGroup)
{
  free(initialGroup->list_vertice);
  free(initialGroup);
}

void group_print(Group initialGroup)
{
  printf("Inside the Group :\n");
  printf("Number of Vertice : %d\n", initialGroup->number_vertice);
  for (int i = 0; i < initialGroup->number_vertice; ++i)
    printf("| %d,%d %c ", vertice_get_abscisse(initialGroup->list_vertice[i]),
                          vertice_get_ordonnee(initialGroup->list_vertice[i]),
                          vertice_get_color(initialGroup->list_vertice[i]));
  printf("|\n");
}

/*
 * A faire :
 * Créer une fonction fusion
 * Créer une fonction de recherche/vérification de gagnant
 */