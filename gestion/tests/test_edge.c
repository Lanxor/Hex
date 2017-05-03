#include <stdio.h>
#include <stdlib.h>

#include "vertice.h"
#include "edge.h"

void test1_create()
{
    printf("\ttest1_create()\n");
    Edge edge;
    Vertice vertice1, vertice2;
    
    vertice1 = vertice_create(TRANSPARENT, 0, 0);
    vertice2 = vertice_create(TRANSPARENT, 0, 0);
    
    edge = edge_create(&vertice1, &vertice2);
    printf("\n");
}

void test2_delete()
{
    printf("\ttest2_delete()\n");
    Edge edge;
    Vertice vertice1, vertice2;
    
    vertice1 = vertice_create(TRANSPARENT, 0, 0);
    vertice2 = vertice_create(TRANSPARENT, 0, 0);
    
    edge = edge_create(&vertice1, &vertice2);
    edge_delete(edge);
    printf("\n");
}

void test3_print()
{
    printf("\ttest3_print()\n");
    Edge edge;
    Vertice vertice1, vertice2;
    
    vertice1 = vertice_create(TRANSPARENT, 1, 2);
    vertice2 = vertice_create(TRANSPARENT, 1, 3);
    
    edge = edge_create(&vertice1, &vertice2);
    edge_print(edge);
    printf("\n");
}

int main() {
    printf("\nTest Simple - TEST EDGE\n");
    test1_create();
    test2_delete();
    test3_print();

    printf("\n");
    return (EXIT_SUCCESS);
}
