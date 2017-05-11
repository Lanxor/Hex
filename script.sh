#! /bin/bash

pathJni="/usr/lib/jvm/java-8-openjdk-amd64"
pathSrcC="gestion"
pathSrcJava="interface/src"
pathObjC="gestion/obj"
pathLib="interface/src"

nameLib="librairies.so"


compile_file()
{
  gcc -fPIC -c "$1"
}

compile_interface()
{
  gcc -fPIC -I"${pathJni}/include" -I"${pathJni}/include/linux" -c "$1"
}

compile_c()
{
  echo -e "\t - Compilation du fichier vertice.c"
  compile_file "${pathSrcC}/vertice.c"

  echo -e "\t - Compilation du fichier edge.c"
  compile_file "${pathSrcC}/edge.c"

  echo -e "\t - Compilation du fichier deck.c"
  compile_file "${pathSrcC}/deck.c"

  echo -e "\t - Compilation du fichier interfaceCJava.c"
  compile_interface "${pathSrcC}/interfaceCJava.c"

  mv *.o "${pathObjC}/"
}

compile_lib()
{
  gcc -shared -o ${nameLib} ${pathObjC}/*
  mv "${nameLib}" "${pathLib}/${nameLib}"
}

compile_java()
{
  return 0
}

compile_project()
{
  echo -e "\t - Compilation du projet C"
  compile_c

  echo -e "\t - Compilation de la librairie"
  compile_lib
}

clean_c()
{
  for file in "${pathObjC}/"*; do
    if [ -f $file ]; then
      rm "$file"
    fi
  done
}

clean_lib()
{
  file="${pathLib}/${nameLib}"
  if [ -f "${file}" ]; then
    rm "${file}"
  fi
}

clean_project()
{
  echo -e "\t - Nettoyage du c"
  clean_c

  echo -e "\t - Nettoyage de la librairie"
  clean_lib
}



usage()
{
  echo -e "\n##################################################"
  echo "###                Jeux de Hex                 ###"
  echo "##################################################"

  echo -e "\nVoici les différents options possible pour jouer au jeu du Hex :"
  echo -e "\tcompile - Installer/Compiler le programme."
  echo -e "\tclean - Désinstaller/Effacer le programme."
  echo -e "\tplay - Lancer le jeu de Hex."
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
  echo "Compilation du projet"
  compile_project
fi

if [ "$1" == "clean" ]; then
  echo "Nettoyage du projet"
  clean_project
fi

if [ "$1" == "play" ]; then
  echo "Lancement du jeu"
fi

exit 0
