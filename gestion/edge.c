
#include "edge.h"

typedef struct s_edge
{
    Vertice *vertice_1;
    Vertice *vertice_2;
} t_edge;

Edge edge_create(Vertice *first, Vertice *second)
{
    Edge edge;
    
    edge = malloc(sizeof(t_edge));
    assert( edge != NULL );
    
    edge->vertice_1 = first;
    edge->vertice_2 = second;
}