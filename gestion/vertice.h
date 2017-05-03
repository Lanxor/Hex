#ifndef __VERTICE_H__
#define __VERTICE_H__

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#define TRANSPARENT 't'
#define BLACK 'b'
#define WHITE 'w'

typedef struct s_vertice *Vertice;

Vertice vertice_create(char color, unsigned int abscisse,
                                   unsigned int ordonnee);

void vertice_print(Vertice vertice);

void vertice_delete(Vertice vertice);

void vertice_free(Vertice vertice);

#endif /* VERTICE_H */

