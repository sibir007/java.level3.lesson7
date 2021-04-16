package ru.geekbrains.java.level3.lesson7;

public class MainTest {
    @BeforeSuite
    public void init(){
        System.out.println("BeforeSuite");
    }

//    @BeforeSuite
//    public void init2(){
//        System.out.println("BeforeSuite");
//    }

    @AfterSuite
    public void shutdown(){
        System.out.println("AfterSuite");
    }
//    @AfterSuite
//    public void shutdown2(){
//        System.out.println("AfterSuite");
//    }

    @Test(order = 1)
    public void test1(){
        System.out.println("test 1");
    }
    @Test(order = 2)
    public void test2(){
        System.out.println("test 2");
    }
    @Test(order = 3)
    public void test3(){
        System.out.println("test 3");
    }
    @Test(order = 4)
    public void test4(){
        System.out.println("test 4");
    }
    @Test(order = 5)
    public void test5(){
        System.out.println("test 5");
    }
    @Test(order = 6)
    public void test6(){
        System.out.println("test 6");
    }
    @Test(order = 7)
    public void test7(){
        System.out.println("test 7");
    }
}
