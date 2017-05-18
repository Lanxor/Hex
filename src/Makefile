all:

testc: test_compile test_deck clean_c

testjava: test_java_coordinates test_java_player clean_java

test_compile:
	gcc -c deck.c vertice.c edge.c group.c

test_deck:
	gcc test_deck.c -o test_deck.exe

test_java_coordinates:
	javac pkginterface/Coordinates.java pkgtest/test_coordinates.java
	java pkgtest.test_coordinates

test_java_player:
	javac pkginterface/Player.java pkgtest/test_player.java
	java pkgtest.test_player

clean_java:
	rm -f pkginterface/*.class
	rm -f pkgtest/*.class

clean_c:
	rm -f tests/*.o
	rm -f tests/*.exe
