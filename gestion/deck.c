
#include "deck.h"

typedef struct s_deck
{
    unsigned int size;
    Vertice white_1;
    Vertice white_2;
    Vertice black_1;
    Vertice black_2;
    Vertice *set_vertices;
    Edge *set_edges;
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
    
    deck->set_vertices = malloc(size*size*sizeof(Vertice));
    
    for(int abscisse = 0; abscisse < size; abscisse++)
    {
        for(int ordonnee = 0; ordonnee < size; ordonnee++)
        {
            new_vertice = vertice_create(TRANSPARENT, abscisse, ordonnee);
            deck->set_vertices[(deck->size*abscisse)+ordonnee] = new_vertice;
        }
    }
    return deck;
}
void deck_print(Deck deck)
{
    deck_print_coordinates(deck);
    printf("\n");
    deck_print_color(deck);
}

void deck_print_coordinates(Deck deck)
{
    printf("Deck size : %u\n", deck->size);
    for(int abscisse = 0; abscisse < deck->size; abscisse++)
    {
        for(int ordonnee = 0; ordonnee < deck->size; ordonnee++)
        {
            vertice_print_coordinates(deck->set_vertices[(deck->size*abscisse)+ordonnee]);
            printf(" ");
        }
        printf("\n");
    }
}

void deck_print_color(Deck deck)
{
    printf("Deck size : %u\n", deck->size);
    for(int abscisse = 0; abscisse < deck->size; abscisse++)
    {
        for(int ordonnee = 0; ordonnee < deck->size; ordonnee++)
        {
            vertice_print_color(deck->set_vertices[(deck->size*abscisse)+ordonnee]);
            printf(" ");
        }
        printf("\n");
    }
}

void deck_delete(Deck deck)
{
    vertice_delete(deck->white_1);
    vertice_delete(deck->white_2);
    vertice_delete(deck->black_1);
    vertice_delete(deck->black_2);
    for(int abscisse = 0; abscisse < deck->size; abscisse++)
    {
        for(int ordonnee = 0; ordonnee < deck->size; ordonnee++)
        {
            vertice_delete(deck->set_vertices[(deck->size*abscisse)+ordonnee]);
        }
    }
    deck_free(deck);
}

void deck_free(Deck deck)
{
    free(deck->set_vertices);
    free(deck->set_edges);
    free(deck);
}