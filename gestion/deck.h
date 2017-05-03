#ifndef __DECK_H__
#define __DECK_H__

#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#include "edge.h"
#include "vertice.h"

typedef struct s_deck* Deck;

Deck deck_create(unsigned int size);

#endif
