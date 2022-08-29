package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {
    public static Method beforeSuit = null;
    public static Method afterSuit = null;
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        Class<?> test = testClass.getClass();
        Method[] methods = test.getDeclaredMethods();
        ArrayList<Method> toDo = new ArrayList<>();
        for (Method method : methods) {
            if(method.isAnnotationPresent(BeforeSuit.class)){
                if(beforeSuit != null){
                    throw new RuntimeException("U can't use one more BeforeSit annotation");
                } else {
                    beforeSuit = method;
                }
            }
            else if(method.isAnnotationPresent(Test.class)){
                toDo.add(method);
            }
            else {
                if(afterSuit != null){
                    throw new RuntimeException("U can't use one more AfterSuit annotation");
                } else{
                    afterSuit = method;
                }
            }

        }
        try {
            Constructor<?> constructor = test.getConstructor();
            try {

                testClass = (TestClass) constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        toDo.sort(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o1.getAnnotation(Test.class).priority() - o2.getAnnotation(Test.class).priority();
            }
        });

        if(beforeSuit != null){
            try {
                beforeSuit.invoke(testClass);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        for (Method method : toDo) {
            try {
                method.invoke(testClass);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if(afterSuit != null){
            try {
                afterSuit.invoke(testClass);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
