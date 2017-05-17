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

char group_color(Group initialGroup)
{
  if (initialGroup != NULL && initialGroup->number_vertice > 0)
    return (vertice_get_color(initialGroup->list_vertice[0]));
  else
    return (TRANSPARENT);
}

Group group_fusion(Group firstGroup, Group secondGroup)
{
  for (int cpt = 0; cpt < secondGroup->number_vertice; ++cpt)
    firstGroup = group_insert(firstGroup, secondGroup->list_vertice[cpt]);
  group_delete(secondGroup);
  return (firstGroup);
}

char group_who_win(Group initialGroup, Deck deck)
{
  Vertice   firstBorder;
  Vertice   secondBorder;
  int       isWinner;
  
  firstBorder = deck_get_border(deck, group_color(initialGroup), 1);
  secondBorder = deck_get_border(deck, group_color(initialGroup), 2);
  isWinner = 0;
  
  if (firstBorder != NULL && secondBorder != NULL)
  {
    for (int cpt = 0; cpt < initialGroup->number_vertice; ++cpt)
      if (initialGroup->list_vertice[cpt] == firstBorder || initialGroup->list_vertice[cpt] == secondBorder )
        ++isWinner;
  } 
  if (isWinner == 2)
    return(group_color(initialGroup));
  else
    return(TRANSPARENT);
}

int group_winner(Group initialGroup, Deck deck)
{
  Vertice   firstBorder;
  Vertice   secondBorder;
  int       isWinner;
  
  firstBorder = deck_get_border(deck, group_color(initialGroup), 1);
  secondBorder = deck_get_border(deck, group_color(initialGroup), 2);
  isWinner = 0;
  
  if (firstBorder != NULL && secondBorder != NULL)
  {
    for (int cpt = 0; cpt < initialGroup->number_vertice; ++cpt)
      if (initialGroup->list_vertice[cpt] == firstBorder || initialGroup->list_vertice[cpt] == secondBorder )
        ++isWinner;
  } 
  if (isWinner == 2)
    return(1);
  else
    return(0);
}



/*
 * A faire :
 * Créer une fonction de création de group a partir d'un deck
 */