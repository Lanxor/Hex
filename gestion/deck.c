
#include "deck.h"

typedef struct s_deck
{
    unsigned int size;
    Vertice *white_1;
    Vertice *white_2;
    Vertice *black_1;
    Vertice *black_2;
    Vertice **set_vertices;
    Edge **set_edges;
} t_deck;

Deck deck_create(unsigned int size)
{
    Deck deck;
    Vertice new_vertice;
    
    deck = malloc(sizeof(t_deck));
    assert( deck != NULL );
    
    deck->size = size;
    deck->white_1 = vertice_create(TRANSPARENT, -1, -1);
    deck->white_2 = vertice_create(TRANSPARENT, -1, -1);
    deck->black_1 = vertice_create(TRANSPARENT, -1, -1);
    deck->black_2 = vertice_create(TRANSPARENT, -1, -1);
    
    deck->set_vertices = malloc(size*size*sizeof(*t_vertice));
    
    for(int abscisse = 0; abscisse < size; abscisse++)
    {
        for(int ordonnee = 0; ordonnee < size; ordonnee++)
        {
            new_vertice = vertice_create(TRANSPARENT, abscisse, ordonnee);
            (deck->set_vertices+(size*abscisse)+ordonnee) = new_vertice;
        }
    }
    
}