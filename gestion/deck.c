
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
    int abscisse, ordonnee, number_edges, counter_edges;
    Deck deck;
    Vertice new_vertice, vertice_current, vertice_target;
    Edge new_edge;
    
    deck = malloc(sizeof(t_deck));
    assert( deck != NULL );
    
    deck->size = size;
    deck->white_1 = vertice_create(TRANSPARENT, -1, -1);
    deck->white_2 = vertice_create(TRANSPARENT, -1, -1);
    deck->black_1 = vertice_create(TRANSPARENT, -1, -1);
    deck->black_2 = vertice_create(TRANSPARENT, -1, -1);
    
    deck->set_vertices = malloc(size*size*sizeof(Vertice));
    // (4*size)+((3*size)*(size-2))+(size*2+1) est le nombre d'arête (sans compter les doublons)
    number_edges = (4*size)+((3*size)*(size-2))+(size*2+1);
    deck->set_edges = malloc( (number_edges)*sizeof(Edge) );
    
    for(abscisse = 0; abscisse < size; abscisse++)
    {
        for(ordonnee = 0; ordonnee < size; ordonnee++)
        {
            new_vertice = vertice_create(TRANSPARENT, abscisse, ordonnee);
            deck->set_vertices[(deck->size*abscisse)+ordonnee] = new_vertice;
            if ( abscisse == 0 )
            {
                new_edge = edge_create(&deck->black_1, &new_vertice);
            }
            else if ( abscisse == deck->size )
            {
                new_edge = edge_create(&deck->black_2, &new_vertice);
            }
            if ( ordonnee == 0 )
            {
                new_edge = edge_create(&deck->white_1, &new_vertice);
            }
            else if ( ordonnee == deck->size )
            {
                new_edge = edge_create(&deck->white_2, &new_vertice);
            }
        }
    }
    
    counter_edges = 0;
    
    for(abscisse = 0; abscisse < size; abscisse++)
    {
        for(ordonnee = 0; ordonnee < size; ordonnee++)
        {
            vertice_current;
            vertice_target;
            // b1 jamais
            // b2 jamais
                // sauf si abscisse == 0 -> on le met à black
            if ( abscisse == 0 )
            {
                vertice_target = deck->black_1;
                new_edge = edge_create(&vertice_current, &vertice_target);
                deck->set_edges[counter_edges++] = new_edge;
            }
            
            // b3 tout le temps 
                // si ordonnee+1 == deck->size -> on le met à white
            if ( ordonnee+1 == deck->size )
            {
                vertice_target = deck->white_2;
            }
            else
            {
                vertice_target = deck_get_vertice(deck, abscisse, ordonnee+1);
            }
            new_edge = edge_create(&vertice_current, &vertice_target);
            deck->set_edges[counter_edges++] = new_edge;
            
            // b4 tout le temps
                // si abscisse+1 == deck->size -> on le met à black
            if ( abscisse+1 == deck->size )
            {
                vertice_target = deck->black_2;
            }
            else
            {
                vertice_target = deck_get_vertice(deck, abscisse+1, ordonnee);
            }
            new_edge = edge_create(&vertice_current, &vertice_target);
            deck->set_edges[counter_edges++] = new_edge;
            
            // b5 tout le temps
                // sauf sur la dernière ligne mais pas la première de la dernière ligne
                // sauf abscisse != 0 && ordonnee == deck->size-1
            if ( !(abscisse != 0 && ordonnee == deck->size-1) )
            {
                vertice_target = deck_get_vertice(deck, abscisse+1, ordonnee-1);
                new_edge = edge_create(&vertice_current, &vertice_target);
                deck->set_edges[counter_edges++] = new_edge;
            }
            // b6 jamais
        }
    }
    
    assert( (counter_edges) == number_edges );
    
    return deck;
}

Vertice deck_get_vertice(Deck deck, unsigned int abscisse,
                                    unsigned int ordonnee)
{
    int counter_vertice, number_vertice;
    Vertice vertice_current;
    
    number_vertice = deck->size * deck->size;
    counter_vertice = 0;
    while (vertice_get_abscisse(vertice_current) != abscisse &&
           vertice_get_ordonnee(vertice_current) != ordonnee &&
           counter_vertice < number_vertice)
    {
        vertice_current = deck->set_vertices[counter_vertice++];
    }
    
    return vertice_current;
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