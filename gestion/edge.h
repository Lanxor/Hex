#ifndef EDGE_H
#define EDGE_H

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#include "vertice.h"

typedef struct s_edge *Edge;

Edge edge_create(Vertice *first, Vertice *second);

#endif /* EDGE_H */

