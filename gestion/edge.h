#ifndef __EDGE_H__
#define __EDGE_H__

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#include "vertice.h"

typedef struct s_edge *Edge;

Edge edge_create(Vertice *first, Vertice *second);

void edge_print(Edge edge);

void edge_get_vertice_first(Edge edge);

void edge_get_vertice_second(Edge edge);

void edge_delete(Edge edge);

void edge_free(Edge edge);

#endif /* EDGE_H */

