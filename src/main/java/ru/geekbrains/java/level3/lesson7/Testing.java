package ru.geekbrains.java.level3.lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Testing {
    private static Method beforeSuiteMet = null;
    private static Method afterSuiteMet = null;
    private static List<Method> testMetList = new ArrayList();

    public static void start(Class cls){

        Method[] methods = cls.getDeclaredMethods();
        getBeforeAndAfterSuitMethods(methods);
        getTestMethodsList(methods);

        Object obj = null;
        try {
            obj= cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            if (beforeSuiteMet != null) {
                beforeSuiteMet.invoke(obj);
            }
            for (Method m: testMetList) {
                m.invoke(obj);
            }
            if (afterSuiteMet != null) {
                afterSuiteMet.invoke(obj);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Извлекает из массива методов BeforeSuit и AfterSuit методы
     * если каздого из них больше одного бросает RuntimeException
     */
    private static void getBeforeAndAfterSuitMethods(Method[] methods) {
        int countBeforeSuiteMet = 0;
        int countAfterSuiteMet = 0;
        for (Method m: methods) {
            if (m.getAnnotation(BeforeSuite.class) != null){
                countBeforeSuiteMet++;
                beforeSuiteMet = m;
            }
            if (m.getAnnotation(AfterSuite.class) != null) {
                countAfterSuiteMet++;
                afterSuiteMet = m;
            }
        }
        if (countBeforeSuiteMet > 1 || countAfterSuiteMet > 1) {
            throw new RuntimeException("BeforeSuit и AfterSuite методы должен присутствовать в единственном экземпляре");
        }
    }

    /**
     * Извлекает из массива методов Test методы
     * сортирует их по порядку
     */
    private static void getTestMethodsList(Method[] methods) {
        for (Method m: methods) {
            if (m.getAnnotation(Test.class) != null){
                testMetList.add(m);
            }
        }
        sortTestMethodsList();
    }

    /**
     * Сортирует Test методы по порядку
     */
    private static void sortTestMethodsList() {
        Collections.sort(testMetList, new Comparator<Method>() {
            public int compare(Method m1, Method m2) {
                return m1.getAnnotation(Test.class).order() - m2.getAnnotation(Test.class).order();
            }
        });
    }
}
