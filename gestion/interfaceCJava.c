#include <jni.h>
#include "pkginterface_InterfaceJavaC.h"
#include <stdlib.h>
#include <stdio.h>

#include "deck.h"

JNIEXPORT void JNICALL Java_pkginterface_InterfaceJavaC_sayHello
  (JNIEnv *env, jobject obj)
{
  printf("Hello world !\n");
  return;
}

JNIEXPORT jint JNICALL Java_pkginterface_InterfaceJavaC_createDeck
  (JNIEnv *env, jobject obj, jint size)
{
  Deck deck;
  int adressDeck;

  deck = deck_create(size);
  adressDeck = deck;
  return adressDeck;
}

JNIEXPORT void JNICALL Java_pkginterface_InterfaceJavaC_deleteDeck
  (JNIEnv *env, jobject obj, jint adressDeck)
{
  Deck deck;

  deck = adressDeck;
  deck_delete(deck);
  return;
}
