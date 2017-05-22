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
nameFileError="erreur.log"

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

move_it()
{
  if [ -f "$1" -a -d "$2" ]; then
    mv "$1" "$2"
  fi
}

delete_file()
{
  if [ -f "$1" ]; then
    echo -n "Fichier : $1 "
    rm "$1"
  fi
  if [ ! -f "$1" ]; then
    echo -e "\033[32m[SUPPRIMER]\033[0m"
  else
    echo -e "\033[33m[ERREUR]\033[0m"
  fi
}

compile_c_file()
{
  if [ -f "$1" ]; then
    echo -n "Fichier : $1 "
    gcc "${optionC}" -I"${pathJni}/include" -I"${pathJni}/include/linux" -c "$1" 2>> "${nameFileError}"
  fi
  fileObject=`echo "$1" | cut -f 1 -d '.' | cut -f 2 -d '/' `".o"
  if [ -f "${fileObject}" ]; then
    echo -e "\033[32m[COMPILER]\033[0m"
  else
    echo -e "\033[31m[ERREUR]\033[0m"
  fi
}

compile_c()
{
  for file in "${pathSource}/"*; do
    if [ -f "${file}" -a "${file}" != "${pathSource}/Makefile" ]; then
      compile_c_file "${file}"
    fi
  done
  exist_folder "${pathObject}"
  for file in *".o"; do
    move_it "${file}" "${pathObject}"
  done
}

compile_java()
{
  javac "${pathSource}/${namePakage}/"*".java"
  for file in "${pathSource}/${namePakage}/"*".java"; do
    echo -n "Fichier : ${file} "
    if [ -f "${file}" ]; then
      echo -e "\033[32m[COMPILER]\033[0m"
    else
      echo -e "\033[31m[ERREUR]\033[0m"
    fi
  done
  exist_folder "${pathClass}"
  exist_folder "${pathClass}/${namePakage}"
  for file in "${pathSource}/${namePakage}/"*".class"; do
      move_it "${file}" "${pathClass}/${namePakage}"
  done
}

compile_interface()
{
  cd "${pathSource}"
  javah "${namePakage}.InterfaceJavaC"
  cd "../"
  move_it "${pathSource}/${namePakage}_InterfaceJavaC.h" "header/"

  exist_folder "${pathObject}"
  for file in "${pathSource}/${namePakage}/"*".class"; do
      move_it "${file}" "${pathClass}/${namePakage}"
  done
}

compile_lib()
{
  sed -i -e "/^\tSystem.load/d" "src/${namePakage}/InterfaceJavaC.java"
  sed -i "/\/\/ DO NOT EDIT THIS LINE PLEASE/a\\\tSystem.load(\"${pathLocal}/${pathLibrary}/${nameFileLib}\");" "src/${namePakage}/InterfaceJavaC.java"
  echo -n "Fichier : ${nameFileLib} "
  gcc -shared -o "${nameFileLib}" "${pathObject}/"* 2>> "${nameFileError}"
  if [ -f "${nameFileLib}" ]; then
    echo -e "\033[32m[COMPILER]\033[0m"
  else
    echo -e "\033[31m[ERREUR]\033[0m"
  fi
  move_it "${nameFileLib}" "${pathLibrary}/"
}

clean_c()
{
  for file in "${pathObject}/"*".o"; do
    if [ -f "${file}" ]; then
      delete_file "${file}"
    fi
  done
}

clean_java()
{
  for file in "${pathClass}/${namePakage}/"*".class"; do
    if [ -f "${file}" ]; then
      delete_file "${file}"
    fi
  done
}

clean_log()
{
  file="${nameFileError}"
  if [ -f "${file}" ]; then
    delete_file "${file}"
  fi
}

clean_lib()
{
  file="${pathLibrary}/${nameFileLib}"
  if [ -f "${file}" ]; then
    delete_file "${file}"
  fi
}

clean_player()
{
  for file in "${pathPlayer}/"*; do
    if [ -f "${file}" ]; then
      delete_file "${file}"
    fi
  done
}

clean_saveguards()
{
  for file in "${pathSaveguards}/"*; do
    if [ -f "${file}" ]; then
      delete_file "${file}"
    fi
  done
}

################################################################################
## Programme Principale    #####################################################
## Fonctionnalitées        #####################################################
################################################################################

if [ "$1" != 'play' -a $# -ne 1 ]; then
  echo -e "\nErreur : nombre d'argument."
  echo "Usage : $0 arg1"
  exit -1
fi

if [ "$1" == 'play' -a $# -ne 2 ]; then
  echo -e "\nErreur : nombre d'argument."
  echo "Usage : $0 play arg1 (console/gui)"
  exit -1
fi

if [ "$1" != "compile" -a "$1" != "compilelib" -a "$1" != "clean" -a "$1" != "play" -a "$1" != "remove" ]; then
  echo -e "\nErreur : argument incorrect."
  echo "Veuillez utiliser les mots : compile, clean, play, remove"
  exit -2
fi

if [ "$1" == "compile" ]; then
  exist_folder "player"
  exist_folder "save"
  compile_c
  compile_interface
  compile_java
  compile_lib
fi

if [ "$1" == "clean" ]; then
  clean_c
  clean_java
  clean_lib
  clean_log
fi

if [ "$1" == "play" ]; then

  if [ executable == 0 ]; then
    echo "Can't play..."
    echo "Please compile the project before to start it"
    echo "Thank you"
    exit 1
  fi

  cd class
  if [ "$2" == 'console' ]; then
    application="${namePakage}.InterfaceConsole"
  else if [ "$2" == 'gui' ]; then
    application="${namePakage}.InterfaceSwing"
  fi;fi
  java "$application"
fi

if [ "$1" == "remove" ]; then
  clean_player
  clean_saveguards
fi

exit 0
