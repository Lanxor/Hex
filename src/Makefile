
testjava: test_java_coordinates test_java_player

test_java_coordinates:
	javac pkginterface/Coordinates.java pkgtest/test_coordinates.java
	java pkgtest.test_coordinates

test_java_player:
	javac pkginterface/Player.java pkgtest/test_player.java
	java pkgtest.test_player

clean:
	rm -f pkginterface/*.class
	rm -f pkgtest/*.class
