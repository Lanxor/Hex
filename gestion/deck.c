
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
    deck->white_1 = vertice_create(WHITE, 0, 0);
    deck->white_2 = vertice_create(WHITE, 0, 0);
    deck->black_1 = vertice_create(BLACK, 0, 0);
    deck->black_2 = vertice_create(BLACK, 0, 0);
    
    deck->set_vertices = malloc(size*size*sizeof(Vertice));
    // (4*size)+((3*size)*(size-2))+(size*2+1) est le nombre d'arête (sans compter les doublons)
    number_edges = deck_get_number_edge(size);
    deck->set_edges = malloc( (number_edges)*sizeof(Edge) );
    
    for(abscisse = 0; abscisse < size; abscisse++)
    {
        for(ordonnee = 0; ordonnee < size; ordonnee++)
        {
            new_vertice = vertice_create(TRANSPARENT, abscisse, ordonnee);
            deck->set_vertices[(deck->size*abscisse)+ordonnee] = new_vertice;
        }
    }
    
    counter_edges = 0;
    
    for(abscisse = 0; abscisse < size; abscisse++)
    {
        for(ordonnee = 0; ordonnee < size; ordonnee++)
        {
            vertice_current = NULL;
            vertice_current = deck_get_vertice(deck, abscisse, ordonnee);
            
            vertice_target = NULL;
            // b1 jamais
            
            vertice_target = NULL;
            // b2 jamais
                // sauf si abscisse == 0 -> on le met à black
            if ( abscisse == 0 )
            {
                vertice_target = deck->black_1;
                
                new_edge = edge_create(vertice_current, vertice_target);
                deck->set_edges[counter_edges++] = new_edge;
            }
            
            vertice_target = NULL;
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
                
            new_edge = edge_create(vertice_current, vertice_target);
            deck->set_edges[counter_edges++] = new_edge;
            
            vertice_target = NULL;
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
                
            new_edge = edge_create(vertice_current, vertice_target);
            deck->set_edges[counter_edges++] = new_edge;
            
            vertice_target = NULL;
            // b5 tout le temps
                // sauf sur la dernière ligne mais pas la première de la dernière ligne
                // sauf abscisse != 0 && ordonnee == deck->size-1
            if ( !(abscisse != 0 && ordonnee == deck->size-1) )
            {
                vertice_target = deck_get_vertice(deck, abscisse+1, ordonnee-1);
                
                new_edge = edge_create(vertice_current, vertice_target);
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
    while ( (vertice_get_abscisse(vertice_current) != abscisse ||
           vertice_get_ordonnee(vertice_current) != ordonnee) &&
           counter_vertice < number_vertice)
    {
        vertice_current = deck->set_vertices[counter_vertice++];
    }
    
    return vertice_current;
}

int vertice_is_border(Vertice vertice, Deck deck)
{
    if ( vertice == deck_get_border(deck, WHITE, 1) )
    {
        return 1;
    }
    else if ( vertice == deck_get_border(deck, WHITE, 2) )
    {
        return 1;
    }
    else if ( vertice == deck_get_border(deck, BLACK, 1) )
    {
        return 1;
    }
    else if ( vertice == deck_get_border(deck, BLACK, 2) )
    {
        return 1;
    }
    else
    {
        return 0;
    }
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

void deck_print_edge(Deck deck)
{
    unsigned int number_edge, counter_edge;
    
    number_edge = deck_get_number_edge(deck->size);
    for(counter_edge = 0; counter_edge < number_edge; counter_edge++)
    {
        printf("%u : ", counter_edge);
        edge_print(deck->set_edges[counter_edge]);
        printf("\n");
    }
}

Vertice deck_get_border(Deck deck, char color, int number)
{
    if ( color == BLACK )
    {
        if ( number == 1 )
        {
            return deck->white_1;
        }
        else if ( number == 2 )
        {
            return deck->white_2;
        }
        else
        {
            return NULL;
        }
    }
    else if ( color == WHITE )
    {
        if ( number == 1 )
        {
            return deck->black_1;
        }
        else if ( number == 2 )
        {
            return deck->black_2;
        }
        else
        {
            return NULL;
        }
    }
    else
    {
        return NULL;
    }
}

unsigned int deck_get_number_edge(unsigned int size)
{
    return (4*size)+((3*size)*(size-2))+(size*2+1);
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