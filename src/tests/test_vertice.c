#include <stdio.h>
#include <stdlib.h>

#include "../../header/vertice.h"

void test1_create()
{
    printf("\ttest_create()\n");
    Vertice vertice;
    
    vertice = vertice_create(TRANSPARENT, 0, 0);
    printf("\n");
}

void test2_delete()
{
    printf("\ttest_delete()\n");
    Vertice vertice;
    
    vertice = vertice_create(TRANSPARENT, 0, 0);
    vertice_delete(vertice);
    printf("\n");
}

void test3_print()
{
    printf("\ttest_print()\n");
    Vertice vertice;
    
    vertice = vertice_create(TRANSPARENT, 0, 0);
    printf("Coordinates : ");
    vertice_print_coordinates(vertice);
    printf("\nColor : ");
    vertice_print_color(vertice);
    printf("\n");
}

void test4_modify_color()
{
    printf("\ttest_modify_color()\n");
    Vertice vertice;
    
    vertice = vertice_create(TRANSPARENT, 0, 0);
    printf("Avant : ");
    vertice_print_color(vertice);
    printf("\n");
    vertice_modify_color(vertice, BLACK);
    printf("Après : ");
    vertice_print_color(vertice);
    printf("\n");
}

void test5_getters()
{
    printf("\ttest_getters()\n");
    Vertice vertice;
    
    vertice = vertice_create(TRANSPARENT, 3, 5);
    printf("Voici les coordonnées du sommet %d, %d",
            vertice_get_abscisse(vertice),
            vertice_get_ordonnee(vertice));
    printf("\n");
}

int main() 
{
    printf("\nTest Simple - TEST VERTICE\n");
    test1_create();
    test2_delete();
    test3_print();
    test4_modify_color();
    test5_getters();
    
    printf("\n");
    return (EXIT_SUCCESS);
}
