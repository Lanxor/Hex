#include "../header/vertice.h"

typedef struct    s_vertice
{
    char          color;
    int           abscisse;
    int           ordonnee;
    Group         group;
} t_vertice;

Vertice vertice_create(char color, int abscisse,
                                   int ordonnee)
{
    Vertice vertice;
    
    vertice = (Vertice) malloc(sizeof(t_vertice));
    assert( vertice != NULL );
    
    vertice->color = color;
    vertice->abscisse = abscisse;
    vertice->ordonnee = ordonnee;
    
    vertice->group = group_create();
    vertice->group = group_insert(vertice_group, vertice);

    return vertice;
}

void vertice_modify_color(Vertice vertice, char color)
{
  if (color == TRANSPARENT || color == BLACK || color == WHITE )
    vertice->color = color;
  else
    vertice->color = TRANSPARENT;
}

int vertice_get_abscisse(Vertice vertice)
{
    return vertice->abscisse;
}

int vertice_get_ordonnee(Vertice vertice)
{
    return vertice->ordonnee;
}

char vertice_get_color(Vertice vertice)
{
  return (vertice->color);
}

Group vertice_get_group(Vertice vertice)
{
  return (vertice->group);
}

void vertice_set_color(Vertice vertice , char color)
{
  vertice->color = color;
}

void vertice_set_group(Vertice vertice, Group group)
{
  vertice->group = group;
}

void vertice_print_coordinates(Vertice vertice)
{
    // printf - %u int
    printf("%d,%d", vertice->abscisse, vertice->ordonnee);
}

void vertice_print_color(Vertice vertice)
{
    printf("%c", vertice->color);
}

void vertice_delete(Vertice vertice)
{
    vertice_free(vertice);
}

void vertice_free(Vertice vertice)
{
    free(vertice);
}

Vertice vertice_update_group(Vertice vertice, Deck deck)
{
  for (int cpt = 0; cpt < deck_get_number_edge(deck->size); ++cpt)
  {
    if (edge_get_vertice_first(deck->set_edges[cpt]) == vertice)
    {
      if (vertice_get_color(edge_get_vertice_second(deck->set_edges[cpt]))
              == vertice_get_color(vertice) 
              && vertice_get_color(vertice) != TRANSPARENT)
      {
        vertice_set_group(vertice, 
                          group_fusion(vertice_get_group(edge_get_vertice_second(deck->set_edges[cpt])), 
                                                                                 vertice->group));
      }
    }
    else if (edge_get_vertice_second(deck->set_edges[cpt]) == vertice)
    {
      if (vertice_get_color(edge_get_vertice_first(deck->set_edges[cpt]))
              == vertice_get_color(vertice) 
              && vertice_get_color(vertice) != TRANSPARENT)
      {
        vertice_set_group(vertice, 
                          group_fusion(vertice_get_group(edge_get_vertice_first(deck->set_edges[cpt])), 
                                                                                 vertice->group));
      }
    }
  }
  return (vertice);
}
