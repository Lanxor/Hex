#include <stdio.h>
#include <stdlib.h>

#include "../../header/vertice.h"

void test1_create()
{
    printf("test_create test 1\n");
    Vertice vertice;
    
    if ( (vertice = vertice_create(TRANSPARENT, 0, 0)) != NULL)
      printf("OK\n");
    else
      printf("KO\n");
}

void test2_delete()
{
    printf("test_create test 2\n");
    Vertice vertice;
    
    vertice = vertice_create(TRANSPARENT, 0, 0);
    vertice_delete(vertice);
}

int main() {
    test1_create();
    test2_delete();

    return (EXIT_SUCCESS);
}
