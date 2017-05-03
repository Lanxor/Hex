#ifndef VERTICE_H
#define VERTICE_H

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#define TRANSPARENT 't'
#define BLACK 'b'
#define WHITE 'w'

typedef struct s_vertice *Vertice;

Vertice vertice_create(char color, unsigned int abscisse,
                                   unsigned int ordonnee);

#endif /* VERTICE_H */

