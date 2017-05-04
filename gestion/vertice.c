
#include "vertice.h"

typedef struct s_vertice
{
    char color;
    unsigned int abscisse;
    unsigned int ordonnee;
} t_vertice;

Vertice vertice_create(char color, unsigned int abscisse,
                                   unsigned int ordonnee)
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

unsigned int vertice_get_abscisse(Vertice vertice)
{
    return vertice->abscisse;
}

unsigned int vertice_get_ordonnee(Vertice vertice)
{
    return vertice->ordonnee;
}

void vertice_print_coordinates(Vertice vertice)
{
    // printf - %u unsigned int
    printf("%u,%u", vertice->abscisse, vertice->ordonnee);
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