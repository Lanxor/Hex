TARGET=test_deck

SOURCES=($test_deck).c

OBJECTS=$(SOURCES:.c=.o)

CC=gcc
CFLAGS=-g -std=c99 -Wextra -Wall -pedentic-errors -Werror

$(TARGET) : $(OBJECTS)
	$(CC) $(OBJECTS) -o $@

.c.o :
	$(CC) $(CFLAGS) -c $<

clean :
	rm -f $(TARGET) $(OBJECTS) *~

tests : $(TARGET)
	@$(BASH) test_script.sh

