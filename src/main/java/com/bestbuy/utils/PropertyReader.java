package com.bestbuy.utils;

import java.io.FileInputStream;
import java.util.Properties;


public class PropertyReader {
    /* Rules for implementing singleton design pattern
    1. make the constructor private
    2. create a static method to get the instance
    3. create a static member variables
     */

    // declare the PropertyReader variable , volatile keyword is to avoid multithreading
    private static volatile PropertyReader propInstance;

    // Create private constructor because of prevent the instatiation of class
    private PropertyReader() {
    }
    // synchronized will handle multithreading issue
    public static synchronized PropertyReader getInstance() {

        if (propInstance == null) { // if its null then
            propInstance = new PropertyReader(); // create the object/instance of property class

        }
        return propInstance;
    }

    /**
     * This method will read property from property file
     * @param propertyName
     * @return
     */
    public String getProperty (String propertyName){

        Properties prop = new Properties();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/com/bestbuy/resources/propertyfile/application.properties");
            prop.load(inputStream);
            if(prop.getProperty(propertyName)!=null){
                return prop.getProperty(propertyName);
            }
        } catch (Exception e) {
            System.out.println("Property not found");
        }
        return null;
    }


}
