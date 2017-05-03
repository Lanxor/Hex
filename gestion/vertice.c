
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

void vertice_print(Vertice vertice)
{
    printf("%d,%d", vertice->abscisse, vertice->ordonnee);
}

void vertice_delete(Vertice vertice)
{
    vertice_free(vertice);
}

void vertice_free(Vertice vertice)
{
    free(vertice);
}