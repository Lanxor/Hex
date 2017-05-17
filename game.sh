#! /bin/bash

################################################################################
## Programme Principale    #####################################################
## Options                 #####################################################
################################################################################

optionC="-fPIC"
optionJava=""
pathLocal=`pwd`
pathSaveguards="save"
pathPlayer="player"
pathSource="src"
pathClass="class"
pathObject="obj"
pathLibrary="lib"
namePakage="pkginterface"
nameFileLib="libInterfaceC.so"
nameFileConfig="config.conf"

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
  echo "Lisez le Readme.md pour plus d'explication."
  echo "Ou rendez-vous sur la page https://github.com/Lanxor/Hex"
  exit -1
fi

################################################################################
## Programme Principale    #####################################################
## Fonctions               #####################################################
################################################################################

exist_folder()
{
  if [ ! -d "$1" ]; then
    mkdir "$1"
  fi
}

delete_file()
{
  if [ -f "$1" ]; then
    echo "Suppression du fichier : $1"
    rm "$1"
  fi
}

compile_c_file()
{
  if [ -f "$1" ]; then
    echo -e "\tFile $1"
    gcc "${optionC}" -I"${pathJni}/include" -I"${pathJni}/include/linux" -c "$1"
  fi
}

compile_c()
{
  for file in "${pathSource}/"*; do
    if [ -f "${file}" -a "${file}" != "${pathSource}/Makefile" ]; then
      compile_c_file "${file}"
    fi
  done
  echo -e "\tMove it to ${pathObject}/"
  exist_folder "${pathObject}"
  mv *.o "${pathObject}"
}

compile_java()
{
  javac "${pathSource}/${namePakage}/"*".java"
  for file in "${pathSource}/${namePakage}/"*".java"; do
    echo -e "\tFile $file"
  done
  exist_folder "${pathClass}"
  exist_folder "${pathClass}/${namePakage}"
  mv "${pathSource}/${namePakage}/"*".class" "${pathClass}/${namePakage}/"
}

compile_interface()
{
  cd "${pathSource}"
  echo -e "\tCreate header file"
  javah "${namePakage}.InterfaceJavaC"
  cd ".."
  echo -e "\tMove it to header/"
  mv "${pathSource}/${namePakage}_InterfaceJavaC.h" "header/"

  compile_c_file "${pathSource}/interfaceCJava.c"
  exist_folder "${pathObject}"
  mv *.o "${pathObject}"
}

compile_lib()
{
  sed -i -e "/^\tSystem.load/d" "src/${namePakage}/InterfaceJavaC.java"
  sed -i "/\/\/ DO NOT EDIT THIS LINE PLEASE/a\\\tSystem.load(\"${pathLocal}/${pathLibrary}/${nameFileLib}\");" "src/${namePakage}/InterfaceJavaC.java"
  echo -e "\tFile ${nameFileLib}"
  gcc -shared -o "${nameFileLib}" "${pathObject}/"*
  echo -e "\tMove it to ${pathLibrary}/"
  mv "${nameFileLib}" "${pathLibrary}/"
}

clean_c()
{
  for file in "${pathObject}/"*".o"; do
    delete_file "$file"
  done
}

clean_java()
{
  for file in "${pathClass}/${namePakage}/"*".class"; do
    delete_file "$file"
  done
}

clean_lib()
{
  delete_file "${pathLibrary}/${nameFileLib}"
}

clean_player()
{
  for file in "${pathPlayer}/"*; do
    delete_file "${file}"
  done
}

clean_saveguards()
{
  for file in "${pathSaveguards}/"*; do
    delete_file "${file}"
  done
}

################################################################################
## Programme Principale    #####################################################
## Fonctionnalitées        #####################################################
################################################################################

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
  exist_folder "player"
  exist_folder "save"
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
  clean_player
  clean_saveguards
fi

exit 0
