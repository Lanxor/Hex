#include "../header/listGroup.h"

typedef struct s_node
{
  Group         group;
  struct s_node *next;
  struct s_node *previous;
} t_node;

typedef struct s_ldg
{
  Node    sentinel;
  int     numberOfNode;
} t_ldg;

LDG listGroup_create()
{
  LDG ldg;
  ldg = (LDG) malloc(sizeof(t_ldg));
  assert(ldg != NULL);
  ldg->numberOfNode = 0;
  ldg->sentinel = (Node) malloc(sizeof(t_node));
  ldg->sentinel->next = ldg->sentinel;
  ldg->sentinel->previous = ldg->sentinel;
  ldg->sentinel->group = NULL;
  return (ldg);
}

Node linkThreeNode(Node leftNode, Node midNode, Node rightNode)
{
  leftNode->next = midNode;
  midNode->previous = leftNode;
  midNode->next = rightNode;
  rightNode->previous = midNode;
  return (midNode);
}

LDG listGroup_append(LDG ldg, Group groupToInsert)
{
  assert(ldg != NULL);
  Node    nodeToInsert;
  Node    currentNode;
  nodeToInsert = (Node) malloc(sizeof(t_node));
  nodeToInsert->group = groupToInsert;
  currentNode = ldg->sentinel;
  for (int cpt = 0; cpt < ldg->numberOfNode; ++cpt)
    currentNode = currentNode->next;
  linkThreeNode(currentNode, nodeToInsert, currentNode->next);
  ++ldg->numberOfNode;
  printf("number : %d\n", ldg->numberOfNode);
  return (ldg);
}

LDG listGroup_remove(LDG ldg, Group groupToRemove)
{
  assert(ldg != NULL);
  int     cpt;
  Node    currentNode;
  cpt = 0;
  currentNode = ldg->sentinel;
  while (cpt < ldg->numberOfNode || currentNode->group != groupToRemove)
  {
    currentNode = currentNode->next;
    ++cpt;
  }
  currentNode->previous->next = currentNode->next;
  currentNode->next->previous = currentNode->previous;
  free(currentNode->group);
  free(currentNode);
  --ldg->numberOfNode;
  printf("number : %d\n", ldg->numberOfNode);
  return (ldg);
}

void listGroup_delete(LDG ldg)
{
  assert(ldg != NULL);
  Node   currentNode;
  currentNode = ldg->sentinel;
  for (int cpt = 0; cpt < ldg->numberOfNode; ++cpt)
  {
    currentNode = currentNode->next;
    free(currentNode->previous);
  }
  free(ldg);
}

Node ldg_get_sentinel(LDG ldg)
{
  return (ldg->sentinel);
}

Node ldg_get_next(Node currentNode)
{
  return (currentNode->next);
}

Group ldg_get_group(Node currentNode)
{
  return (currentNode->group);
}

int ldg_get_number(LDG ldg)
{
  return (ldg->numberOfNode);
}