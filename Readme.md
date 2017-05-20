# Game of Hex

## The Game (explications)

Hex is a strategy board game for two players played on a hexagonal grid, theoretically of any size and several possible shapes, but traditionally as an 11×11 rhombus. Players alternate placing markers or stones (Go stones make ideal playing pieces) on unoccupied spaces in an attempt to link their opposite sides of the board in an unbroken chain. One player must win; there are no draws.

## Compilation

Compile before to start to play.

```
> ./game.sh compile
```

## Usage

Let's go !

```
> ./game.sh play console
```
or

```
> ./game.sh play gui
```

## Cleaning

Clean objects and class files.

```
> ./game.sh clean
```
## Removing

Remove players and saveguards files.

```
> ./game.sh remove
```
## Reports

If you have any problems with the library, please check where the folder "jvm" of Java-8-openjdk is. And create or edit the file "config.conf" like this :
Add or edit this line :
```
jni:/path/to/jvm/java-8-openjdk
```
If you don't know where to search the folder. Please search in "/usr/lib/"
