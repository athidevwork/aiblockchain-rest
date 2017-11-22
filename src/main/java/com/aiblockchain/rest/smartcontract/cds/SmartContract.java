/**
 * 
 */
package com.aiblockchain.rest.smartcontract.cds;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * @author Athi
 *
 */
public class SmartContract {
	// Create a File object on the root of the directory containing the class file
	static File file = new File("contracts\\");
	
    public static void main(String[] args) {
        //JavaClassLoader javaClassLoader = new JavaClassLoader();
    	SmartContract sc = new SmartContract();
        sc.invokeClassMethod("com.aiblockchain.rest.smartcontract.cds.Book", "getMember");
    	//loadSmartContract();
    }
    
    public void invokeClassMethod(String classBinName, String methodName){
        try {
            // Create a new JavaClassLoader
            ClassLoader classLoader = this.getClass().getClassLoader();
            System.out.println("loader : " + classLoader);
            
            // Load the target class using its binary name
            Class loadedClass = classLoader.loadClass(classBinName);
            System.out.println("Loaded class name: " + loadedClass.getName());
            
		    System.out.println(Arrays.asList(loadedClass.getMethods()));
		    System.out.println(Arrays.asList(loadedClass.getFields()));
		    
            // Create a new instance from the loaded class
		    Object myClassObject = loadedClass.newInstance();
		    System.out.println("Instantiated class : " + myClassObject.getClass());
            /*Constructor constructor = loadedClass.getConstructor();
            Object myClassObject = constructor.newInstance();*/
		    
            // Getting the target method from the loaded class and invoke it using its name
            Method method = loadedClass.getMethod(methodName);
            System.out.println("Invoked method name: " + method.getName());
            method.invoke(myClassObject);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	public static void loadSmartContract() {
		try {
		    // Convert File to a URL
		    URL url = file.toURL();          // file:/c:/myclasses/
		    URL[] urls = new URL[]{url};

		    // Create a new class loader with the directory
		    ClassLoader cl = new URLClassLoader(urls);

		    // Load in the class; MyClass.class should be located in
		    // the directory file:/c:/myclasses/com/mycompany
		    Class cls = cl.loadClass("com.aiblockchain.rest.smartcontract.Book");
		    System.out.println(cls.getMethods());
		    System.out.println(cls.getFields());
		} catch (MalformedURLException e) {
		} catch (ClassNotFoundException e) {
		}
	}
}
