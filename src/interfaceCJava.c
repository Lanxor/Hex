#include <jni.h>
#include "../header/pkginterface_InterfaceJavaC.h"
#include <stdlib.h>
#include <stdio.h>

#include "../header/deck.h"

Deck global_deck;

JNIEXPORT void JNICALL Java_pkginterface_InterfaceJavaC_sayHello
  (JNIEnv *env, jobject obj)
{
    printf("Hello world !\n");
    return;
}

JNIEXPORT void JNICALL Java_pkginterface_InterfaceJavaC_createDeck
  (JNIEnv *env, jobject obj, jint size)
{
    extern Deck global_deck;
    global_deck = deck_create(size);
    return;
}

JNIEXPORT void JNICALL Java_pkginterface_InterfaceJavaC_deleteDeck
  (JNIEnv *env, jobject obj)
{
    extern Deck global_deck;
    deck_delete(global_deck);
    return;
}

/*
 * Class:     pkginterface_InterfaceJavaC
 * Method:    printDeckColor
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_pkginterface_InterfaceJavaC_printDeckColor
  (JNIEnv *env, jobject obj)
{
    extern Deck global_deck;
    deck_print_color(global_deck);
    return;
}

/*
 * Class:     pkginterface_InterfaceJavaC
 * Method:    modifyVertice
 * Signature: (CII)V
 */
JNIEXPORT void JNICALL Java_pkginterface_InterfaceJavaC_modifyVertice
  (JNIEnv *env, jobject obj, jchar color, jint abscisse, jint ordonnee)
{
    extern Deck global_deck;
    deck_vertice_modify(global_deck, color, abscisse, ordonnee);
    return;
}

/*
 * Class:     pkginterface_InterfaceJavaC
 * Method:    isModifyVertice
 * Signature: (CII)I
 */
JNIEXPORT jint JNICALL Java_pkginterface_InterfaceJavaC_isModifyVertice
  (JNIEnv *env, jobject obj, jchar color, jint abscisse, jint ordonnee)
{
    extern Deck global_deck;
    return deck_vertice_modify_is_possible(global_deck, color, abscisse, ordonnee);
}
