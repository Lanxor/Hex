#include <stdio.h>
#include <stdlib.h>

#include "../vertice.h"

void test1_create()
{
    printf("test_create test 1\n");
    Vertice vertice;
    
    vertice = vertice_create(TRANSPARENT, 0, 0);
}

void test2_delete()
{
    printf("test_create test 2\n");
    Vertice vertice;
    
    vertice = vertice_create(TRANSPARENT, 0, 0);
    vertice_delete(vertice);
}

void test3_print()
{
    printf("test_print test 3\n");
    Vertice vertice;
    
    vertice = vertice_create(TRANSPARENT, 0, 0);
    vertice_print(vertice);
}

int main() {
    test1_create();
    test2_delete();
    test3_print();

    return (EXIT_SUCCESS);
}
