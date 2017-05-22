#include "../header/deck.h"

typedef struct s_deck
{
  int           size;
  Vertice       white_1;
  Vertice       white_2;
  Vertice       black_1;
  Vertice       black_2;
  Vertice       *set_vertices;
  Edge          *set_edges;
  LDG           set_groups;
} t_deck;

Deck deck_initialize_set(Deck deck)
{
  int   number_edges;
  
  deck->set_vertices = (Vertice*) malloc((deck->size * deck->size) * sizeof(Vertice));
  assert( deck->set_vertices != NULL );
  number_edges = deck_get_number_edge(deck->size);
  deck->set_edges = (Edge*) malloc((number_edges) * sizeof(Edge));
  assert( deck->set_edges != NULL );
  deck->set_groups = listGroup_create();
  return (deck);
}

Deck deck_insert_group(Deck deck, Vertice vertice)
{
  Group   initialGroup;
  
  initialGroup = group_create();
  initialGroup = group_insert(initialGroup, vertice);
  deck->set_groups = listGroup_append(deck->set_groups, initialGroup);
  return (deck);
}

Deck deck_initialize_border(Deck deck)
{
  deck->white_1 = vertice_create(WHITE, -1, -1);
  deck->white_2 = vertice_create(WHITE, -2, -2);
  deck->black_1 = vertice_create(BLACK, -3, -3);
  deck->black_2 = vertice_create(BLACK, -4, -4);
  return (deck);
}

Deck deck_border_insert(Deck deck)
{
  deck_insert_group(deck, deck->white_1);
  deck_insert_group(deck, deck->white_2);
  deck_insert_group(deck, deck->black_1);
  deck_insert_group(deck, deck->black_2);
  return (deck);
}

Deck deck_create_edge(Deck deck)
{
  int     counter_edges;
  Vertice new_vertice, vertice_current, vertice_target;
  Edge    new_edge;
  
  counter_edges = 0;
  for(int abscisse = 0; abscisse < deck->size; ++abscisse)
    {
      for(int ordonnee = 0; ordonnee < deck->size; ++ordonnee)
        {
          vertice_current = NULL;
          vertice_current = deck_get_vertice(deck, abscisse, ordonnee);
          vertice_target = NULL;

          if ( abscisse == 0 )
            {
              vertice_target = deck->black_1;
              new_edge = edge_create(vertice_current, vertice_target);
              deck->set_edges[counter_edges++] = new_edge;
            }

          if ( ordonnee + 1 == deck->size )
            vertice_target = deck->white_2;
          else
            vertice_target = deck_get_vertice(deck, abscisse, ordonnee + 1);  
          new_edge = edge_create(vertice_current, vertice_target);
          deck->set_edges[counter_edges++] = new_edge;

          if ( abscisse + 1 == deck->size )
            vertice_target = deck->black_2;
          else
            vertice_target = deck_get_vertice(deck, abscisse + 1, ordonnee);

          new_edge = edge_create(vertice_current, vertice_target);
          deck->set_edges[counter_edges++] = new_edge;

          if ( abscisse != deck->size - 1 )
            {
              if ( ordonnee == 0 )
          vertice_target = deck->white_1;
              else
          vertice_target = deck_get_vertice(deck, abscisse + 1, ordonnee - 1);
              new_edge = edge_create(vertice_current, vertice_target);
              deck->set_edges[counter_edges++] = new_edge;
            }

          if ( abscisse == deck->size - 1 && ordonnee == 0 )
            {
              vertice_target = deck->white_1;
              new_edge = edge_create(vertice_current, vertice_target);
              deck->set_edges[counter_edges++] = new_edge;
            }
        }
    }
  return(deck);
}

Deck deck_create_vertice(Deck deck)
{
  Vertice new_vertice;
  
  for (int abscisse = 0; abscisse < deck->size; ++abscisse)
    {
      for (int ordonnee = 0; ordonnee < deck->size; ++ordonnee)
        {
          new_vertice = vertice_create(TRANSPARENT, abscisse, ordonnee);
          deck->set_vertices[(deck->size * abscisse) + ordonnee] = new_vertice;
        }
    }
  return (deck);
}

Deck deck_create(int size)
{
  Deck    deck;

  deck = (Deck) malloc(sizeof(t_deck));
  assert( deck != NULL );
  deck->size = size;
  deck = deck_initialize_set(deck);
  deck = deck_initialize_border(deck);
  deck = deck_border_insert(deck);
  deck = deck_create_vertice(deck);
  deck = deck_create_edge(deck);

  return (deck);
}

Vertice deck_get_vertice(Deck deck, int abscisse,
			 int ordonnee)
{
  return (deck->set_vertices[(deck->size * abscisse) + ordonnee]);
}

int vertice_is_border(Vertice vertice, Deck deck)
{
  if ( vertice == deck_get_border(deck, WHITE, 1) )
    return (1);
  else if ( vertice == deck_get_border(deck, WHITE, 2) )
    return (1);
  else if ( vertice == deck_get_border(deck, BLACK, 1) )
    return (1);
  else if ( vertice == deck_get_border(deck, BLACK, 2) )
    return (1);
  else
    return (0);
}

void deck_print(Deck deck)
{
  deck_print_coordinates(deck);
  printf("\n");
  deck_print_color(deck);
  printf("\n");
  deck_print_edge(deck);
}

void deck_print_coordinates(Deck deck)
{
  printf("Deck size : %d\n", deck->size);
  for(int abscisse = 0; abscisse < deck->size; ++abscisse)
    {
      for(int ordonnee = 0; ordonnee < deck->size; ++ordonnee)
        {
          vertice_print_coordinates(deck_get_vertice(deck, abscisse, ordonnee));
          printf(" ");
        }
      printf("\n");
    }
}

void deck_print_color(Deck deck)
{
  printf("Deck size : %d\n", deck->size);
  for(int abscisse = 0; abscisse < deck->size; ++abscisse)
    {
      for(int ordonnee = 0; ordonnee < deck->size; ++ordonnee)
        {
          vertice_print_color(deck_get_vertice(deck, abscisse, ordonnee));
          printf(" ");
        }
      printf("\n");
    }
}

void deck_print_edge(Deck deck)
{
  int  number_edge, counter_edge;

  number_edge = deck_get_number_edge(deck->size);
  for(counter_edge = 0; counter_edge < number_edge; ++counter_edge)
    {
      printf("%d : ", counter_edge);
      edge_print(deck->set_edges[counter_edge]);
      printf("\n");
    }
}

Vertice deck_get_border(Deck deck, char color, int number)
{
  if ( color == WHITE )
    {
      if ( number == 1 )
	return deck->white_1;
      else if ( number == 2 )
	return deck->white_2;
      else
	return (NULL);
    }
  else if ( color == BLACK )
    {
      if ( number == 1 )
	return deck->black_1;
      else if ( number == 2 )
	return deck->black_2;
      else
	return (NULL);
    }
  else
    return (NULL);
}

int deck_get_number_edge( int size)
{
  return ((4 * size) + ((3 * size) * (size - 2)) + (size * 2 + 1));
}

int deck_get_size(Deck deck)
{
  return (deck->size);
}

void deck_delete(Deck deck)
{
  vertice_delete(deck->white_1);
  vertice_delete(deck->white_2);
  vertice_delete(deck->black_1);
  vertice_delete(deck->black_2);
  for(int abscisse = 0; abscisse < deck->size; ++abscisse)
    for(int ordonnee = 0; ordonnee < deck->size; ++ordonnee)
      vertice_delete(deck_get_vertice(deck, abscisse, ordonnee));
  deck_free(deck);
}

void deck_free(Deck deck)
{
  free(deck->set_vertices);
  free(deck->set_edges);
  free(deck);
}

int deck_vertice_modify_is_possible(Deck deck, char color, int abscisse, int ordonnee)
{
  if ((color == TRANSPARENT) 
      || (vertice_get_color(deck_get_vertice(deck, abscisse, ordonnee)) == TRANSPARENT))
    return (1);
  else
    return (0);
}

Deck deck_group_reevaluation(Deck deck)
{
  Vertice vertice;
  char    color;
  
  listGroup_delete(deck->set_groups);
  deck->set_groups = listGroup_create();
  deck = deck_border_insert(deck);
  for(int abscisse = 0; abscisse < deck->size; ++abscisse)
    for(int ordonnee = 0; ordonnee < deck->size; ++ordonnee)
      {
        vertice = deck_get_vertice(deck, abscisse, ordonnee);
        color = vertice_get_color(vertice);
        if (color != TRANSPARENT)
          deck_vertice_modify(deck, color, abscisse, ordonnee);
      }
  return (deck);
}

void deck_vertice_modify(Deck deck, char color, int abscisse, int ordonnee)
{
  Group initialGroup;
  vertice_set_color(deck_get_vertice(deck, abscisse, ordonnee), color);
  if (color != TRANSPARENT)
    {
      initialGroup = group_create();
      initialGroup = group_insert(initialGroup, deck_get_vertice(deck, abscisse, ordonnee));
      deck->set_groups = listGroup_append(deck->set_groups, initialGroup);
      deck = deck_update_ldg(deck, initialGroup);
    }
  else
    deck = deck_group_reevaluation(deck);
}


Deck deck_update_ldg(Deck deck, Group currentGroup)
{
  int     groupSize, numberOfEdge, cptOtherGroup, isFind;
  Node    otherNode;
  Vertice currentVertice, verticeToSearch;
  Edge    currentEdge;
  LDG     ldg;
  
  ldg = deck->set_groups;
  numberOfEdge = deck_get_number_edge(deck->size);
  groupSize = group_get_number(currentGroup);
  for (int cptVertice = 0; cptVertice < groupSize; ++cptVertice)
    {
      currentVertice = group_get_vertice(currentGroup, cptVertice);
      for (int cptEdge = 0; cptEdge < numberOfEdge; ++cptEdge)
        {
          currentEdge = deck->set_edges[cptEdge];
          if (currentVertice == edge_get_vertice_first(currentEdge)
              || currentVertice == edge_get_vertice_second(currentEdge))
            {
              if (currentVertice == edge_get_vertice_first(currentEdge))
                verticeToSearch = edge_get_vertice_second(currentEdge);
              else
                verticeToSearch = edge_get_vertice_first(currentEdge);
              isFind = 0;
              cptOtherGroup = 0;
              otherNode = ldg_get_sentinel(ldg);
              while (!isFind && cptOtherGroup < ldg_get_number(ldg))
                {
                  otherNode = ldg_get_next(otherNode);
                  isFind = group_search_vertice(ldg_get_group(otherNode), verticeToSearch);
                  ++cptOtherGroup;     
                }
              if (isFind && group_color(currentGroup) == group_color(ldg_get_group(otherNode)))
                {
                  if (ldg_get_group(otherNode) != currentGroup)
                    {
                      currentGroup = group_fusion(currentGroup, ldg_get_group(otherNode));
                      ldg = listGroup_remove(ldg, ldg_get_group(otherNode));
                    }
                }
            }
        }
    }
  return (deck);
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
      for (int cpt = 0; cpt < group_get_number(initialGroup); ++cpt)
        if (group_get_vertice(initialGroup, cpt) == firstBorder 
            || group_get_vertice(initialGroup, cpt) == secondBorder )
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
  for (int cpt = 0; cpt < group_get_number(initialGroup); ++cpt)
    if (group_get_vertice(initialGroup, cpt) == firstBorder 
        || group_get_vertice(initialGroup, cpt) == secondBorder )
      ++isWinner;
  if (isWinner == 2)
    return(1);
  else
    return(0);
}

char deck_know_winner(Deck deck)
{
  Node  otherNode;
  int   cptOtherGroup = 0;
  char  colorWin;
  
  colorWin = TRANSPARENT;
  otherNode = ldg_get_sentinel(deck->set_groups);
  while (cptOtherGroup < ldg_get_number(deck->set_groups))
    {
      otherNode = ldg_get_next(otherNode);
      if (group_winner(ldg_get_group(otherNode), deck))
        colorWin = group_who_win(ldg_get_group(otherNode), deck);
      ++cptOtherGroup;
    }
  return (colorWin);
}

int deck_has_winner(Deck deck)
{
  if (deck_know_winner(deck) != TRANSPARENT)
    return (1);
  else
    return(0);
}

LDG deck_get_ldg(Deck deck)
{
  return ((LDG) deck->set_groups);
}

