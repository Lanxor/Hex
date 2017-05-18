all:

testjava: test_java_coordinates test_java_player clean_java

testjava: compile_java tj_coordinates tj_historic tj_score tj_deck tj_move clean

compile_java:
	javac pkginterface/*.java

tj_coordinates:
	javac pkgtest/test_coordinates.java
	java pkgtest.test_coordinates

tj_historic:
	javac pkgtest/test_historic.java
	java pkgtest.test_historic

tj_score:
	javac pkgtest/test_score.java
	java pkgtest.test_score

tj_deck:
	javac pkgtest/test_deck.java
	java pkgtest.test_deck

tj_move:
	javac pkgtest/test_move.java
	java pkgtest.test_move

clean_java:
	rm -f pkginterface/*.class
	rm -f pkgtest/*.class

clean_c:
	rm -f tests/*.o
	rm -f tests/*.exe
