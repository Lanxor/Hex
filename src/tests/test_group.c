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
  printf("test_group test 3\n");
  Group initialGroup;
  Vertice vertice;

  vertice = vertice_create(TRANSPARENT, 0, 0);
  initialGroup = group_create();
  group_print(initialGroup);
  initialGroup = group_insert(initialGroup, vertice);
  group_print(initialGroup);

  vertice_delete(vertice);
  group_delete(initialGroup);
}

void test4_color()
{
  printf("test_group test 4\n");
  Group initialGroup;
  Vertice vertice;

  vertice = vertice_create(BLACK, 0, 0);
  initialGroup = group_create();
  printf("%c, ", group_color(initialGroup));
  initialGroup = group_insert(initialGroup, vertice);
  printf("%c\n", group_color(initialGroup));

  vertice_delete(vertice);
  group_delete(initialGroup);
}

void test5_fusion()
{
  printf("test_group test 5\n");
  Group   initialGroup;
  Group   otherGroup;
  Vertice first;
  Vertice second;
  Vertice third;

  first = vertice_create(TRANSPARENT, 0, 0);
  second = vertice_create(TRANSPARENT, 1, 1);
  third = vertice_create(TRANSPARENT, 2, 2);
  initialGroup = group_create();
  otherGroup = group_create();

  initialGroup = group_insert(initialGroup, first);
  initialGroup = group_insert(initialGroup, second);
  otherGroup = group_insert(otherGroup, third);
  group_print(initialGroup);
  group_print(otherGroup);

  group_fusion(initialGroup, otherGroup);
  printf("After Fusion :\n");
  group_print(initialGroup);

  group_delete(initialGroup);
  vertice_delete(first);
  vertice_delete(second);
  vertice_delete(third);
  
}

int main() 
{
    printf("\nTest Simple - TEST GROUP\n");
    test1_create();
    test2_delete();
    test3_insert();
    test4_color();
    test5_fusion();

    return (EXIT_SUCCESS);
}
