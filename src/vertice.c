#include "../header/vertice.h"

typedef struct    s_vertice
{
    char          color;
    int           abscisse;
    int           ordonnee;
} t_vertice;

Vertice vertice_create(char color, int abscisse,
                                   int ordonnee)
{
    Vertice vertice;
    
    vertice = malloc(sizeof(t_vertice));
    assert( vertice != NULL );
    
    vertice->color = color;
    vertice->abscisse = abscisse;
    vertice->ordonnee = ordonnee;
}

void vertice_modify_color(Vertice vertice, char color)
{
    if (color == TRANSPARENT || color == BLACK || color == WHITE )
    {
        vertice->color = color;
    }
    else
    {
        vertice->color = TRANSPARENT;
    }
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

void vertice_set_color(Vertice vertice , char color)
{
  vertice->color = color;
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
