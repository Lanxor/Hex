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

int vertice_is_border(Vertice vertice, Deck deck);

void vertice_modify_color(Vertice vertice, char color);

unsigned int vertice_get_abscisse(Vertice vertice);

unsigned int vertice_get_ordonnee(Vertice vertice);

void vertice_print_coordinates(Vertice vertice);

void vertice_print_color(Vertice vertice);

void vertice_delete(Vertice vertice);

void vertice_free(Vertice vertice);

#endif /* VERTICE_H */

