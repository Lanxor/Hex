#! /bin/bash

pathJni="/usr/lib/jvm/java-8-openjdk-amd64"
optionC="-fPIC"
namePakage="pkginterface"
nameFileLib="libInterfaceC.so"

delete_file()
{
  if [ -f "$1" ]; then
    rm "$1"
  fi
}

compile_c_file()
{
  gcc "${optionC}" -I"${pathJni}/include" -I"${pathJni}/include/linux" -c "$1"
}

compile_c()
{
  echo -e "\tFile vertice.c"
  compile_c_file "src/vertice.c"

  echo -e "\tFile edge.c"
  compile_c_file "src/edge.c"

  echo -e "\tFile deck.c"
  compile_c_file "src/deck.c"

  echo -e "\tMove it to obj/"
  mv *.o obj/
}

compile_java()
{
  javac "src/${namePakage}/"*".java"
  for file in "src/${namePakage}/"*".java"; do
    echo -e "\tFile $file"
  done
  mv "src/${namePakage}/"*".class" "class/${namePakage}/"
}

compile_interface()
{
  cd "src"
  echo -e "\tCrate header file"
  javah "${namePakage}.InterfaceJavaC"
  cd ".."
  echo -e "\tMove it to header/"
  mv "src/${namePakage}_InterfaceJavaC.h" "header/"

  echo -e "\tFile interfaceCJava.c"
  compile_c_file "src/interfaceCJava.c"
  mv *.o obj/
}

compile_lib()
{
  gcc -shared -o "${nameFileLib}" obj/*
  mv "${nameFileLib}" "lib/"
}

clean_c()
{
  for file in "obj/"*".o"; do
    delete_file "$file"
  done
}

clean_java()
{
  for file in "class/${namePakage}/"*".class"; do
    delete_file "$file"
  done
}

clean_lib()
{
  delete_file "lib/${nameFileLib}"
}




if [ $# -ne 1 ]; then
  echo -e "\nErreur : nombre d'argument."
  echo "Usage : $0 arg1"
  usage
  exit -1
fi

if [ "$1" != "compile" -a "$1" != "compilelib" -a "$1" != "clean" -a "$1" != "play" ]; then
  echo -e "\nErreur : argument incorrect."
  echo "Veuillez utiliser les mots : compile, clean, play"
  usage
  exit -2
fi

if [ "$1" == "compile" ]; then
  echo "Compilation des fichiers C"
  compile_c

  echo "Compilation des fichier interfaceJavaC"
  compile_interface

  echo "Compilation des fichier JAVA"
  compile_java

  echo "Création de la librairie"
  compile_lib
fi

if [ "$1" == "clean" ]; then
  echo "Nettoyage de C"
  clean_c

  echo "Nettoyage de Java"
  clean_java

  echo "Nettoyage de la librairies"
  clean_lib
fi

if [ "$1" == "play" ]; then

  if [ ! -f "class/${namePakage}/Interface.class" ]; then
    echo "Can't play..."
    echo "Please compile the project before to start it"
    echo "Thank you"
    exit 1
  fi

  cd class
  java "${namePakage}.Interface"
fi

exit 0
