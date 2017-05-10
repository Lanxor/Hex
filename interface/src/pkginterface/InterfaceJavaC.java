/**
 * @file InterfaceJavaC.java
 * @author PACE Eleana
 * @date 26 avril 2017
 * @brief Interface des fonctions du c 
 */

package pkginterface;

public class InterfaceJavaC {
    public native void sayHello();
    
    static 
    {
        System.load("librairieC.so");
    }
}
