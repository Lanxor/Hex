#! /bin/bash

optionC="-fPIC"
namePakage="version2"
nameFileLib="libInterfaceC.so"
nameFileConfig="config.conf"
nameFolderClass="class"
nameFolderObject="obj"
nameFolderLib="lib"


if [ -d "/usr/lib/jvm/java-8-openjdk-amd64" ]; then
  pathJni="/usr/lib/jvm/java-8-openjdk-amd64"
fi

if [ -z "$pathJni" -a -d "/usr/lib/jvm/java-8-openjdk" ];then
  pathJni="/usr/lib/jvm/java-8-openjdk"
fi

if [ -z "$pathJni" -a -d "$nameFileConfig" ]; then
  pathJni=`grep "jni" "$nameFileConfig" | cut -f 2 -d ':'`
fi

if [ -z "$pathJni" ]; then
  echo "Erreur : La librairie jvm n'est pas installer ou est inexistant"
  echo "Usage : veuillez bien configurer le fichier config.conf"
  echo "Ajouter la ligne jni:/chemin/vers/le/dossier/java-8-openjdk"
  exit -1
fi

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

  echo -e "\tMove it to ${nameFolderObject}/"
  if [ ! -d "${nameFolderObject}" ]; then
    mkdir "${nameFolderObject}"
  fi
  mv *.o "${nameFolderObject}"
}

compile_java()
{
  javac "src/${namePakage}/"*".java"
  for file in "src/${namePakage}/"*".java"; do
    echo -e "\tFile $file"
  done
  if [ ! -d "${nameFolderClass}/${namePakage}" ]; then
    mkdir "${nameFolderClass}/${namePakage}"
  fi
  mv "src/${namePakage}/"*".class" "${nameFolderClass}/${namePakage}/"
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
  mv *.o "${nameFolderObject}"
}

compile_lib()
{
  echo -e "\tFile ${nameFileLib}"
  gcc -shared -o "${nameFileLib}" "${nameFolderObject}/"*
  echo -e "\tMove it to ${nameFolderLib}/"
  mv "${nameFileLib}" "${nameFolderLib}/"
}

clean_c()
{
  for file in "${nameFolderObject}/"*".o"; do
    delete_file "$file"
  done
}

clean_java()
{
  for file in "${nameFolderClass}/${namePakage}/"*".class"; do
    delete_file "$file"
  done
}

clean_lib()
{
  delete_file "${nameFolderLib}/${nameFileLib}"
}




if [ $# -ne 1 ]; then
  echo -e "\nErreur : nombre d'argument."
  echo "Usage : $0 arg1"
  usage
  exit -1
fi

if [ "$1" != "compile" -a "$1" != "compilelib" -a "$1" != "clean" -a "$1" != "play" -a "$1" != "remove" ]; then
  echo -e "\nErreur : argument incorrect."
  echo "Veuillez utiliser les mots : compile, clean, play, remove"
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

if [ "$1" == "remove" ]; then
  rm save/*
  rm player/*
fi

exit 0
