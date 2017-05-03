#include <stdio.h>
#include <stdlib.h>

#include "../vertice.h"

void test1_create()
{
    printf("\ttest_create()\n");
    Vertice vertice;
    
    vertice = vertice_create(TRANSPARENT, 0, 0);
}

void test2_delete()
{
    printf("\ttest_delete()\n");
    Vertice vertice;
    
    vertice = vertice_create(TRANSPARENT, 0, 0);
    vertice_delete(vertice);
}

void test3_print()
{
    printf("\ttest_print()\n");
    Vertice vertice;
    
    vertice = vertice_create(TRANSPARENT, 0, 0);
    vertice_print_coordinates(vertice);
    vertice_print_color(vertice);
}

int main() {
    printf("\nTest Simple - TEST VERTICE\n");
    test1_create();
    test2_delete();
    test3_print();
    
    printf("\n");
    return (EXIT_SUCCESS);
}
