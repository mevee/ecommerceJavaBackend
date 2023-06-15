package com.example.todoapp.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AppLogger {
    public String name;

    private AppLogger(String className) {
       this.name =className;
    }

    public void info(Object message){
        System.out.println(name+":: "+message.toString());
    }
    public void error(Object message){
        System.out.println(name+":: error->"+message.toString());
    }

    public static AppLogger getLogger(Class clazz) {
        return new AppLogger(clazz.getName());
    }

}
