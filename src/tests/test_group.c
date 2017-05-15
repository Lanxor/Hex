#include <stdio.h>
#include <stdlib.h>

#include "../../header/group.h"

void test1_create()
{
    printf("test_group test 1\n");
    Group initialGroup;
    
    initialGroup = group_create();
}

void test2_delete()
{
    printf("test_group test 2\n");
    Group initialGroup;
    
    initialGroup = group_create();
    group_delete(initialGroup);
}

void test3_insert()
{
    Group initialGroup;
    Vertice vertice;
    
    vertice = vertice_create(TRANSPARENT, 0, 0);
    initialGroup = group_create();
    group_print(initialGroup);
    initialGroup = group_insert(initialGroup, vertice);
    group_print(initialGroup);
}

int main() {
    test1_create();
    test2_delete();
    test3_insert();

    return (EXIT_SUCCESS);
}
